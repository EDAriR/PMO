package com.syntrontech.pmo;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {


        String role  = "http://localhost:8080/aaa/forSystem/sync/role";
        String tenant  = "http://localhost:8080/aaa/forSystem/sync/tenant";
        String unit  = "http://localhost:8080/aaa/forSystem/sync/unit";
        String user  = "http://localhost:8080/aaa/forSystem/sync/user";
        String url  = "http://localhost:8080/aaa/forSystem/sync/user";

//        Arrays.stream(ServiceName.values())
//                .map(name -> doRequest(name));

        for (ServiceName name:ServiceName.values()) {
            try {
                doPut(name.geturl());
            } catch (IOException e) {
                logger.debug("sync " + name + " fail");
                throw e;
            }
        }
        System.out.println(doPut2(url));
//        System.out.println(doPut(role));
    }

    static void doRequest(ServiceName name) throws IOException {
        try {
            doPut(name.geturl());
        } catch (IOException e) {
            logger.debug("sync " + name + " fail");
            throw e;
        }
    }


    public static String doPut2(String url) {


        Client client = ClientBuilder.newClient();
        String entity = null;
        try {

            Response response = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(""));

            entity = response.readEntity(String.class);

            if (response.getStatus() == 400) {

                int status = response.getStatus();
                String errorMessage = "sync error 400 [" + entity + "]";

                logger.error(errorMessage);

            }else if(response.getStatus() != 200) {
                int status = response.getStatus();
                String errorMessage = "sync error  [" + entity + "]";

                logger.error(errorMessage);
            }

            logger.trace("response is =>" + entity);

        } catch (Exception ex) {

        }

        return entity;
    }

    public static int doPut(String url) throws IOException {

        // 太吵
        HttpClient client = new HttpClient();

        PutMethod putMethod = new PutMethod(url);
//        GetMethod getMethod = new GetMethod(url);

        // 设置请求的编码方式
        int statusCode = client.executeMethod(putMethod);

        if (statusCode != HttpStatus.SC_OK) {
            // 打印服务器返回的状态
            System.out.println("Method failed: " + putMethod.getStatusLine());
        }

        return statusCode;
    }
}
