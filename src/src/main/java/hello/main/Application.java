
package hello.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"hello.tasks", 
"hello.startup", 
"hello.business",
"hello.core",
"hello.data",
"hello.controllers"})
@EnableMongoRepositories ({"hello.data", "hello.core"}) 
@EnableScheduling
@EnableAutoConfiguration

public class Application {
	public static void main(String[] args) {		
		SpringApplication.run(Application.class, args);
	}
}
