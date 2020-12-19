package com.hovel.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hovel.common.util.CryptUtils;
import com.hovel.common.util.tls.HttpsUrlConnectionForTLS;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Jdk7HttpsTest {

    public static final String APPID = "";
    public static final String URL = "";
    public static final String ACCESS_KEY = "";
    public static final String REPLACE_STR = "\r\n";

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
        String aesJsonStr;
        aesJsonStr = Objects.requireNonNull(CryptUtils.encrypt(newparams, ACCESS_KEY, x_bigtree_nonce)).replaceAll(REPLACE_STR, "");
        //签名规则
        String signRule = ACCESS_KEY + aesJsonStr + APPID + x_bigtree_nonce;
        //MD5签名(去掉空格和换行)
        String sign = CryptUtils.md5(signRule.replaceAll(REPLACE_STR, ""));
        Map<String, String> param = new HashMap<>();
        param.put("data", URLEncoder.encode(aesJsonStr, "UTF-8"));
        System.out.println("nonce:" + x_bigtree_nonce + ",sign:" + sign);
        System.out.println("sign:" + sign);
        System.out.println("data:" + param.get("data"));
        System.out.println("requestParam:" + JSON.toJSONString(param));


        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=utf8");
        headers.put("x-bigtree-appid", APPID);
        headers.put("x-bigtree-nonce", x_bigtree_nonce);
        headers.put("x-bigtree-sign", sign);

        HttpsUrlConnectionForTLS.doPost(apiUrl, JSON.toJSONString(param), headers);
    }

}
