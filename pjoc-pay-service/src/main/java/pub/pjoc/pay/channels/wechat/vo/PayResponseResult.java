package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by blademainer on 16/3/21.
 */
@JsonRootName("xml")
public class PayResponseResult extends ReturnResult{

    // 以下字段在return_code为SUCCESS的时候有返回

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
     * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
     */
    @JsonProperty("result_code")
    @JacksonXmlCData
    private String resultCode;
    //    名称	    描述	            原因	                解决方案
    //    NOAUTH	商户无此接口权限	商户未开通此接口权限	请商户前往申请此接口权限
    //    NOTENOUGH	余额不足	用户帐号余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付
    //    ORDERPAID	商户订单已支付	商户订单已支付，无需重复操作	商户订单已支付，无需更多操作
    //    ORDERCLOSED	订单已关闭	当前订单已关闭，无法支付	当前订单已关闭，请重新下单
    //    SYSTEMERROR	系统错误	系统超时	系统异常，请用相同参数重新调用
    //    APPID_NOT_EXIST	APPID不存在	参数中缺少APPID	请检查APPID是否正确
    //    MCHID_NOT_EXIST	MCHID不存在	参数中缺少MCHID	请检查MCHID是否正确
    //    APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	appid和mch_id不匹配	请确认appid和mch_id是否匹配
    //    LACK_PARAMS	缺少参数	缺少必要的请求参数	请检查参数是否齐全
    //    OUT_TRADE_NO_USED	商户订单号重复	同一笔交易不能多次提交	请核实商户订单号是否重复提交
    //    SIGNERROR	签名错误	参数签名结果不正确	请检查签名参数和方法是否都符合签名算法要求
    //    XML_FORMAT_ERROR	XML格式错误	XML格式错误	请检查XML参数格式是否正确
    //    REQUIRE_POST_METHOD	请使用post方法	未使用post传递参数 	请检查请求参数是否通过post方法提交
    //    POST_DATA_EMPTY	post数据为空	post数据不能为空	请检查post数据是否为空
    //    NOT_UTF8	编码格式错误	未使用指定编码格式	请使用NOT_UTF8编码格式
    /**
     * 错误代码	err_code	否	String(32)	SYSTEMERROR	详细参见第6节错误列表
     */
    @JsonProperty("err_code")
    @JacksonXmlCData
    private String errCode;
    /**
     * 错误代码描述	err_code_des	否	String(128)	系统错误	错误返回的信息描述
     */
    @JsonProperty("err_code_des")
    @JacksonXmlCData
    private String errCodeDes;

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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    @Override
    public String toString() {
        return "PayResponseResult{" +
                "appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errCodeDes='" + errCodeDes + '\'' +
                "} " + super.toString();
    }
}
