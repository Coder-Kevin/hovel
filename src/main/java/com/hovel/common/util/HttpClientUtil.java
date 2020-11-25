package com.hovel.common.util;

import cn.hutool.http.HttpUtil;
import jodd.http.HttpRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpClientUtil {

    private HttpClientUtil() {
    }

    public static String get(String url) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpGet request = new HttpGet(url);
            request.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            response.getEntity().getContentLength();
            OutputStream outputStream = new ByteArrayOutputStream();
            HttpEntity entity = response.getEntity();
            entity.writeTo(outputStream);

            System.out.println("Response encode: " + entity.getContentEncoding());
            System.out.println("Response content length: " + entity.getContentLength());
            System.out.println("Response status line: " + response.getStatusLine());
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                System.out.println(header.getName() + "---" + header.getValue());
            }

            return new String(((ByteArrayOutputStream) outputStream).toByteArray());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
