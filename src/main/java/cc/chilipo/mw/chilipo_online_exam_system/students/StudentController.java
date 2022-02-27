package cc.chilipo.mw.chilipo_online_exam_system.students;

import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import cc.chilipo.mw.chilipo_online_exam_system.courses.CourseRepository;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.Lecture;
import cc.chilipo.mw.chilipo_online_exam_system.lectures.LectureRepository;
import cc.chilipo.mw.chilipo_online_exam_system.questions.Question;
import cc.chilipo.mw.chilipo_online_exam_system.questions.QuestionRepository;
import cc.chilipo.mw.chilipo_online_exam_system.studentAnswers.StudentAnswerRepository;
import cc.chilipo.mw.chilipo_online_exam_system.studentAnswers.StudentAnswer;
import cc.chilipo.mw.chilipo_online_exam_system.studentExamInfo.studentExamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private LectureRepository lectureRepository;
   @Autowired
   private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private ExamRepository examRepository;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents() {
        return "Hello students";
    }


    private int getQuestion=0; // will be used to fetch question from list


    //viewing student data
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @RequestMapping(value = "students/register", method = RequestMethod.GET)
    public String ListAllLectures(Model model)
    {
       Student student = new Student();
        model.addAttribute("student",student);
        return "studentRegistration";
    }

    @RequestMapping(value = "students/log", method = RequestMethod.GET)
    public String getlogin(Model model)
    {
       Student student = new Student();
        model.addAttribute("student",student);
        return "StudentLogin";
    }

    //updating student fields
    @Transactional
    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public cc.chilipo.mw.chilipo_online_exam_system.students.Student updateStudent(@RequestBody cc.chilipo.mw.chilipo_online_exam_system.students.Student student, @PathVariable Long id) {
        cc.chilipo.mw.chilipo_online_exam_system.students.Student object = studentRepository.getOne(id);
        object.setEmail(student.getEmail());
        object.setPassword(student.getPassword());
        object.setFirstname(student.getFirstname());
        object.setLastname(student.getLastname());
        object.setUsername(student.getUsername());
        object.setPhoneNumber(student.getPhoneNumber());

        return studentRepository.save(object);

    }

    /// student registering courses
    @RequestMapping(value = "students/register_course/{student_id}/{course_id}", method = RequestMethod.PUT)
    public Course student_register_course(@PathVariable Long student_id, @PathVariable Long course_id) {
        cc.chilipo.mw.chilipo_online_exam_system.students.Student _student = studentRepository.getOne(student_id);
        Course _course = courseRepository.getOne(course_id);
        _course.enrollStudent(_student);
        return courseRepository.save(_course);

    }

    //student get a Question
    @RequestMapping(value = "students/{student_id}/{exam_id}/{questionNumber}", method = RequestMethod.POST)
    public List<Question> getQuestion(@PathVariable Long student_id, @PathVariable Long exam_id, @PathVariable Long questionNumber) {
        Course _course = courseRepository.getOne(examRepository.getOne(exam_id).getCourse().getCourse_id());
        if (_course.getEnrolled_student().contains(studentRepository.getOne(student_id)) && examRepository.getOne(exam_id).isPosted()) {
            //questionRepository.findAll()
            return questionRepository.findByExamAndQuestionnumber(examRepository.getOne(exam_id),questionNumber);

        }

     List<Question>ReturnEmptyQuestion = new ArrayList<>();
        return ReturnEmptyQuestion;

    }

    //submitQuestion Answer
    @RequestMapping(value="students/submitQuestionAnswer/{student_id}/{exam_id}/{Question_no}", method=RequestMethod.POST)
    public void SubmitQuestionAnswer(@RequestBody StudentAnswer studentAnswers, @PathVariable Long student_id,
                                     @PathVariable Long exam_id, @PathVariable Long Question_no)
    {
        StudentAnswer Object =new StudentAnswer();
        Object.setStudent(studentRepository.getOne(student_id));
        Object.setQuestions(questionRepository.getOne(Question_no));
        Object.setStudentAnswer(studentAnswers.getStudentAnswer());
        if(questionRepository.getOne(Question_no).getQuestion_answer().equals(studentAnswers.getStudentAnswer()))
        {

            Object.setScoreMark(questionRepository.getOne(Question_no).getQuestion_marks());
        }
        else
        {
             Object.setScoreMark(0);
        }
        studentAnswerRepository.save(Object);
    }




    //lecture first Login page
    @RequestMapping(value = "/Student/Login", method = RequestMethod.GET)
    public String StudentGetLoginForm(Model model )
    {
        Student student = new Student();
        model.addAttribute("student",student);
        return "StudentLogin";
    }

    ///get student control pane
    @RequestMapping(value = "/Student/Login/{id}", method = RequestMethod.GET)
    public String StudentGetControlPanel(Model model,@PathVariable Long id )
    {
        Student student = studentRepository.getOne(id);
        model.addAttribute("student",student);
        return "studentsControlPanel";
    }

    //Log in student
    @RequestMapping(value = "/student/first_login", method = RequestMethod.POST)
    public String StudentFirstLogin(@ModelAttribute("student") Student student, Model model  )
    {
       Student _student = studentRepository.findByEmail(student.getEmail());
        if(_student!=null)
        {
            if(_student.getPassword().equals(student.getPassword()))
            {
                model.addAttribute("student",studentRepository.findByEmail(student.getEmail()) );
                return "redirect:/Student/Login/"+_student.getId();

            }
        }
        return "redirect:/Student/Login";
    }

    //get student register form
    @RequestMapping(value = "/Student/register", method = RequestMethod.GET)
    public String StudentGetRegisterForm(Model model )
    {
        Student student = new Student();
        model.addAttribute("student",student);
        return "StudentRegistration";
    }

    @RequestMapping(value = "/student/register", method = RequestMethod.POST)
    public String RegisterStudent(@ModelAttribute("student") Student student) {
        Student object = new Student();
        object.setEmail(student.getEmail());
        object.setPassword(student.getPassword());
        object.setFirstname(student.getFirstname());
        object.setLastname(student.getLastname());
        object.setUsername(student.getUsername());
        object.setPhoneNumber(student.getPhoneNumber());
        studentRepository.save(object);
        return "redirect:/Student/Login";
    }
    //update login
    @RequestMapping(value = "/student/update_login", method = RequestMethod.POST)
    public String StudentUpdateLogin(@ModelAttribute("student") Student student, Model model  )
    {
        Student _student = studentRepository.findByEmail(student.getEmail());
        if(_student!=null)
        {
            if(_student.getPassword().equals(student.getPassword()))
            {
                model.addAttribute("student",studentRepository.findByEmail(student.getEmail()) );
                return "redirect:/Student/update/"+student.getEmail();

            }
        }
        return "redirect:/Student/Login";
    }




    //UPDATING STUDENTS INFO
    @RequestMapping(value = "/students/update/stud", method = RequestMethod.POST)
    public String updateStudents(@ModelAttribute("student") Student student , @RequestParam(name="id") Long id){
        Student object =studentRepository.getOne(id);
        object.setEmail(student.getEmail());
        object.setPassword(student.getPassword());
        object.setFirstname(student.getFirstname());
        object.setLastname(student.getLastname());
        object.setUsername(student.getUsername());
        object.setPhoneNumber(student.getPhoneNumber());
        studentRepository.save(object);
        return "redirect:/Student/Login/";
    }

    //student get update forrm
    //lecture first Login page
    @RequestMapping(value = "/Student/update/{email}", method = RequestMethod.GET)
    public String StudentGetUpdateForm(Model model ,@PathVariable String email)
    {
        Student student = studentRepository.findByEmail(email);
        model.addAttribute("student",student);
        return "StudentUpdateHimself";
    }
    //student update Login form Login page
    @RequestMapping(value = "/Student/updateLogin", method = RequestMethod.GET)
    public String StudentGetLoginUpdateForm(Model model )
    {
        return "StudentsUpdateLogin";
    }
    // ------------------------------------------------------------------------------------------------
    //student Register courses
    @RequestMapping(value = "/student/reg/course/{id}/{course_id}", method = RequestMethod.GET)
    public String StudentRegisterCourse(Model model,@PathVariable Long id ,@PathVariable Long course_id)
    {
        Student student = studentRepository.getOne(id);
        Course course = courseRepository.getOne(course_id);
        if(!course.getEnrolled_student().contains(student))
        {
            course.getEnrolled_student().add(student);
            courseRepository.save(course);

        }
        else
        {
            course.getEnrolled_student().remove(studentRepository.getOne(id));
            courseRepository.save(course);
        }

        return "redirect:/Student/getAlistOfAllCourses/"+id;
    }

    @RequestMapping(value = "/Student/getAlistOfAllCourses/{id}", method = RequestMethod.GET)
    public String allStudentCourses(Model model ,@PathVariable Long id)
    {
        Student student = studentRepository.getOne(id);
        model.addAttribute("student",student);
        model.addAttribute("courses",courseRepository.findAll());
        return "studentListOfAllCourses";
    }


