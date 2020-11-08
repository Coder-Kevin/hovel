package com.hovel.http;

import com.hovel.common.util.HttpClientUtil;

public class HttpClientTest {

    private HttpClientTest() {
    }

    public static void main(String[] args) {
        String result = HttpClientUtil.get("http://localhost:8046/user/Kevin");
        System.out.println(result);
    }
}
