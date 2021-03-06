package com.hovel.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import javax.net.ssl.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * @author Kevin
 */
public class HttpsUtil {

    /**
     * 请求超时时间
     */
    private static final int TIME_OUT = 120000;

    /**
     * Https请求
     */
    private static final String HTTPS = "https";

    /**
     * 返回成功状态码
     */
    private static final int OK = 200;

    /**
     * 纯字符串参数post请求
     *
     * @param url      请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return 服务器返回内容
     * @throws Exception
     */
    public static String post(String url, Map<String, String> paramMap) throws Exception {
        Response response = doPostRequest(url, paramMap, null);
        return response.body();
    }

    /**
     * 带上传文件的post请求
     *
     * @param url      请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap  请求文件参数集合
     * @return 服务器返回内容
     * @throws Exception
     */
    public static String postFile(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws Exception {
        Response response = doPostRequestFile(url, paramMap, fileMap);
        return response.body();
    }


    public static String post(String url, Map<String, String> paramMap, Map<String, String> headers) throws Exception {
        Response response = doPostRequest(url, paramMap, headers);
        return response.body();
    }

    /**
     * 执行post请求
     *
     * @param url      请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap  请求文件参数集合
     * @return 服务器相应对象
     * @throws Exception
     */
    public static Response doPostRequestFile(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws Exception {
        if (StringUtils.isEmpty(url)) {
            throw new Exception("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.POST);
        connection.timeout(TIME_OUT);
        connection.header("Content-Type", "multipart/form-data");
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);

        // 添加字符串类参数
        if (null != paramMap && !paramMap.isEmpty()) {
            connection.data(paramMap);
        }

        // 添加文件参数
        if (null != fileMap && !fileMap.isEmpty()) {
            InputStream in = null;
            File file = null;
            Set<Map.Entry<String, File>> set = fileMap.entrySet();
            try {
                for (Map.Entry<String, File> e : set) {
                    file = e.getValue();
                    in = new FileInputStream(file);
                    connection.data(e.getKey(), file.getName(), in);
                }
            } catch (FileNotFoundException e) {
                throw new Exception(e.getMessage());
            }
        }

        try {
            Response response = connection.execute();
            if (response.statusCode() != OK) {
                throw new Exception(response.statusMessage());
            }
            return response;
        } catch (IOException e) {
            throw new Exception(e);
        }
    }


    /**
     * 执行post请求
     *
     * @param url      请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param headers  请求文件参数集合
     * @return 服务器相应对象
     * @throws Exception
     */
    public static Response doPostRequest(String url, Map<String, String> paramMap, Map<String, String> headers) throws Exception {
        if (StringUtils.isEmpty(url)) {
            throw new Exception("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.POST);
        connection.timeout(TIME_OUT);
        connection.header("Content-Type", "application/json;charset=UTF-8");
        connection.headers(headers);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);

        // 添加字符串类参数
        if (null != paramMap && !paramMap.isEmpty()) {
//            connection.data(paramMap);

            connection.requestBody(JSONObject.toJSONString(paramMap));
        }

        try {
            Response response = connection.execute();
            if (response.statusCode() != OK) {
                throw new Exception(response.statusMessage());
            }
            return response;
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取服务器信任
     */
    private static void getTrust() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
