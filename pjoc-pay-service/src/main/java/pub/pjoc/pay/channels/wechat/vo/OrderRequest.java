package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by blademainer on 16/3/21.
 */
@JsonRootName("xml")
public class OrderRequest {
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
     * 设备号	device_info	否	String(32)	013467007045764	终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
     */
    @JsonProperty("device_info")
    @JacksonXmlCData
    private String deviceInfo;
    /**
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     */
    @JsonProperty("nonce_str")
    @JacksonXmlCData
    private String nonceStr;
    /**
     * 签名<p>
     * <pre>
     *     1、
     * 签名算法
     * 签名生成的通用步骤如下：
     * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
     * 特别注意以下重要规则：
     * ◆ 参数名ASCII码从小到大排序（字典序）；
     * ◆ 如果参数的值为空不参与签名；
     * ◆ 参数名区分大小写；
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
     * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。
     * key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     * 举例：
     * 假设传送的参数如下：
     *
     * appid：	wxd930ea5d5a258f4f
     * mch_id：	10000100
     * device_info：	1000
     * body：	test
     * nonce_str：	ibuaiVcKdpRxkhJA
     * 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
     * stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
     * 第二步：拼接API密钥：
     *
     * stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d"
     * sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7"
     * 最终得到最终发送的数据：
     *
     * <xml>
     * <appid>wxd930ea5d5a258f4f</appid>
     * <mch_id>10000100</mch_id>
     * <device_info>1000<device_info>
     * <body>test</body>
     * <nonce_str>ibuaiVcKdpRxkhJA</nonce_str>
     * <sign>9A0A8659F005D6984697E2CA0A9CF3B7</sign>
     * <xml>
     * 微信提供相关接口在线签名验证工具：点击进入。
     * 2、
     * 生成随机数算法
     * 微信支付API接口协议中包含字段nonce_str，主要保证签名不可预测。我们推荐生成随机数算法如下：调用随机数函数生成，将得到的值转换为字符串。
     * 3、
     * 商户证书
     * （1）获取商户证书
     * 微信支付接口中，涉及资金回滚的接口会使用到商户证书，包括退款、撤销接口。商家在申请微信支付成功后，收到的相应邮件后，可以按照指引下载API证书，也可以按照以下路径下载：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->证书下载 。证书文件有四个，分别说明如下：
     * 表4.2：证书文件说明
     *
     * 证书附件	描述	使用场景	备注
     * pkcs12格式
     * (apiclient_cert.p12、	包含了私钥信息的证书文件，为p12(pfx)格式，由微信支付签发给您用来标识和界定您的身份	撤销、退款申请API中调用	windows上可以直接双击导入系统，导入过程中会提示输入证书密码，证书密码默认为您的商户ID（如：10010000）
     * 证书pem格式
     * （apiclient_cert.pem）	从apiclient_cert.p12中导出证书部分的文件，为pem格式，请妥善保管不要泄漏和被他人复制	PHP等不能直接使用p12文件，而需要使用pem，为了方便您使用，已为您直接提供	您也可以使用openssl命令来自己导出：openssl pkcs12 -clcerts -nokeys -in apiclient_cert.p12 -out apiclient_cert.pem
     * 证书密钥pem格式
     * （apiclient_key.pem）	从apiclient_key.pem中导出密钥部分的文件，为pem格式	PHP等不能直接使用p12文件，而需要使用pem，为了方便您使用，已为您直接提供	您也可以使用openssl命令来自己导出：openssl pkcs12 -nocerts -in apiclient_cert.p12 -out apiclient_key.pem
     * CA证书
     * （rootca.pem）	微信支付api服务器上也部署了证明微信支付身份的服务器证书，您在使用api进行调用时也需要验证所调用服务器及域名的真实性	该文件为签署微信支付证书的权威机构的根证书，可以用来验证微信支付服务器证书的真实性	部分工具已经内置了若干权威机构的根证书，无需引用该证书也可以正常进行验证，这里提供给您在未内置所必须根证书的环境中载入使用
     * （2）使用商户证书
     * ◆ apiclient_cert.p12是商户证书文件，除PHP外的开发均使用此证书文件。
     * ◆ 商户如果使用.NET环境开发，请确认Framework版本大于2.0，必须在操作系统上双击安装证书apiclient_cert.p12后才能被正常调用。
     * ◆ 商户证书调用或安装都需要使用到密码，该密码的值为微信商户号（mch_id）
     * ◆ PHP开发环境请使用商户证书文件apiclient_cert.pem和apiclient_key.pem ，rootca.pem是CA证书。
     * 各版本的调用实例请参考微信支付提供的Demo外链。
     *
     * （3）商户证书安全
     * 证书文件不能放在web服务器虚拟目录，应放在有访问权限控制的目录中，防止被他人下载。商户服务器要做好病毒和木马防护工作，不被非法侵入者窃取证书文件。
     * 4、
     * 商户回调API安全
     * 在普通的网络环境下，HTTP请求存在DNS劫持、运营商插入广告、数据被窃取，正常数据被修改等安全风险。商户回调接口使用HTTPS协议可以保证数据传输的安全性。所以微信支付建议商户提供给微信支付的各种回调采用HTTPS协议。请参考：HTTPS搭建指南。
     * </pre>
     */
    @JacksonXmlCData
    private String sign;
    /**
     * 商品描述	body	是	String(128)	Ipad mini  16G  白色	商品或支付单简要描述
     */
    @JacksonXmlCData
    private String body;
    /**
     * 商品详情	detail	否	String(8192)	Ipad mini  16G  白色	商品名称明细列表
     */
    @JacksonXmlCData
    private String detail;
    /**
     * 附加数据	attach	否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    @JacksonXmlCData
    private String attach;
    /**
     * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     */
    @JsonProperty("out_trade_no")
    @JacksonXmlCData
    private String outTradeNo;
    /**
     * 货币类型	fee_type	否	String(16)	CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @JsonProperty("fee_type")
    @JacksonXmlCData
    private String feeType;
    /**
     * 总金额	total_fee	是	Int	888	订单总金额，单位为分，详见支付金额
     */
    @JsonProperty("total_fee")
    @JacksonXmlCData
    private Integer totalFee;
    /**
     * 终端IP	spbill_create_ip	是	String(16)	123.12.12.123	APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     */
    @JsonProperty("spbill_create_ip")
    @JacksonXmlCData
    private String spBillCreateIp;
    /**
     * 交易起始时间	time_start	否	String(14)	20091225091010	订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则<p></p>
     */
    @JsonProperty("time_start")
    private String timeStart;
    /**
     * 交易结束时间	time_expire	否	String(14)	20091227091010
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
     * 注意：最短失效时间间隔必须大于5分钟
     */
    @JsonProperty("time_expire")
    private String timeExpire;
    /**
     * 商品标记	goods_tag	否	String(32)	WXG	商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     */
    @JsonProperty("goods_tag")
    @JacksonXmlCData
    private String goodsTag;
    /**
     * 通知地址	notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     */
    @JsonProperty("notify_url")
    @JacksonXmlCData
    private String notifyUrl;
    /**
     * 交易类型	trade_type	是	String(16)	JSAPI	取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
     */
    @JsonProperty("trade_type")
    @JacksonXmlCData
    private String tradeType;
    /**
     * 商品ID	product_id	否	String(32)	12235413214070356458058	trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
     */
    @JsonProperty("product_id")
    @JacksonXmlCData
    private String productId;
    /**
     * 指定支付方式	limit_pay	否	String(32)	no_credit	no_credit--指定不能使用信用卡支付
     */
    @JsonProperty("limit_pay")
    @JacksonXmlCData
    private String limitPay;
    /**
     * 用户标识	openid	否	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    @JsonProperty("openid")
    @JacksonXmlCData
    private String openId;

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

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", feeType='" + feeType + '\'' +
                ", totalFee=" + totalFee +
                ", spBillCreateIp='" + spBillCreateIp + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeExpire='" + timeExpire + '\'' +
                ", goodsTag='" + goodsTag + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", productId='" + productId + '\'' +
                ", limitPay='" + limitPay + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
