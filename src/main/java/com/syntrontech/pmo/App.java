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

/**
 * Hello world!
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {


//        URL url = new URL("https://stackoverflow.com/questions/1051004/how-to-send-put-delete-http-request-in-httpurlconnection");

//        System.out.println(doGett("https://www.youtube.com/watch?v=Q95xanGEgdY&index=27&list=RDR2MnUWLhdhg"));
        String url  = "http://localhost:8080/aaa/forSystem/sync/role";
        String tenant  = "http://localhost:8080/aaa/forSystem/sync/tenant";
        String unit  = "http://localhost:8080/aaa/forSystem/sync/unit";
        String user  = "http://localhost:8080/aaa/forSystem/sync/user";

//        System.out.println(doGett(url));
        System.out.println(doGet(url));
        System.out.println("Hello World!");
    }



    public static String doGett(String url) {


        Client client = ClientBuilder.newClient();
        String entity = null;
        try {

            Response response = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .put(null);

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

    public static int doGet(String url) throws Exception {

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
