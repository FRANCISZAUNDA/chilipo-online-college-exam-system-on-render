package cc.chilipo.mw.chilipo_online_exam_system.admin;

import cc.chilipo.mw.chilipo_online_exam_system.lectures.Lecture;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.LectureRepository;
import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import cc.chilipo.mw.chilipo_online_exam_system.courses.CourseRepository;
import cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import cc.chilipo.mw.chilipo_online_exam_system.departments.DepartmentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.applet.Applet;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private  LectureRepository lectureRepository;
    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private  ExamRepository     examRepository;


    //--------functions----------what can admin do to himself
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmins() {
        return "Hello adminn";
    }
///admin creating himself
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Admin createAdmin(@RequestBody Admin admin) {
        Admin object = new Admin();
        object.setEmail(admin.getEmail());
        object.setPassword(admin.getPassword());
        object.setFirstname(admin.getFirstname());
        object.setLastname(admin.getLastname());
        object.setUsername(admin.getUsername());
        object.setPhoneNumber(admin.getPhoneNumber());
        object.setAge(admin.getAge());

        return adminRepository.save(object);
    }
//admin updating himself/herself
@RequestMapping(value="/admin",method = RequestMethod.PUT)
public Admin updateAdmin(@RequestBody Admin admin)
{
    return adminRepository.save(admin);
}
    //--------------Admin functions with lectures---------------------------
    //the code below is for welcome interface of registering lectures might be changed later
    @RequestMapping(value = "/admin/manage_lectures", method = RequestMethod.GET)
    public String getLectures() {
        return "please register lectures";
    }


/*the admin will register lecture set them all fields including password and give login info to the
lecture via face to face,email,sms or call but the lecture will be advised to change the password this is to
not let anyone register as a lecture
 */
    @RequestMapping(value = "/admin/manage_lectures", method = RequestMethod.POST)
    public Lecture createLecture(@RequestBody Lecture lecture) {
        Lecture object = new Lecture();
        object.setEmail(lecture.getEmail());
        object.setPassword(lecture.getPassword());
        object.setFirstname(lecture.getFirstname());
        object.setLastname(lecture.getLastname());
        object.setUsername(lecture.getUsername());
        object.setPhoneNumber(lecture.getPhoneNumber());
        object.setAge(lecture.getAge());

        return lectureRepository.save(object);
    }



    // this is for deleting lectures
    @RequestMapping(value = "admin/manage_lectures/{id}",method = RequestMethod.DELETE)
    public  String removeLecture(@PathVariable Long id)
    {
        Lecture a = lectureRepository.getOne(id);
        lectureRepository.delete(a);
        return "deleted successfully";
    }

//---------------end of lecture functions-----------------------------------------------------

//------------------Admin functions with courses-----------------------------------------------

    //this will help admin register courses
    @RequestMapping(value = "admin/manage_courses/{course_name}/{dept_id}", method = RequestMethod.PUT)
    public Course createCourse(@PathVariable String course_name, @PathVariable Long dept_id){
        Course object =new Course();
        object.setCourse_name(course_name);
        object.setDepartment(departmentRepository.findById(dept_id).get());
      return   courseRepository.save(object);
    }

    // this is for deleting courses
    @RequestMapping(value = "admin/manage_courses/{id}",method = RequestMethod.DELETE)
    public  String removeCourse(@PathVariable Long id)
    {
        Course a = courseRepository.getOne(id);
        courseRepository.delete(a);
        return "deleted successfully";
    }
 //---------------end of Admin Functions with courses---------------------------------------------   //

 //--------------Admin functions with Department-----------------------------------------------------
    //this is for admin to register a department
 @RequestMapping(value = "admin/manage_departments", method = RequestMethod.POST)
    public Department registerDepartment(@RequestBody Department department) {
        Department object = new Department();
        object.setDept_name(department.getDept_name());
        object.setDean(department.getDean());
        object.setBuilding(department.getBuilding());
        object.setRoom(department.getRoom());

        return departmentRepository.save(object);
    }

    // this to delete departments
    @RequestMapping(value = "admin/manage_departments/delete/{dept_id}",method = RequestMethod.DELETE)
    public  String removeDepartments(@PathVariable Long dept_id)
    {
        Department a = departmentRepository.getOne(dept_id);
        departmentRepository.delete(a);
        return "department deleted successfully";
    }
 //----------------end of Admin Functions with Department----------------------------------


    ////   //-------------Admin functions with Students-------------------
   @RequestMapping(value = "admin/manage_students/{id}",method =RequestMethod.DELETE)
    public  String removeStudents(@PathVariable Long id)
    {
        Student a = studentRepository.getOne(id);
        studentRepository.delete(a);

        return "Student deleted successfully";
    }

    //function for admin to get a particular student
    @RequestMapping(value = "admin/manage_students/{id}",method =RequestMethod.GET)
    public  Student getStudent(@PathVariable Long id)
    {
        Student a = studentRepository.findById(id).get();


        return a;
    }

    //admin to get a list of all studnents
    @RequestMapping(value = "admin/manage_students",method =RequestMethod.GET)
    public List<Student> getStudents()
    {
        return studentRepository.findAll();

    }
 //-----------------------end of Admin Functions with Students----------------------------------
    //

 //-----------------------Admin Functions with exams--------------------------------------------

    //admin post exams based on exam id
    @RequestMapping(value = "/admin/post_exam/{id}", method = RequestMethod.GET)
 public Optional<Exam> getExam(@PathVariable Long id)
 {
     return examRepository.findById(id);

 }

}
