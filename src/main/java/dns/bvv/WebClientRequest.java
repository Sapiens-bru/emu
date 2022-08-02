package dns.bvv;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

public final class WebClientRequest {

    static void connect() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Date currentDate = new Date();
        String clientTime = String.valueOf(currentDate.getTime());

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
            System.out.println("Map");
        } else {
            throw new RuntimeException("JSON type must be List or Map");
        }

    }

    public static void main(String[] args) {
        connect();
    }

    private WebClientRequest(){} //make no instance to class

}
