package com.hovel.http;

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

    public static void main(String[] args) {
        String result = get("http://localhost:8046/user/Kevin");
        System.out.println(result);
    }

    public static String get(String url) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpGet request = new HttpGet(url);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            response.getEntity().getContentLength();
            OutputStream outputStream = new ByteArrayOutputStream();
            HttpEntity entity = response.getEntity();
            entity.writeTo(outputStream);

            System.out.println("Response encode: " + entity.getContentEncoding());
            System.out.println("Response content length: " + entity.getContentLength());
            System.out.println("Response status line: " + response.getStatusLine());

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
