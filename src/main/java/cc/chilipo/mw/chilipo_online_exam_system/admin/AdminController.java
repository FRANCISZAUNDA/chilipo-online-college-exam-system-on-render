package cc.chilipo.mw.chilipo_online_exam_system.admin;

import cc.chilipo.mw.chilipo_online_exam_system.fakeCourseClass.fakeCourseclass;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.Lecture;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.LectureRepository;
import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import cc.chilipo.mw.chilipo_online_exam_system.courses.CourseRepository;
import cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import cc.chilipo.mw.chilipo_online_exam_system.departments.DepartmentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.StudentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    private ExamRepository examRepository;


    //--------functions----------what can admin do to himself
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmins() {
        return "Hello adminn";
    }
///admin creating himself
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public String createAdmin(@ModelAttribute ("admin")  Admin admin) {
        Admin object = new Admin();
        object.setEmail(admin.getEmail());
        object.setPassword(admin.getPassword());
        object.setFirstname(admin.getFirstname());
        object.setLastname(admin.getLastname());
        object.setUsername(admin.getUsername());
        object.setPhoneNumber(admin.getPhoneNumber());
        object.setAge(admin.getAge());
        adminRepository.save(object);
        return "AdminLogin";
    }

    //get registration form
    @RequestMapping(value = "/admin/register", method = RequestMethod.GET)
    public String getAdminRegisterForm(Model model)
    {
        Admin admin = new Admin();
        model.addAttribute("admin",admin);
        return "AdminRegister";
    }

    //List all Admins

//admin updating himself/herself
//@RequestMapping(value="/admin",method = RequestMethod.PUT)
//admin update himself
@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
public String updateAdmin(@ModelAttribute("admin") Admin admin ,@RequestParam(name="id") Long id){
    Admin admin1 =adminRepository.getOne(id);
    admin1.setEmail(admin.getEmail());
    admin1.setPassword(admin.getPassword());
    admin1.setFirstname(admin.getFirstname());
    admin1.setLastname(admin.getLastname());
    admin1.setUsername(admin.getUsername());
    admin1.setPhoneNumber(admin.getPhoneNumber());
    admin1.setAge(admin.getAge());
    adminRepository.save(admin1);
    return "redirect:/admin/ListAdminis";
}


// List all Admins
@RequestMapping(value = "/admin/ListAdminis", method = RequestMethod.GET)
public String ListAllAdmins(Model model)
{
    List<Admin> admins=adminRepository.findAll();
    model.addAttribute("admins",admins);
    return "ListOfAdmins";
}
    //get admin update html form
    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public String getAdminUpdateHimselfForm(Model model,@PathVariable long id) {
        Admin admin =adminRepository.getOne(id);
        model.addAttribute("admins",admin);

        return "AdminUpdateHimself";
    }
    //deleting admins
    @RequestMapping(value = "/admin/del/{id}",method = RequestMethod.GET)
    public  String removeAdmin(@PathVariable Long id,@ModelAttribute("admins") Admin admin)
    {
        Admin a = adminRepository.getOne(id);
        adminRepository.delete(a);
        return "redirect:/admin/ListAdminis";
    }




    //--------------Admin functions with lectures---------------------------
    //function to get the registration form for lectures
    @RequestMapping(value = "/admin/registerLectures" ,method=RequestMethod.GET)
    public String getLectures_reg_form(Model model)
    {
        Lecture lecture = new Lecture();
        model.addAttribute("lecture",lecture);

        return "AdminRegisterLecture";
    }





