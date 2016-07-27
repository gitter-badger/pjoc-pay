package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 以下字段在return_code为SUCCESS的时候有返回
 * <p>
 * 字段名	变量名	必填	类型	示例值	描述
 * 公众账号ID	appid	是	String(32)	wxd678efh567hg6787	微信分配的公众账号ID
 * 商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
 * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
 * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
 * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
 * 错误代码	err_code	否	String(32)	SYSTEMERROR	错误码
 * 错误代码描述	err_code_des	否	String(128)	系统错误	结果信息描述
 * <p>
 * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
 * <p>
 * 字段名	变量名	必填	类型	示例值	描述
 * 设备号	device_info	否	String(32)	013467007045764	微信支付分配的终端设备号，
 * 用户标识	openid	是	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	用户在商户appid下的唯一标识
 * 是否关注公众账号	is_subscribe	否	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
 * 交易类型	trade_type	是	String(16)	JSAPI	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
 * 交易状态	trade_state	是	String(32)	SUCCESS
 * SUCCESS—支付成功
 * REFUND—转入退款
 * NOTPAY—未支付
 * CLOSED—已关闭
 * REVOKED—已撤销（刷卡支付）
 * USERPAYING--用户支付中
 * PAYERROR--支付失败(其他原因，如银行返回失败)
 * 付款银行	bank_type	是	String(16)	CMC	银行类型，采用字符串类型的银行标识
 * 总金额	total_fee	是	Int	100	订单总金额，单位为分
 * 货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
 * 现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
 * 现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
 * 代金券或立减优惠金额	coupon_fee	否	Int	100	“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
 * 代金券或立减优惠使用数量	coupon_count	否	Int	1	代金券或立减优惠使用数量
 * 代金券或立减优惠批次ID	coupon_batch_id_$n	否	String(20)	100	代金券或立减优惠批次ID ,$n为下标，从0开始编号
 * 代金券或立减优惠ID	coupon_id_$n	否	String(20)	10000 	代金券或立减优惠ID, $n为下标，从0开始编号
 * 单个代金券或立减优惠支付金额	coupon_fee_$n	否	Int	100	单个代金券或立减优惠支付金额, $n为下标，从0开始编号
 * 微信支付订单号	transaction_id	是	String(32)	1009660380201506130728806387	微信支付订单号
 * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统的订单号，与请求一致。
 * 附加数据	attach	否	String(128)	深圳分店	附加数据，原样返回
 * 支付完成时间	time_end	是	String(14)	20141030133525	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
 * 交易状态描述	trade_state_desc	是	String(256)	支付失败，请重新下单支付	对当前查询订单状态的描述和下一步操作的指引
 * <p>
 * Created by blademainer on 16/3/21.
 */
public class OrderQueryResponse extends PayResponseResult {
    @JsonProperty("device_info")
    @JacksonXmlCData
    private String deviceInfo;
    @JsonProperty("openid")
    @JacksonXmlCData
    private String openId;
    @JsonProperty("is_subscribe")
    @JacksonXmlCData
    private String isSubscribe;
    @JsonProperty("trade_type")
    @JacksonXmlCData
    private String tradeType;
    @JsonProperty("trade_state")
    @JacksonXmlCData
    private String tradeState;
    @JsonProperty("bank_type")
    @JacksonXmlCData
    private String bankType;
    @JsonProperty("total_fee")
    @JacksonXmlCData
    private String totalFee;
    @JsonProperty("fee_type")
    @JacksonXmlCData
    private String feeType;
    @JsonProperty("cash_fee")
    @JacksonXmlCData
    private String cashFee;
    @JsonProperty("cash_fee_type")
    @JacksonXmlCData
    private String cashFeeType;
    @JsonProperty("coupon_fee")
    @JacksonXmlCData
    private String couponFee;
    @JsonProperty("coupon_count")
    @JacksonXmlCData
    private String couponCount;
    @JsonProperty("transaction_id")
    @JacksonXmlCData
    private String transactionId;
    @JsonProperty("out_trade_no")
    @JacksonXmlCData
    private String outTradeNo;
    @JsonProperty("attach")
    @JacksonXmlCData
    private String attach;
    @JsonProperty("time_end")
    @JacksonXmlCData
    private String timeEnd;
    @JsonProperty("trade_state_desc")
    @JacksonXmlCData
    private String tradeStatDesc;

    @Override
    public String getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

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

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
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

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeStatDesc() {
        return tradeStatDesc;
    }

    public void setTradeStatDesc(String tradeStatDesc) {
        this.tradeStatDesc = tradeStatDesc;
    }
}
