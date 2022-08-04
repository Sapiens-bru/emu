package dns.bvv;

import io.netty.util.internal.logging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.client.WebClient;


import java.util.*;
import java.util.concurrent.TimeUnit;

public final class WebClientRequest {

    private static WebClient client;

    private static void connect() {

        client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    private static void sendRequest(){

        Date currentDate = new Date();
        Long clientTime1 = currentDate.getTime();

        Object response = client
                .get()
//                .uri(uriBuilder -> uriBuilder
//                        .queryParam("clienttime",clientTime)
//                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        if(response instanceof List){
            System.out.println("List");
        } else if (response instanceof Map) {
            Long clientTime2 = currentDate.getTime();
            Map<String,String> responseMap = (Map) response;
            Long servertime = Long.parseLong(responseMap.get("servertime"));
            //System.out.println("c1="+clientTime1+" c2="+clientTime2+" s="+responseMap.get("servertime"));
            System.out.println("c2-c1= "+(clientTime2-clientTime1)+" c2-s= "+(clientTime2-servertime));

        } else {
            throw new RuntimeException("JSON type must be List or Map");
        }

    }

    public static void runTest(){

    //    InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.INSTANCE);
        connect();
        Random rnd = new Random();
        while (true){
            sendRequest();
            try {
                TimeUnit.MILLISECONDS.sleep(5000+ rnd.nextInt(20000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private WebClientRequest(){} //make no instance to class

    public static void main(String[] args) {

        runTest();
    }
}
