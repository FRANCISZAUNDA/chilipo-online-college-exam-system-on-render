package cc.chilipo.mw.chilipo_online_exam_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ChilipoOnlineExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChilipoOnlineExamSystemApplication.class, args);
	}

}
@RestController
class welcome_api{
	@GetMapping("/chilipo")
	String hello(){
		return "hello world";
	}
}
