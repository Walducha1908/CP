package cp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HelloService {
    public String sayHello() {
        return "Hello";
    }
}
