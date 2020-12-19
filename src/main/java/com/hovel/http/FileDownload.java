package com.hovel.http;

import cn.hutool.http.HttpUtil;
import com.hovel.common.util.HttpClientUtil;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class FileDownload {

    public static void main(String[] args) {
        String fileUrl = "https://wx.sandbox1.wanbochain.com/wx/attachment/ac5c0c88-b5c1-4946-b5eb-6780568c4b51";

        String s = HttpClientUtil.get(fileUrl);
        assert s != null;
        System.out.println(s.length());

        byte[] bytes = HttpUtil.downloadBytes(fileUrl);
        System.out.println(bytes.length);


        HttpResponse response;
        try {
            response = HttpRequest.get(fileUrl).send();
            if (response.statusCode() != 200) {
                log.error("文件下载失败");
                throw new RuntimeException("文件下载失败,url:" + fileUrl);
            }

            String fileName = null;

            String contentDispositionValue = response.header("content-disposition");
            if (StringUtils.isNotEmpty(contentDispositionValue)) {
                fileName = contentDispositionValue.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
            }

            if (StringUtils.isEmpty(fileName)) {
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                if (fileName.contains("?")) {
                    fileName = fileName.substring(0, fileName.indexOf("?"));
                }
            }
         } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
