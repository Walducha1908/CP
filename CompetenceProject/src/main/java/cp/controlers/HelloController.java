package cp.controlers;

import cp.services.HelloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello();
    }
}
