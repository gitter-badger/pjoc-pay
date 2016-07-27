package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by blademainer on 16/3/21.
 */
public class OrderResponse extends PayResponseResult {


    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /**
     * 交易类型	trade_type	是	String(16)	JSAPI	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
     */
    @JsonProperty("trade_type")
    @JacksonXmlCData
    private String tradeType;
    /**
     * 预支付交易会话标识	prepay_id	是	String(64)	wx201410272009395522657a690389285100	微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @JsonProperty("prepay_id")
    @JacksonXmlCData
    private String prepayId;
    /**
     * 二维码链接	code_url	否	String(64)	URl：weixin：//wxpay/s/An4baqw	trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
     */
    @JsonProperty("code_url")
    @JacksonXmlCData
    private String codeUrl;

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "tradeType='" + tradeType + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                "} " + super.toString();
    }
}
