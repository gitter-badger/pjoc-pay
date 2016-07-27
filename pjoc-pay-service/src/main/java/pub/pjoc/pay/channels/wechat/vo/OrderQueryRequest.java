package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 公众账号ID	appid	是	String(32)	wxd678efh567hg6787	微信分配的公众账号ID（企业号corpid即为此appId）
 * 商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
 * 微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，优先使用
 * 商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部的订单号，当没提供transaction_id时需要传这个。
 * 随机字符串	nonce_str	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	随机字符串，不长于32位。推荐随机数生成算法
 * 签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	签名，详见签名生成算法
 * Created by blademainer on 16/3/21.
 */
public class OrderQueryRequest {
    /**
     * 公众账号ID	appid	是	String(32)	wxd678efh567hg6787	微信分配的公众账号ID（企业号corpid即为此appId）
     */
    @JsonProperty("appid")
    @JacksonXmlCData
    private String appId;
    /**
     * 商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
     */
    @JsonProperty("mch_id")
    @JacksonXmlCData
    private String mchId;
    /**
     * 微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，优先使用
     */
    @JsonProperty("transaction_id")
    @JacksonXmlCData
    private String transactionId;
    /**
     * 商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部的订单号，当没提供transaction_id时需要传这个。
     */
    @JsonProperty("out_trade_no")
    @JacksonXmlCData
    private String outTradeNo;
    /**
     * 随机字符串	nonce_str	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	随机字符串，不长于32位。推荐随机数生成算法
     */
    @JsonProperty("nonce_str")
    @JacksonXmlCData
    private String nonceStr;
    /**
     * 签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	签名，详见签名生成算法
     */
    @JacksonXmlCData
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
