package dns.bvv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        for (String arg:args) {
            if (arg.equalsIgnoreCase("-c")) {
                //client
                WebClientRequest.connect();
                break;
            } else if (arg.equalsIgnoreCase("-s")) {
                //server
                SpringApplication.run(Main.class, args);
                break;
            }
        }
    }
}