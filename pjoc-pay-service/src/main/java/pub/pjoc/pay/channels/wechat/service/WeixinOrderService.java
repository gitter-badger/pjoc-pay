package pub.pjoc.pay.channels.wechat.service;

import com.xiongyingqi.util.StringUtil;
import pub.pjoc.pay.channels.wechat.util.WeixinPayUtils;
import pub.pjoc.pay.channels.wechat.vo.OrderCallBackRequest;
import pub.pjoc.pay.channels.wechat.vo.OrderRequest;
import pub.pjoc.pay.channels.wechat.vo.OrderResponse;
import pub.pjoc.pay.channels.wechat.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by blademainer on 16/3/21.
 */
@Service
public class WeixinOrderService {
    private static final Logger logger = LoggerFactory.getLogger(WeixinOrderService.class);
    @Autowired
    private WeixinPayConfigInfo weixinPayConfigInfo;

    public OrderResponse requestOrder(OrderRequest orderRequest) {
        Assert.hasText(orderRequest.getBody(), "商品描述不能为空");
        Assert.isTrue(orderRequest.getTotalFee() != null && orderRequest.getTotalFee() > 0, "金额必须大于0");
        Assert.hasText(orderRequest.getTradeType(), "支付类型必须是: JSAPI，NATIVE，APP");
        if (WeixinPayUtils.isNeedOpenId(orderRequest)) {
            Assert.hasText(orderRequest.getOpenId(), "openId不能为空");
        }

        orderRequest.setAppId(weixinPayConfigInfo.getAppId());
        orderRequest.setMchId(weixinPayConfigInfo.getPayMchId());

        String nonceStr = StringUtil.randomString(32);
        orderRequest.setNonceStr(nonceStr);

        String orderId = WeixinPayUtils.generateOrderId();
        orderRequest.setOutTradeNo(orderId);

        orderRequest.setNotifyUrl(weixinPayConfigInfo.getPayCallBackUrl());

        String payKey = weixinPayConfigInfo.getPayKey();
        //sign
        String sign = WeixinPayUtils.sign(orderRequest, payKey, "sign");
        orderRequest.setSign(sign);


        String orderRequestUrl = weixinPayConfigInfo.getOrderRequestUrl();
        try {
            InputStream inputStream = WeixinPayUtils.xmlHttpPostRequest(orderRequestUrl, orderRequest);
            OrderResponse orderResponse = WeixinPayUtils.parseOrderResponse(inputStream, payKey);
            if (logger.isDebugEnabled()) {
                logger.debug("Requesting a order by request: {} and get response: {}", orderRequest, orderResponse);
            }
            return orderResponse;
        } catch (IOException e) {
            logger.error("Caught exception when requesting a order! message: " + e.getMessage(), e);
        }

        return null;
    }

    public ReturnResult doCallBack(OrderCallBackRequest callBackRequest) {
        Assert.notNull(callBackRequest, "回调通知为空");
        String payKey = weixinPayConfigInfo.getPayKey();
        String sign = WeixinPayUtils.sign(callBackRequest, payKey, "sign");
        if(sign == null || !sign.equals(callBackRequest.getSign())){
            ReturnResult returnResult = new ReturnResult();
            returnResult.setReturnCode(ReturnResult.FAIL);
            returnResult.setReturnMsg("sign error");
            logger.error("Doing call back... check sign error! remote sign: {}, local sign: {}", callBackRequest.getSign(), sign);
            return returnResult;
        }
        ReturnResult returnResult = new ReturnResult();
        returnResult.setReturnCode(ReturnResult.SUCCESS);
        return returnResult;
    }



}