/*the admin will register lecture set them all fields including password and give login info to the
lecture via face to face,email,sms or call but the lecture will be advised to change the password this is to
not let anyone register as a lecture
 */
    @RequestMapping(value = "/admin/registerLectures", method = RequestMethod.POST)
    public String createLecture(@ModelAttribute("lecture") Lecture lecture) {
        Lecture object = new Lecture();
        object.setEmail(lecture.getEmail());
        object.setPassword(lecture.getPassword());
        object.setFirstname(lecture.getFirstname());
        object.setLastname(lecture.getLastname());
        object.setUsername(lecture.getUsername());
        object.setPhoneNumber(lecture.getPhoneNumber());
        object.setAge(lecture.getAge());
        lectureRepository.save(object);
        return "AdminRegisterLecture";
    }

    //



    // this is for deleting lectures
    @RequestMapping(value = "/admin/manage_lectures/del/{id}",method = RequestMethod.DELETE)
    public  String removeLecture(@PathVariable Long id)
    {
        Lecture a = lectureRepository.getOne(id);
        lectureRepository.delete(a);
        return "deleted successfully";
    }
    @RequestMapping(value = "/admin/manage_lectures/del/{id}",method = RequestMethod.GET)
    public  String removeLecture(@PathVariable Long id,@ModelAttribute("lectures") Lecture lecture)
    {
        Lecture a = lectureRepository.getOne(id);
        lectureRepository.delete(a);
        return "redirect:/admin/ListLectures";
    }




    //lIST ALL LECTURES
    @RequestMapping(value = "admin/ListLectures", method = RequestMethod.GET)
    public String ListAllLectures(Model model)
    {
        List<Lecture> lectures=lectureRepository.findAll();
        model.addAttribute("lectures",lectures);
        return "ListOfLecture";
    }

    //to get get update form for lecture
    @RequestMapping(value = "/admin/manage_lectures/update/{id}", method = RequestMethod.GET)
    public String getUpdateLectureForm(Model model,@PathVariable long id) {
        // long pop=1;
        Lecture lecture =lectureRepository.getOne(id);
        model.addAttribute("lectures",lecture);

        return "AdminUpdateLecture";
    }

    // update lecture
    @RequestMapping(value = "/admin/manage_lectures/updateLec", method = RequestMethod.POST)
    public String updateLecture2(@ModelAttribute("lecture") Lecture lecture , @RequestParam(name="id") Long id){
        Lecture _Lecture =lectureRepository.getOne(id);
        _Lecture.setEmail(lecture.getEmail());
        _Lecture.setPassword(lecture.getPassword());
        _Lecture.setFirstname(lecture.getFirstname());
        _Lecture.setLastname(lecture.getLastname());
        _Lecture.setUsername(lecture.getUsername());
        _Lecture.setPhoneNumber(lecture.getPhoneNumber());
        _Lecture.setAge(lecture.getAge());
        lectureRepository.save(_Lecture);
        return "redirect:/admin/ListLectures";
    }



//---------------end of lecture functions-----------------------------------------------------

//------------------Admin functions with courses-----------------------------------------------

    //this will help admin register courses
    @RequestMapping(value = "/admin/manage_courses", method = RequestMethod.POST)
    public String createCourse(@ModelAttribute("fakeCourseclass") fakeCourseclass _fkcourse){
        Course object =new Course();
        object.setCourse_name(_fkcourse.getCourse_name());
        object.setDepartment(departmentRepository.getOne(_fkcourse.getDept_id()));
        courseRepository.save(object);
        return "redirect:/admin/manage_departments";
    }

    //this will help admin update courses
    @RequestMapping(value = "/admin/manage_courses/updateCourse", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("course") Course course ,@RequestParam(name="course_id") Long course_id){
        Course object =courseRepository.getOne(course_id);
        object.setCourse_name(course.getCourse_name());
        object.setDepartment(departmentRepository.getOne(course.getDepartment().getDept_id()));
        courseRepository.save(object);
        return "redirect:/admin/coursesList";
    }


    // this is for deleting courses
    @RequestMapping(value = "/admin/manage_courses/del/{course_id}",method = RequestMethod.GET)
    public  String removeCourse(@PathVariable Long course_id,@ModelAttribute("course") Course course)
    {
        Course a = courseRepository.getOne(course_id);
        courseRepository.delete(a);
        return "redirect:/admin/coursesList";
    }


    // admin List all courses
    @RequestMapping(value = "/admin/coursesList", method = RequestMethod.GET)
    public String listAllCourses(Model model)
    {
        List<Course> courses=courseRepository.findAll();
        model.addAttribute("courses",courses);
        return "ListOfcourses";


    }

    @RequestMapping(value = "/admin/manage_courses/update/{course_id}", method = RequestMethod.GET)
    public String getUpdateCourseForm(Model model,@PathVariable long course_id) {
        // long pop=1;
        Course course =courseRepository.getOne(course_id);
        model.addAttribute("course",course);

        return "adminUpdateCourse";
    }

 //---------------end of Admin Functions with courses---------------------------------------------   //

 //--------------Admin functions with Department-----------------------------------------------------
    //this is for admin to register a department
 @RequestMapping(value = "/admin/manage_departments", method = RequestMethod.POST)
    public String registerDepartment(@ModelAttribute("department") Department department) {
        Department object = new Department();
        object.setDept_name(department.getDept_name());
        object.setDean(department.getDean());
        object.setBuilding(department.getBuilding());
        object.setRoom(department.getRoom());
        departmentRepository.save(object);

        return "AdminRegisterCourseOrDepartment";
    }


    @RequestMapping(value = "/admin/manage_departments", method = RequestMethod.GET)
    public String getDeptCourseForm() {


        return "AdminRegisterCourseOrDepartment";
    }
    @RequestMapping(value = "/admin/manage_departments/update/{dept_id}", method = RequestMethod.GET)
    public String getUpdateDeptForm(Model model,@PathVariable long dept_id) {
       // long pop=1;
    Department department =departmentRepository.getOne(dept_id);
    model.addAttribute("department",department);

        return "adminUpdateDepartment";
    }

    @RequestMapping(value = "/admin/manage_departments/updateDepartment", method = RequestMethod.POST)
    public String updateDepartment(@ModelAttribute("department") Department department, Model model, @RequestParam(name="dept_id") Long dept_id){

        Department object =departmentRepository.getOne(dept_id);
        object.setDept_name(department.getDept_name());
        object.setBuilding(department.getBuilding());
        object.setRoom(department.getRoom());
        object.setDean(department.getDean());
        departmentRepository.save(object);
        model.addAttribute("department",object);
        return "redirect:/admin/ListDepartments";
    }


    // this to delete departments
    @RequestMapping(value = "/admin/manage_departments/delete/{dept_id}",method = RequestMethod.GET)
    public  String removeDepartments(@PathVariable Long dept_id,@ModelAttribute("department") Department department)
    {
        Department a = departmentRepository.getOne(dept_id);
        departmentRepository.delete(a);
        return "redirect:/admin/ListDepartments";
    }

    // this to lIST ALL departments

    @RequestMapping(value = "admin/ListDepartments", method = RequestMethod.GET)
    public String ListDepartments(Model model)
    {
        List<Department> departments=departmentRepository.findAll();
        model.addAttribute("departments",departments);
        return "ListOfDepartments";


    }



 //----------------end of Admin Functions with Department----------------------------------


    ////   //-------------Admin functions with Students-------------------
   @RequestMapping(value = "/admin/manage_students/del/{id}",method =RequestMethod.GET)
    public  String removeStudents(@PathVariable Long id)
    {
        cc.chilipo.mw.chilipo_online_exam_system.students.Student a = studentRepository.getOne(id);
        studentRepository.delete(a);

        return "redirect:/admin/students";
    }



    //admin to get a list of all studnents
   // @RequestMapping(value = "admin/manage_students")
   @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public String getStudents(Model model)
    {
        List<cc.chilipo.mw.chilipo_online_exam_system.students.Student>students=studentRepository.findAll();
        model.addAttribute("students",students);
        return "ListOfStudents";


    }


