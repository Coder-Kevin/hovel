package com.hovel.basetest;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class FileDemo {

    /**
     * 字节流uft-8转ISO-8859-1
     */
    @Test
    public void fileCharsetConvert(){
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File("D:\\hello.txt"));
            byte[] inBytes = IOUtils.toByteArray(in);
            System.out.println(new String(inBytes));

            byte[] aBytes = new String(inBytes).getBytes(Charset.forName("ISO-8859-1"));
            System.out.println(new String(aBytes));

            out = new FileOutputStream(new File("D:\\helo.txt"));
            out.write(aBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void convertISOToUtf() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File("D:\\helo.txt"));
            byte[] inBytes = IOUtils.toByteArray(in);
            System.out.println(new String(inBytes));

            System.out.println("-----------------------------------");
            byte[] aBytes = new String(inBytes).getBytes(Charset.forName("UTF-8"));
            System.out.println(new String(aBytes));

            out = new FileOutputStream(new File("D:\\helo00.txt"));
            out.write(aBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
