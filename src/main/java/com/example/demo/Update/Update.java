package com.example.demo.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Update {
    public static void update(){
        System.out.println("from update");
    }
    
    @GetMapping("/helloworld")
    public String hello(){
        return "Hello World";
    }
}
