package rentalcars.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRest {

    @GetMapping("/hello")

    @ResponseBody String sayHello() {return "Hey Universe!";}
}