//guyfiufiu
    @RequestMapping(value = "/getMytry", method = RequestMethod.GET)
    public String mytry(Model model )
    {

        model.addAttribute("student",studentRepository.findAll());
        model.addAttribute("lectures",lectureRepository.findAll());
        return "mytry";
    }

//list course exams
@RequestMapping(value = "/student/course/exam/{id}/{course_id}", method = RequestMethod.GET)
public String listCourseExams(Model model,@PathVariable Long id ,@PathVariable Long course_id)
{
    Course course = courseRepository.getOne(course_id);
    Student student = studentRepository.getOne(id);
    if(course.getEnrolled_student().contains(student))
    {
        List<Exam> exams= new ArrayList<>();
        for(Exam exam:examRepository.findByCourse(courseRepository.getOne(course_id)))
        {
            if(exam.isPosted())
            {
                exams.add(exam);
            }

        }

        model.addAttribute("student",student);
        model.addAttribute("exam",exams);
        return "studentListofExams";
    }


    return "redirect:/Student/getAlistOfAllCourses/"+student.getId();
}

    @RequestMapping(value = "/student/exam/questions/{id}/{exam_id}", method = RequestMethod.GET)
    public String listExamQuestions(Model model,@PathVariable Long id ,@PathVariable Long exam_id)
    {
        Student student = studentRepository.getOne(id);
        Exam exam = examRepository.getOne(exam_id);
        if(getQuestion <questionRepository.findByExam(examRepository.getOne(exam_id)).size())
        {

        model.addAttribute("student",student);
        List<Question> questions=questionRepository.findByExam(examRepository.getOne(exam_id));
        model.addAttribute("Question",questions.get(getQuestion));
        getQuestion++;
        return "studentListOfAllExamQuestions";
        }
        return "redirect:/student/course/exam/"+student.getId()+"/"+exam.getCourse().getCourse_id();
    }

    @RequestMapping(value = "/student/submit/question/", method = RequestMethod.POST)
    public String SubmitExamQuestion(@ModelAttribute("Question") Question question, Model model,@RequestParam(name="id") Long id)
    {
        StudentAnswer studAns = new StudentAnswer();
        studAns.setStudent(studentRepository.getOne(id));
        studAns.setQuestions(questionRepository.getOne(question.getQuestionId()));
        studAns.setStudentAnswer(question.getStudentanswer());
        studentAnswerRepository.save(studAns);

        return "redirect:/student/exam/questions/"+id+"/"+questionRepository.getOne(question.getQuestionId()).getExam().getExam_id();
    }

    @RequestMapping(value = "/student/check_myExams/{id}", method = RequestMethod.GET)
    public String getStudentExamInfo(Model model,@PathVariable Long id)
    {
        List<Exam>totalExams=studentWrittenExams(id);
        List<studentExamInfo> studExam_info=new ArrayList<>();

            for(Exam exams:totalExams)
            {
                studentExamInfo studexaminf=new studentExamInfo();
                studexaminf.setExam(exams);
                studexaminf.setStudent(studentRepository.getOne(id));
                studexaminf.setTotalStudentScore(getTotalScore(id,exams.getExam_id()));
                studexaminf.setPassed((studexaminf.getTotalStudentScore()>=exams.getRequiredpassmarks()));
                studExam_info.add(studexaminf);
            }


        model.addAttribute("studentExamInfo",studExam_info);

        return "studentWritenExamInfo";
    }

     public List<Exam> studentWrittenExams(Long studentId)
    {
        List<Exam>exams=new ArrayList<>();
        for(StudentAnswer studentAnswer:studentAnswerRepository.findByStudent(studentRepository.getOne(studentId)))
        {
           if(!(exams.contains(studentAnswer.getQuestions().getExam())))
            {
                exams.add(studentAnswer.getQuestions().getExam());
            }
        }
        return exams;
    }


    //findTotalScore
    public int getTotalScore(Long student_id,Long exam_id)
    {
        int totalScore =0;
        List<StudentAnswer> studentAnswerList=(studentAnswerRepository.findByStudent(studentRepository.getOne(student_id)));
        for (StudentAnswer studAns:studentAnswerList)
        {
            if(studAns.getQuestions().getExam()==examRepository.getOne(exam_id))
            {
                if(studAns.getStudentAnswer().equals(studAns.getQuestions().getQuestion_answer()))
                {
                    totalScore+=studAns.getQuestions().getQuestion_marks();
                }
            }
        }
        return totalScore;
    }

    @RequestMapping(value = "/chilipo/courses", method = RequestMethod.GET)
    public String getallCourses(Model model)
    {
        model.addAttribute("courses",courseRepository.findAll());
        return "userViewOfCourses";
    }


    //hfjh-----------------------------------------hgdjhgjd-------------------------------------------





















}
