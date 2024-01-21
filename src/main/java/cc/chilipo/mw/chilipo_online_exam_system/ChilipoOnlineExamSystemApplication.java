package cc.chilipo.mw.chilipo_online_exam_system;

import cc.chilipo.mw.chilipo_online_exam_system.admin.Admin;
import cc.chilipo.mw.chilipo_online_exam_system.admin.AdminController;
import cc.chilipo.mw.chilipo_online_exam_system.admin.AdminRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ChilipoOnlineExamSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChilipoOnlineExamSystemApplication.class, args);
	}
    @Autowired
	private AdminController adminController;
	@Autowired
	private AdminRepository adminRepository;
	@Override
	public void run(String... args) throws Exception
	{

		Admin defaultAdmin =new Admin();
		defaultAdmin.setAge(23);
		defaultAdmin.setFirstname("myAdmin");
		defaultAdmin.setLastname("thisAdmin");
		defaultAdmin.setPhoneNumber("099678945");
		defaultAdmin.setUsername("cantGuessme");
		defaultAdmin.setEmail("MyAdmin@chilipo.com");
		defaultAdmin.setPassword("THISWEB");
		adminRepository.save(defaultAdmin);

	}
}



