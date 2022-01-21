package cc.chilipo.mw.chilipo_online_exam_system.students;

import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import cc.chilipo.mw.chilipo_online_exam_system.courses.CourseRepository;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.Lecture;
import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents() {
        return "Hello students";
    }

    //viewing student data
    @RequestMapping(value="/students/{id}",method = RequestMethod.GET)
    public Optional<Student> getStudent(@PathVariable Long id)
    {
        return studentRepository.findById(id);
    }

    //student registering
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
    //updating student fields
    @Transactional
    @RequestMapping(value="/students",method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student student)
    {
        return studentRepository.save(student);
    }
    /// student regisreing courses
    @RequestMapping(value="students/register_course/{student_id}/{course_id}", method=RequestMethod.PUT)
    public Course student_register_course(@PathVariable Long student_id,@PathVariable Long course_id)
    {
        Student _student = studentRepository.findById(student_id).get();
       Course   _course = courseRepository.findById(course_id).get();
       _course.enrollStudent(_student);
        return courseRepository.save(_course);

    }



}