package com.hovel.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.net.Socket;

@Slf4j
public class ElementalHttpPost {

    public static void main(String[] args) throws Exception {
        HttpProcessor httpProc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("Test/1.1"))
                .add(new RequestExpectContinue(true)).build();

        HttpRequestExecutor httpExecutor = new HttpRequestExecutor();

        HttpCoreContext coreContext = HttpCoreContext.create();
        HttpHost host = new HttpHost("localhost", 8080);
        coreContext.setTargetHost(host);

        try (DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024)) {
            ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

            HttpEntity[] requestBodies = {
                    new StringEntity(
                            "This is the first test request",
                            ContentType.create("text/plain", Consts.UTF_8)),
                    new ByteArrayEntity(
                            "This is the second test request".getBytes(Consts.UTF_8),
                            ContentType.APPLICATION_OCTET_STREAM),
                    new InputStreamEntity(
                            new ByteArrayInputStream(
                                    "This is the third test request (will be chunked)"
                                            .getBytes(Consts.UTF_8)),
                            ContentType.APPLICATION_OCTET_STREAM)
            };

            for (HttpEntity requestBody : requestBodies) {
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket);
                }
                BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST",
                        "/servlets-examples/servlet/RequestInfoExample");
                request.setEntity(requestBody);
                log.info(">> Request URI: " + request.getRequestLine().getUri());

                httpExecutor.preProcess(request, httpProc, coreContext);
                HttpResponse response = httpExecutor.execute(request, conn, coreContext);
                httpExecutor.postProcess(response, httpProc, coreContext);

                log.info("<< Response: " + response.getStatusLine());
                log.info(EntityUtils.toString(response.getEntity()));
                log.info("==============");
                if (!connStrategy.keepAlive(response, coreContext)) {
                    conn.close();
                } else {
                    log.info("Connection kept alive...");
                }
            }
        }
    }

}
