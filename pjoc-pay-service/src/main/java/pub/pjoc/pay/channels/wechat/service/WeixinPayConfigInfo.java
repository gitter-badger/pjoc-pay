package pub.pjoc.pay.channels.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.pjoc.config.ConfigInfoService;

import java.util.ResourceBundle;

/**
 * Created by blademainer on 16/3/22.
 */
@Service
public class WeixinPayConfigInfo implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(WeixinPayConfigInfo.class);
    @Autowired
    private ConfigInfoService configInfoService;
    public static final String GROUP_ID = "weixin_pay";

    public static final String ORDER_REQUEST_URL = "order_request_url";
    public static final String ORDER_QUERY_URL = "order_query_url";
    public static final String CLOSE_ORDER_URL = "close_order_url";
    public static final String REFUND_URL = "refund_url";
    public static final String REFUND_QUERY_URL = "refund_query_url";
    public static final String DOWNLOAD_BILL_URL = "download_bill_url";
    public static final String PAY_CALL_BACK_URL = "pay_call_back_url";
    public static final String PAY_MCH_ID = "pay_mch_id";
    public static final String PAY_KEY = "pay_key";
    public static final String WEIXIN_ID = "weixin_id";

    // 系统下单地址
    public static final String SYSTEM_ORDER_URL = "system.orderUrl";

    public String getConfig(String key) {
        String value = configInfoService.getConfig(GROUP_ID, key);
        logger.debug("Getting config... key: {}, value: {}", key, value);
        return value;
    }

    public String setConfig(String key, String value) {
        String exists = configInfoService.setConfig(GROUP_ID, key, value);
        logger.debug("Setting config... key: {}, value: {}, exists: {}", key, value, exists);
        return exists;
    }

    public String getOrderRequestUrl() {
        return getConfig(ORDER_REQUEST_URL);
    }

    public String getOrderQueryUrl() {
        return getConfig(ORDER_QUERY_URL);
    }

    public String getCloseOrderUrl() {
        return getConfig(CLOSE_ORDER_URL);
    }

    public String getRefundUrl() {
        return getConfig(REFUND_URL);
    }

    public String getRefundQueryUrl() {
        return getConfig(REFUND_QUERY_URL);
    }

    public String getDownloadBillUrl() {
        return getConfig(DOWNLOAD_BILL_URL);
    }

    public String getPayCallBackUrl() {
        return getConfig(PAY_CALL_BACK_URL);
    }

    public String getPayMchId() {
        return getConfig(PAY_MCH_ID);
    }

    public String getSystemOrderUrl() {
        return getConfig(SYSTEM_ORDER_URL);
    }

    public String getPayKey() {
        return getConfig(PAY_KEY);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ResourceBundle weixin = ResourceBundle.getBundle("weixin");
        setConfig(ORDER_REQUEST_URL, weixin.getString(ORDER_REQUEST_URL));
        setConfig(ORDER_QUERY_URL, weixin.getString(ORDER_QUERY_URL));
        setConfig(CLOSE_ORDER_URL, weixin.getString(CLOSE_ORDER_URL));
        setConfig(REFUND_URL, weixin.getString(REFUND_URL));
        setConfig(REFUND_QUERY_URL, weixin.getString(REFUND_QUERY_URL));
        setConfig(DOWNLOAD_BILL_URL, weixin.getString(DOWNLOAD_BILL_URL));
        setConfig(PAY_CALL_BACK_URL, weixin.getString(PAY_CALL_BACK_URL));
        setConfig(PAY_MCH_ID, weixin.getString(PAY_MCH_ID));
        setConfig(PAY_KEY, weixin.getString(PAY_KEY));
        // 系统下单地址
        setConfig(SYSTEM_ORDER_URL, weixin.getString(SYSTEM_ORDER_URL));
    }

    public String getAppId() {
        return getConfig(WEIXIN_ID);
    }
}
