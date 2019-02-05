package model;

import controller.RequestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = RequestController.class)
public class SocialNetowrkMain {

    public static void main (String args[]){
        SpringApplication.run(SocialNetowrkMain.class, args);
    }

}
