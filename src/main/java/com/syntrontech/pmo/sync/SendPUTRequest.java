package com.syntrontech.pmo.sync;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class SendPUTRequest {

    private static Logger logger = LoggerFactory.getLogger(SendPUTRequest.class);


    public void sendPUTRequest() {
        for (ServiceName name : ServiceName.values()) {

            try {
                doPut2(name.geturl());
                System.out.println("sync successful =>" + name);
            } catch (Exception e) {
                logger.error("send request " + name + " fail ");
            }
        }
    }

    public void sendPUTRequest(ServiceName servicename) {

        try {
            doPut2(servicename.geturl());
            System.out.println("sync successful =>" + servicename);
        } catch (Exception e) {
            logger.error("send request " + servicename + "fail ");
        }

    }


    protected String doPut2(String url) {

        Client client = ClientBuilder.newClient();
        String entity = null;

        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));

        entity = response.readEntity(String.class);

        if (response.getStatus() == 400) {

            int status = response.getStatus();
            String errorMessage = "sync error 400 [" + entity + "]";

//            System.out.println("status:" + status + ", " + errorMessage);
            logger.error(errorMessage);

        } else if (response.getStatus() != 200) {
            int status = response.getStatus();
            String errorMessage = "sync error  [" + entity + "]";

//            System.out.println("status:" + status + ", " + errorMessage);
            logger.error(errorMessage);
        }
//            logger.trace("response is =>" + entity);

        return entity;
    }

}
