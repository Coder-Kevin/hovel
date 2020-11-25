package com.hovel.common.util.tls;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Map;

/**
 * @author Kevin
 */
public class HttpsUrlConnectionForTLS {

    public HttpURLConnection createConnection(URI uri) throws IOException {
        URL url = uri.toURL();
        URLConnection connection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
        httpsURLConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
        return httpsURLConnection;
    }

    public static void doPost(String url, String body, Map<String, String> headers) {
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
