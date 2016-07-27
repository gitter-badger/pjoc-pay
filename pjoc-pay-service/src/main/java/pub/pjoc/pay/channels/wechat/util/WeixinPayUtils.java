package pub.pjoc.pay.channels.wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import pub.pjoc.pay.channels.wechat.vo.OrderRequest;
import pub.pjoc.pay.channels.wechat.vo.OrderResponse;
import com.xiongyingqi.util.DigestUtils;
import com.xiongyingqi.util.StringUtil;
import com.xiongyingqi.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by blademainer on 16/3/22.
 */
public abstract class WeixinPayUtils {
    private static final Logger logger = LoggerFactory.getLogger(WeixinPayUtils.class);
    public static final String JSAPI = "JSAPI";
    public static final String KEY_PARAMETER_NAME = "key";
    public static final String DEFAULT_ENCODING = "UTF-8";
    private static AtomicInteger seq = new AtomicInteger();
    private static int maxLength = 8;
    private static double maxNumber = Math.pow(10, 8) - 1;
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    public static boolean isNeedOpenId(OrderRequest orderRequest) {
        return orderRequest != null && JSAPI.equals(orderRequest.getTradeType());
    }

    @SuppressWarnings("unchecked")
    public static String sign(Object request, String key, String unSignKey) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(request);
            Map<Object, Object> map = mapper.readValue(json, Map.class);
            map.remove(unSignKey);
            SortedMap<Object, Object> sortedMap = sortMap(map);
            String parameterString = mapToParameterString(sortedMap);
            StringBuilder builder = new StringBuilder(parameterString);
            builder.append("&");
            builder.append(KEY_PARAMETER_NAME);
            builder.append("=");
            builder.append(key);
            String md5 = DigestUtils.md5DigestAsHex(builder.toString().getBytes(Charset.forName(DEFAULT_ENCODING)));
            String result = md5.toUpperCase();
            if (logger.isDebugEnabled()) {
                logger.debug("Signing orderRequest: {} with key: {} and return sign message: {}", request, key, result);
            }
            return result;
        } catch (JsonProcessingException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    public static SortedMap<Object, Object> sortMap(Map<Object, Object> map){
        if (map == null) {
            return null;
        }
        TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
        return treeMap;
    }

    public static String mapToParameterString(Map<Object, Object> map) {
        if (map == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            if(StringUtils.isEmpty(entry.getKey()) || StringUtils.isEmpty(entry.getValue())){
                continue;
            }
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
            builder.append("&");
        }
        String result = builder.substring(0, builder.length() - 1);
        return result;
    }

    public static InputStream xmlHttpPostRequest(String url, Object xmlObject) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String xml = getXml(xmlObject);
        HttpEntity entity = new StringEntity(xml, ContentType.APPLICATION_XML.getMimeType(), DEFAULT_ENCODING);
        if (logger.isDebugEnabled()) {
            logger.debug("Requesting http with post method, xmlObject: {}, post xml: {}", xmlObject, xml);
        }

        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        HttpEntity responseEntity = response.getEntity();
        InputStream content = responseEntity.getContent();
//        String responseBody = StreamUtils.copyToString(content, Charset.forName(DEFAULT_ENCODING));
        return content;
    }

    public static String getXml(Object xmlObject) throws JsonProcessingException {
        String xml = getXmlMapper().writeValueAsString(xmlObject);
        return xml;
    }

    public static XmlMapper getXmlMapper(){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        return xmlMapper;
    }

    public static OrderResponse parseOrderResponse(InputStream inputStream, String payKey) throws IOException {
        XmlMapper mapper = WeixinPayUtils.getXmlMapper();
        OrderResponse orderResponse = mapper.readValue(inputStream, OrderResponse.class);
        if (logger.isDebugEnabled()) {
            logger.debug("Parsing order response... response: {}", orderResponse);
        }
        String sign = WeixinPayUtils.sign(orderResponse, payKey, "sign");
        if (logger.isDebugEnabled()) {
            logger.debug("Parsing order response... response: {} remote sign: {} local sign: {}", orderResponse, orderResponse.getSign(), sign);
        }
        Assert.isTrue(StringUtils.hasText(orderResponse.getSign()) && orderResponse.getSign().equals(sign), "签名错误");
        return orderResponse;
    }


    public static String generateOrderId() {
        try {
            StringBuilder builder = new StringBuilder();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

            String date = simpleDateFormat.format(new Date());
            String seq = nextSeqStr();
            builder.append(date).append(seq);
            return builder.toString();
        } catch (Exception e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            String date = simpleDateFormat.format(new Date());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String orderId = date + uuid;
            logger.error("Generating order id error with message: " + e.getMessage() + " and fast generate order id: " + orderId, e);
            return orderId;
        }
    }

    private static String nextSeqStr() {
        int seq = nextSeq();
        if (seq > maxNumber) {
            seq = 0;
        }
        String seqStr = StringUtil.fillZero(seq, maxLength);

        return seqStr;
    }

    private static int nextSeq() {
        int i = seq.incrementAndGet();
        if (i >= maxNumber) {
            seq.set(0);
        }
        return i;
    }

    public static void main(String[] args) throws JsonProcessingException {
        OrderRequest request = new OrderRequest();
        request.setAppId("wxd930ea5d5a258f4f");
        request.setMchId("10000100");
        request.setDeviceInfo("1000");
        request.setBody("测试");
        request.setNonceStr("ibuaiVcKdpRxkhJA");
        String s = getXml(request);
        System.out.println(s);
    }


}
