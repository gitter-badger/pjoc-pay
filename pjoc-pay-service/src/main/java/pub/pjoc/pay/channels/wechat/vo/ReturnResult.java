package pub.pjoc.pay.channels.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 基本参数
 * Created by blademainer on 16/3/27.
 */
@JsonRootName("xml")
public class ReturnResult {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    /**
     * 返回状态码	return_code	是	String(16)	SUCCESS
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @JsonProperty("return_code")
    @JacksonXmlCData
    private String returnCode;
    /**
     * 返回信息	return_msg	否	String(128)	签名失败
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    @JsonProperty("return_msg")
    @JacksonXmlCData
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @Override
    public String toString() {
        return "ReturnResult{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                '}';
    }
}
