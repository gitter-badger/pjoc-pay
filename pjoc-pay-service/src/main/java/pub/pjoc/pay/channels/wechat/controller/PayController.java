package pub.pjoc.pay.channels.wechat.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.StringUtils;
import pub.pjoc.pay.channels.wechat.service.WeixinOrderService;
import pub.pjoc.pay.channels.wechat.service.WeixinPayConfigInfo;
import pub.pjoc.pay.channels.wechat.util.WeixinPayUtils;
import pub.pjoc.pay.channels.wechat.vo.OrderCallBackRequest;
import pub.pjoc.pay.channels.wechat.vo.OrderRequest;
import pub.pjoc.pay.channels.wechat.vo.OrderResponse;
import pub.pjoc.pay.channels.wechat.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;


/**
 * Created by blademainer on 16/3/22.
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);
    public static final String HAS_REQUEST_OAUTH = "PayController.HAS_REQUEST_OAUTH";
    @Autowired
    private WeixinPayConfigInfo configInfo;
    @Autowired
    private WeixinOrderService orderService;

    /**
     * 订单回调通知
     */
    @RequestMapping(value = "/callBack")
    @ResponseBody
    public ReturnResult callBack(HttpServletRequest servletRequest) {
        if (logger.isDebugEnabled()) {
            Enumeration<String> headerNames = servletRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String header = servletRequest.getHeader(headerName);
                logger.debug("Doing call back... header: {}={}", headerName, header);
            }
        }

        OrderCallBackRequest request;

        try {
            XmlMapper xmlMapper = WeixinPayUtils.getXmlMapper();
            ServletInputStream inputStream = servletRequest.getInputStream();
            request = xmlMapper.readValue(inputStream, OrderCallBackRequest.class);
        } catch (IOException e) {
            logger.error("Caught exception when calling back! message: " + e.getMessage(), e);
            return null;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Doing call back... call back: {}", request);
        }
        ReturnResult returnResult = orderService.doCallBack(request);
        if (logger.isDebugEnabled()) {
            logger.debug("Doing call back... returnResult: {}", returnResult);
        }
        return returnResult;
    }

    /**
     * 订单回调通知
     */
    @RequestMapping(value = "/callBack/xml", consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReturnResult callBackWithRequestBody(@RequestBody OrderCallBackRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("Doing call back... call back: {}", request);
        }
        ReturnResult returnResult = orderService.doCallBack(request);
        if (logger.isDebugEnabled()) {
            logger.debug("Doing call back... returnResult: {}", returnResult);
        }
        return returnResult;
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public OrderResponse orderRequest(HttpServletRequest request, HttpSession session,
                                      OrderRequest orderRequest) {
        //        if (WeixinPayUtils.isNeedOpenId(orderRequest)) {
        //            if (!isHasOpenId(session) && !isRequestedOAuth(session)) {
        //                String redirectUrl = getRequestUrlAndParameters(request);
        //                try {
        //                    String oauth = buildOAuthPath(redirectUrl);
        //                    ModelAndView view = new ModelAndView();
        //                    view.setViewName("redirect:" + oauth);
        //                    return view;
        //                } catch (UnsupportedEncodingException e) {
        //                    logger.error("", e);
        //                }
        //            } else {
        //                ModelAndView view = new ModelAndView();
        //                view.addObject("支付失败");
        //                view.setView(new MappingJackson2JsonView());
        //                return view;
        //            }
        //        }
        if (logger.isDebugEnabled()) {
            logger.debug("request query str: {}, orderRequest: {}", request.getQueryString(),
                    orderRequest);
        }
        if (StringUtils.hasText(request.getRemoteAddr()) && StringUtils
                .isEmpty(orderRequest.getSpBillCreateIp())) {
            String remoteAddr = request.getRemoteAddr();
            orderRequest.setSpBillCreateIp(remoteAddr);
        }
        OrderResponse orderResponse = orderService.requestOrder(orderRequest);
        return orderResponse;
        //        ModelAndView mav = new ModelAndView();
        //        mav.addObject(orderResponse);
        //        mav.setView(new MappingJackson2JsonView());
        //        return mav;
    }

    public static String getRequestUrlAndParameters(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        if (StringUtils.hasText(request.getQueryString())) {
            requestURL.append("?");
            requestURL.append(request.getQueryString());
        }
        return requestURL.toString();
    }

    private boolean isRequestedOAuth(HttpSession session) {
        Boolean requested = (Boolean) session.getAttribute(HAS_REQUEST_OAUTH);
        if (requested == null) {
            return false;
        }
        return requested;
    }


}
