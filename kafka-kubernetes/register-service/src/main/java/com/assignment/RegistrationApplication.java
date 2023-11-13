package com.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RegistrationApplication {
    public static void main(String[] args){
        SpringApplication.run(RegistrationApplication.class , args);
    }

//    @Bean
//    public CommandLineRunner loadData(UserRepository repository) {
//        return (args) -> {
//            // save a couple of Users
//            repository.save(new User("Jack", "Bauer" , "12"));
//            repository.save(new User("Chloe", "O'Brian", "10"));
//            repository.save(new User("Kim", "Bauer","15"));
//            repository.save(new User("David", "Palmer","9"));
//            repository.save(new User("Michelle", "Dessler","16"));
//
//            // fetch all customers
//            log.info("users found with findAll():");
//            log.info("-------------------------------");
//            for (User user : repository.findAll()) {
//                log.info(user.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Optional user = repository.findById(1L);
//            log.info("user found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(user.get().toString());
//            log.info("");
//
//        };
//    }
}
