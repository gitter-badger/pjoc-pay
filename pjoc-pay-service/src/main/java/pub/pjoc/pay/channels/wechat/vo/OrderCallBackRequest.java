package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 回调通知
 * <pre>
 *     通知参数
 * 字段名	变量名	必填	类型	示例值	描述
 * 返回状态码	return_code	是	String(16)	SUCCESS
 * SUCCESS/FAIL
 * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
 * 返回信息	return_msg	否	String(128)	签名失败
 * 返回信息，如非空，为错误原因
 * 签名失败
 * 参数格式校验错误
 * 以下字段在return_code为SUCCESS的时候有返回
 *
 * 字段名	变量名	必填	类型	示例值	描述
 * 公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID（企业号corpid即为此appId）
 * 商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
 * 设备号	device_info	否	String(32)	013467007045764	微信支付分配的终端设备号，
 * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
 * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名算法
 * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
 * 错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
 * 错误代码描述	err_code_des	否	String(128)	系统错误	错误返回的信息描述
 * 用户标识	openid	是	String(128)	wxd930ea5d5a258f4f	用户在商户appid下的唯一标识
 * 是否关注公众账号	is_subscribe	否	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
 * 交易类型	trade_type	是	String(16)	JSAPI	JSAPI、NATIVE、APP
 * 付款银行	bank_type	是	String(16)	CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
 * 总金额	total_fee	是	Int	100	订单总金额，单位为分
 * 货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
 * 现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
 * 现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
 * 代金券或立减优惠金额	coupon_fee	否	Int	10	代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
 * 代金券或立减优惠使用数量	coupon_count	否	Int	1	代金券或立减优惠使用数量
 * 代金券或立减优惠ID	coupon_id_$n	否	String(20)	10000	代金券或立减优惠ID,$n为下标，从0开始编号
 * 单个代金券或立减优惠支付金额	coupon_fee_$n	否	Int	100	单个代金券或立减优惠支付金额,$n为下标，从0开始编号
 * 微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
 * 商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
 * 商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
 * 支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
 * 举例如下：
 *
 * <xml>
 * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
 * <attach><![CDATA[支付测试]]></attach>
 * <bank_type><![CDATA[CFT]]></bank_type>
 * <fee_type><![CDATA[CNY]]></fee_type>
 * <is_subscribe><![CDATA[Y]]></is_subscribe>
 * <mch_id><![CDATA[10000100]]></mch_id>
 * <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
 * <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
 * <out_trade_no><![CDATA[1409811653]]></out_trade_no>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
 * <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
 * <time_end><![CDATA[20140903131540]]></time_end>
 * <total_fee>1</total_fee>
 * <trade_type><![CDATA[JSAPI]]></trade_type>
 * <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
 * </xml>
 * 返回参数
 * 商户处理后同步返回给微信参数：
 *
 * 字段名	变量名	必填	类型	示例值	描述
 * 返回状态码	return_code	是	String(16)	SUCCESS
 * SUCCESS/FAIL
 * SUCCESS表示商户接收通知成功并校验成功
 * 返回信息	return_msg	否	String(128)	OK
 * 返回信息，如非空，为错误原因：
 * 签名失败
 * 参数格式校验错误
 * 举例如下：
 * <xml>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <return_msg><![CDATA[OK]]></return_msg>
 * </xml>
 * </pre>
 * Created by blademainer on 16/3/22.
 */
public class OrderCallBackRequest extends PayResponseResult {
    //    用户标识	openid	是	String(128)	wxd930ea5d5a258f4f	用户在商户appid下的唯一标识
//    是否关注公众账号	is_subscribe	否	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
//    交易类型	trade_type	是	String(16)	JSAPI	JSAPI、NATIVE、APP
//    付款银行	bank_type	是	String(16)	CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
//    总金额	total_fee	是	Int	100	订单总金额，单位为分
//    货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
//    现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
//    现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
//    代金券或立减优惠金额	coupon_fee	否	Int	10	代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
//    代金券或立减优惠使用数量	coupon_count	否	Int	1	代金券或立减优惠使用数量
//    代金券或立减优惠ID	coupon_id_$n	否	String(20)	10000	代金券或立减优惠ID,$n为下标，从0开始编号
//    单个代金券或立减优惠支付金额	coupon_fee_$n	否	Int	100	单个代金券或立减优惠支付金额,$n为下标，从0开始编号
//    微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
//    商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
//    商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
//    支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("is_subscribe")
    private String isSubscribe;
    @JsonProperty("trade_type")
    private String tradeType;
    @JsonProperty("bank_type")
    @JacksonXmlCData
    private String bankType;
    @JsonProperty("total_fee")
    private Integer totalFee;
    @JsonProperty("fee_type")
    private String feeType;
    @JsonProperty("cash_fee")
    private Integer cashFee;
    @JsonProperty("cash_fee_type")
    private String cashFeeType;
    @JsonProperty("coupon_fee")
    private String couponFee;
    @JsonProperty("coupon_count")
    private String couponCount;
    @JsonProperty("transaction_id")
    @JacksonXmlCData
    private String transactionId;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("attach")
    @JacksonXmlCData
    private String attach;
    @JsonProperty("time_end")
    @JacksonXmlCData
    private String time_end;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        return "OrderCallBackRequest{" +
                "openId='" + openId + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", bankType='" + bankType + '\'' +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", couponFee='" + couponFee + '\'' +
                ", couponCount='" + couponCount + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                "} " + super.toString();
    }
}
