package cc.chilipo.mw.chilipo_online_exam_system.students;

import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents() {
        return "Hello students";
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Student createStudent(@RequestBody Student student) {
        Student object = new Student();
        object.setEmail(student.getEmail());
        object.setPassword(student.getPassword());
        object.setFirstname(student.getFirstname());
        object.setLastname(student.getLastname());
        object.setUsername(student.getUsername());
        object.setPhoneNumber(student.getPhoneNumber());

        return studentRepository.save(object);
    }
}
