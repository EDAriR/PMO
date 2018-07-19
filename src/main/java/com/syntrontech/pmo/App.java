package com.syntrontech.pmo;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {


//        URL url = new URL("https://stackoverflow.com/questions/1051004/how-to-send-put-delete-http-request-in-httpurlconnection");

        System.out.println(doGet("https://stackoverflow.com/questions/1051004/how-to-send-put-delete-http-request-in-httpurlconnection"));
        System.out.println("Hello World!");
    }

    public static int doGet(String url) throws Exception {

        HttpClient client = new HttpClient();

        PutMethod putMethod = new PutMethod(url);

        // 设置请求的编码方式
        int statusCode = client.executeMethod(putMethod);

        if (statusCode != HttpStatus.SC_OK) {
            // 打印服务器返回的状态
            System.out.println("Method failed: " + putMethod.getStatusLine());
        }

        return statusCode;
    }
}
