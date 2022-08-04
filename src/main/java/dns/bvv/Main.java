package dns.bvv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        for (String arg:args) {
            if (arg.equalsIgnoreCase("-c")) {
                //client

                WebClientRequest.runTest();
                break;
            } else if (arg.equalsIgnoreCase("-s")) {
                //server
                SpringApplication.run(Main.class, args);
                break;
            }
        }
    }
}