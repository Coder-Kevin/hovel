package com.hovel.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hovel.common.util.CryptUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 */
public class HttpsUrlConnectionForTLS {

    public static final String APPID = "";
    public static final String URL = "";
    public static final String ACCESS_KEY = "";
    public static final String REPLACE_STR = "\r\n";

    public HttpURLConnection createConnection(URI uri) throws IOException {
        URL url = uri.toURL();
        URLConnection connection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
        httpsURLConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
        return httpsURLConnection;
    }

    public static void main(String[] args) throws Exception {

        String apiUrl = URL;
        /*接口参数包装*/
        String json = "json body";
        Map paramMap = JSONObject.parseObject(json, Map.class);

        String newparams = JSON.toJSONString(paramMap);
        System.out.println(apiUrl);

        /*每次接口调用生成随机加密字符串*/
        String x_bigtree_nonce = CryptUtils.makeRandomStr(16);

        //AES加密(去掉空格和换行)
        String aesJsonStr = CryptUtils.encrypt(newparams, ACCESS_KEY, x_bigtree_nonce).replaceAll(REPLACE_STR, "");
        //签名规则
        String signRule = ACCESS_KEY + aesJsonStr + APPID + x_bigtree_nonce;
        //MD5签名(去掉空格和换行)
        String sign = CryptUtils.md5(signRule.replaceAll(REPLACE_STR, ""));
        Map<String, String> param = new HashMap<String, String>();
        param.put("data", URLEncoder.encode(aesJsonStr, "UTF-8"));
        System.out.println("nonce:" + x_bigtree_nonce + ",sign:" + sign);
        System.out.println("sign:" + sign);
        System.out.println("data:" + param.get("data"));
        System.out.println("requestParam:" + JSON.toJSONString(param));


        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=utf8");
        headers.put("x-bigtree-appid", APPID);
        headers.put("x-bigtree-nonce", x_bigtree_nonce);
        headers.put("x-bigtree-sign", sign);
        headers.put("Content-Type", "application/json;charset=utf-8");

        doPost(apiUrl, JSON.toJSONString(param), headers);
    }

    private static void doPost(String url, String body, Map<String, String> headers) {
        HttpsUrlConnectionForTLS httpsUrlConnectionMessageSender = new HttpsUrlConnectionForTLS();
        HttpURLConnection connection;
        try {
            connection = httpsUrlConnectionMessageSender.createConnection(new URI(url));
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    connection.setRequestProperty(key, headers.get(key));
                }
            }

            connection.connect();
            //POST请求
            OutputStreamWriter os = null;
            String json = "";
            os = new OutputStreamWriter(connection.getOutputStream());
            os.write(body);
            os.flush();
            json = getResponse(connection);
            System.out.println(json);

            if (connection != null) {
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String getResponse(HttpURLConnection Conn) throws IOException {
        InputStream is;
        if (Conn.getResponseCode() >= 400) {
            is = Conn.getErrorStream();
        } else {
            is = Conn.getInputStream();
        }
        String response = "";
        byte buff[] = new byte[512];
        int b = 0;
        while ((b = is.read(buff, 0, buff.length)) != -1) {
            response += new String(buff, 0, b);

        }
        is.close();
        return response;
    }
}
