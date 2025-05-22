package main_package;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableAsync
//@EnableJpaRepositories("main_package.repository")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan({"main_package.repository", "main_package.controller", "main_package.service"})

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}