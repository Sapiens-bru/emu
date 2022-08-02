package dns.bvv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class WebServerController {
    @GetMapping("/")
    public Map<String,String> index() {
        Map<String,String> response = new HashMap<>();

        Date currentDate = new Date();
        String serverTime = String.valueOf(currentDate.getTime());

        //response.put("test","test");
        //response.put("clientTimeRequest",clientTime);
        response.put("servertime",serverTime);
        return response;
    }
}