///below code was for debugging it used ppl contrller html etc
    @RequestMapping(value = "dupodu",method = RequestMethod.GET)
    String getPeople(Model model)
    {

      /*  model.addAttribute( "people", Arrays.asList(
            new person("jame",9),
            new person("lumo",91),
            new person("ansa",19),
            new person("zione",93)
    ));*/
        model.addAttribute("students",studentRepository.findAll());

        return "people";
    }

    @RequestMapping(value = "mujavi",method = RequestMethod.GET)
    String haPeople(Model model)
    {
        return "navIndex";
    }
 //-----------------------end of Admin Functions with Students----------------------------------
    //

 //-----------------------Admin Functions with exams--------------------------------------------

    //admin post exams based on exam id
 @RequestMapping(value = "/admin/post_exam/{id}", method = RequestMethod.GET)
 public String postOrUnpostExam(@PathVariable Long id,Model model)
 {
     Exam Object = examRepository.getOne(id);
     if(!Object.isPosted())
     {
       Object.setPosted(true);
     }
     else if (Object.isPosted())
     {
         Object.setPosted(false);
     }
     examRepository.save(Object);

     return "redirect:/admin/ListExams";
 }

 //Admin get a list of exams
 @RequestMapping(value = "admin/ListExams", method = RequestMethod.GET)
 public String ListExams(Model model)
 {
     List<Exam> exams=examRepository.findAll();
     model.addAttribute("exams",exams);
     return "ListOfExams";


 }

// Admin delete exams
@RequestMapping(value = "/admin/manage_Exams/delete/{exam_id}",method = RequestMethod.GET)
public  String AdminRemoveExams(@PathVariable Long exam_id,@ModelAttribute("exam") Exam exam)
{
    Exam a = examRepository.getOne(exam_id);
    examRepository.delete(a);
    return "redirect:/admin/ListExams";
}

 //-----------------------------------------------------------------------------------------
   //admin webflow functionalities;
    // get Login form
 @RequestMapping(value = "/admin/Login", method = RequestMethod.GET)
 public String getLoginForm(Model model)
 {


     return "AdminLogin";
 }
    @RequestMapping(value = "/admin/default", method = RequestMethod.GET)
    public String getLoginForm1(Model model)
    {


        return "redirect:/";
    }

    @RequestMapping(value = "/admin/Login", method = RequestMethod.POST)
    public String getLoginFormm(Model model)
    {


        return "AdminLogin.html";
    }

   //log admin if admin is not registered directs to login platform
    @RequestMapping(value = "/admin/adminLogged", method = RequestMethod.POST)
    public String logAdmin(@ModelAttribute("admin") Admin admin)
    {

        Admin _admin = adminRepository.findByEmail(admin.getEmail());
      if (_admin.getId()!=null)
      {
          if(_admin.getPassword().equals(admin.getPassword()))
          {
          return "AdminRegisterCourseOrDepartment";
          }
      }

        return "redirect:/admin/Login";
    }

    // call register admin html
    @RequestMapping(value = "/admin/registeor", method = RequestMethod.GET)
    public String getRegisterForm(Model model)
    {
        return "AdminRegister";
    }
}




