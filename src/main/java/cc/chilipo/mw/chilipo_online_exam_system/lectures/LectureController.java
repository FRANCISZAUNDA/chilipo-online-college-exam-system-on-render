package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import cc.chilipo.mw.chilipo_online_exam_system.courses.CourseRepository;
import cc.chilipo.mw.chilipo_online_exam_system.questions.Question;
import cc.chilipo.mw.chilipo_online_exam_system.questions.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;

@Controller
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private QuestionRepository questionRepository;
   // private ExamController lectureExamControler;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/lectures", method = RequestMethod.GET)
    public String getLectures() {
        return "Hello lectures";
    }

    // lecture updating profile
    @RequestMapping(value="/lectures",method = RequestMethod.PUT)
    public Lecture updateLecture(@RequestBody Lecture lecture)
    {
        return lectureRepository.save(lecture);
    }

  /*  @RequestMapping(value = "/lectures", method = RequestMethod.POST)
    public Lecture createLecture(@RequestBody Lecture lecture) {
        Lecture object = new Lecture();
        object.setEmail(lecture.getEmail());
        object.setPassword(lecture.getPassword());
        object.setFirstname(lecture.getFirstname());
        object.setLastname(lecture.getLastname());
        object.setUsername(lecture.getUsername());
        object.setPhoneNumber(lecture.getPhoneNumber());

        return LectureRepository.save(object);
    }*/ // we not allowing lecture to register himself code above for debugin only


    //this is for welcoming page might be changed later
    @RequestMapping(value = "/lectures/manage_exams", method = RequestMethod.GET)
    public String getExams() {
        return "feel free to post exams";
    }

    //this is for posting exams post method
    //but it must be noted that lectures will just form and save exams
    // the Admin will be the one to post them on the internet

    //this will help admin update courses
    @RequestMapping(value = "/lectures/manage_exams/create", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("exam") Exam exam ,@RequestParam(name="course_id") Long course_id){
        Exam object = new Exam();
        object.setExam_name(exam.getExam_name());
        object.setRequiredpassmarks(exam.getRequiredpassmarks());
        object.setCourse(courseRepository.getOne((course_id)));
       examRepository.save(object);
       return "redirect:/getCoursesList";
    }





    //lecture creating questions
    @RequestMapping(value = "/lectures/manage_exams_Questions/{Exam_id}", method = RequestMethod.POST)
    public Question createQuestion(@RequestBody Question question, @PathVariable Long Exam_id) {
        Question object = new Question();
        object.setQuestionasked(question.getQuestionasked());
        object.setQuestion_marks(question.getQuestion_marks());
        object.setQuestionnumber(question.getQuestionnumber());
        object.setOption1(question.getOption1());
        object.setOption2(question.getOption2());
        object.setOption3(question.getOption3());
        object.setOption4(question.getOption4());
        object.setQuestion_answer(question.getQuestion_answer());
        object.setExam(examRepository.getOne(Exam_id));

        return  questionRepository.save(object);
    }

    //lecture updating questions
    @RequestMapping(value = "/lectures/manage_exams_Questions/{Exam_id}", method = RequestMethod.PUT)
    public void UpdateQuestion(@RequestBody Question question, @PathVariable Long Exam_id) {
      //should update only if the exams are not posted
       if(!examRepository.getOne(Exam_id).isPosted()){
        Question object = new Question();
        object.setQuestionasked(question.getQuestionasked());
        object.setQuestion_marks(question.getQuestion_marks());
        object.setQuestionnumber(question.getQuestionnumber());
        object.setOption1(question.getOption1());
        object.setOption2(question.getOption2());
        object.setOption3(question.getOption3());
        object.setOption4(question.getOption4());
        object.setQuestion_answer(question.getQuestion_answer());
        object.setExam(examRepository.getOne(Exam_id));

        questionRepository.save(object);
       }

    }

    //----------------------------------------------
    @RequestMapping(value = "/lectures/{id}", method = RequestMethod.PUT)
    public Lecture update_Lecture(@RequestBody Lecture lecture, @PathVariable Long id) {


        Lecture object = lectureRepository.findById(id).get();
        object.setEmail(lecture.getEmail());
        object.setPassword(lecture.getPassword());
        object.setFirstname(lecture.getFirstname());
        object.setLastname(lecture.getLastname());
        object.setUsername(lecture.getUsername());
        object.setPhoneNumber(lecture.getPhoneNumber());
        object.setAge(lecture.getAge());

        return lectureRepository.save(object);
    }

    //open the main lectures control view
    @RequestMapping(value = "/getControlPanel", method = RequestMethod.GET)
    public String getLectureControlPanel(Model model)
    {
        return "LecturesControlPanel";
    }

    //get all courses
    @RequestMapping(value = "/getCoursesList", method = RequestMethod.GET)
    public String getLectureCoursesList(Model model)
    {
        model.addAttribute("courses", courseRepository.findAll());
        return "lecturesViewForAllCourses";
    }

    @RequestMapping(value = "/Lectures/manage_exams/create/{course_id}",method = RequestMethod.GET)
    public  String getExamRegForm(@PathVariable Long course_id,Model model)
    {
        Course a = courseRepository.getOne(course_id);
        Exam exam1 = new Exam();
        exam1.setCourse(a);
        model.addAttribute("exam",exam1);
        return "LectureRegisterExam";
    }

    //get all courses
    @RequestMapping(value = "lectures/getListOfCourseExams/{course_id}", method = RequestMethod.GET)
    public String getLectureCourseExams(@PathVariable Long course_id, Model model)
    {
        model.addAttribute("exam", examRepository.findByCourse(courseRepository.getOne(course_id)));
        return "lecturesExamView";
    }

/// request exam update form

    @RequestMapping(value = "lectures/UpdateExams/{exam_id}", method = RequestMethod.GET)
    public String getLectureUpdateExamForm(@PathVariable Long exam_id, Model model)
    {
     Exam  exam = examRepository.getOne(exam_id);
     model.addAttribute("exam",exam);
        return "lectureUpdateExam";
    }

    //update exam
    @RequestMapping(value = "/lectures/manage_exams/UpdateExams", method = RequestMethod.POST)
    public String LectureUpdateExam(@ModelAttribute("exam") Exam exam ,@RequestParam(name="exam_id") Long exam_id){
        Exam object =examRepository.getOne(exam_id);
        object.setExam_name(exam.getExam_name());
        object.setRequiredpassmarks(exam.getRequiredpassmarks());
        object.setCourse(courseRepository.getOne(exam.getCourse().getCourse_id()));
        examRepository.save(object);
        return "redirect:/getCoursesList";
    }




    //lecture deletes Exam
    @RequestMapping(value = "lectures/Exams/del/{exam_id}", method = RequestMethod.GET)
    public String LectureDeletExam(@PathVariable Long exam_id, Model model)
    {
       examRepository.delete(examRepository.getOne(exam_id));
        return "redirect:/getCoursesList";
    }
/*
    //this will help admin update courses
    @RequestMapping(value = "/Lectures/manage_exams/updateExam", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("course") Course course ,@RequestParam(name="course_id") Long course_id){
        Course object =courseRepository.getOne(course_id);
        object.setCourse_name(course.getCourse_name());
        object.setDepartment(departmentRepository.getOne(course.getDepartment().getDept_id()));
        courseRepository.save(object);
        return "redirect:/admin/coursesList";
    }*/

//get lecture create question form
@RequestMapping(value = "/lectures/create_questions/{exam_id}", method = RequestMethod.GET)
public String getLectureCreateQuestionForm(@PathVariable Long exam_id, Model model)
{
    Question question=new Question();
    question.setExam(examRepository.getOne(exam_id));
    model.addAttribute("Question",question);
    return "lectureCreateQuestions";
}

//lectures create exam
@RequestMapping(value = "/lectures/manage_exams/questions", method = RequestMethod.POST)
public String LectureFormQuestions(@ModelAttribute("Question") Question question ,@RequestParam(name="exam_id") Long exam_id){

    Question object = new Question();
    object.setQuestionasked(question.getQuestionasked());
    object.setQuestion_marks(question.getQuestion_marks());
    object.setQuestionnumber(question.getQuestionnumber());
    object.setOption1(question.getOption1());
    object.setOption2(question.getOption2());
    object.setOption3(question.getOption3());
    object.setOption4(question.getOption4());
    object.setQuestion_answer(question.getQuestion_answer());
    object.setExam(examRepository.getOne(exam_id));
    questionRepository.save(object);
    return "redirect:/lectures/create_questions/"+ exam_id;
}

//view all questions assigned to an exam
@RequestMapping(value = "/lectures/viewExamQuestions/{exam_id}", method = RequestMethod.GET)
public String getLectureExamQuestion(@PathVariable Long exam_id, Model model)
{

    model.addAttribute("Question",questionRepository.findByExam(examRepository.getOne(exam_id)));
    return "lectureListOfQuestions";
}

    //delete questions
@RequestMapping(value = "/lecture/manage_questions/delete/{questionId}/{exam_id}", method = RequestMethod.GET)
public String DeleteQuestions(@PathVariable Long questionId,@PathVariable Long  exam_id, Model model)
{
    questionRepository.delete(questionRepository.getOne(questionId));
    return "redirect:/lectures/viewExamQuestions/"+exam_id;
}

//to get login form for updating lectures
@RequestMapping(value = "/lectures/updateLogin", method = RequestMethod.GET)
public String LectureupdateLoginform(Model model )
{
    Lecture lecture = new Lecture();
    model.addAttribute("lecture",lecture);
    return "LectureUpdateLogin";
}



@RequestMapping(value = "/lecture/updateHimself", method = RequestMethod.POST)
public String LectureUpdateHimself(@ModelAttribute("lecture") Lecture lecture, Model model  )
{
    Lecture _lecture = lectureRepository.findByEmail(lecture.getEmail());
    if(_lecture!=null)
    {
        if(_lecture.getPassword().equals(lecture.getPassword()))
        {
            model.addAttribute("lecture",lectureRepository.getOne(_lecture.getId()) );
            return "LectureUpdateHimself";

        }
    }
    return "redirect:/lectures/updateLogin";
}

    @RequestMapping(value = "/lecture/update/lec", method = RequestMethod.POST)
    public String updateLecture(@ModelAttribute("lecture") Lecture lecture , @RequestParam(name="id") Long id){
        Lecture _Lecture =lectureRepository.getOne(id);
        _Lecture.setEmail(lecture.getEmail());
        _Lecture.setPassword(lecture.getPassword());
        _Lecture.setFirstname(lecture.getFirstname());
        _Lecture.setLastname(lecture.getLastname());
        _Lecture.setUsername(lecture.getUsername());
        _Lecture.setPhoneNumber(lecture.getPhoneNumber());
        _Lecture.setAge(lecture.getAge());
        lectureRepository.save(_Lecture);
        return "redirect:/getControlPanel";
    }

    //lecture first Login page
    @RequestMapping(value = "/lectures/firstLogin", method = RequestMethod.GET)
    public String LectureFirstLoginForm(Model model )
    {
        Lecture lecture = new Lecture();
        model.addAttribute("lecture",lecture);
        return "LectureLogin";
    }

    //Log in lecture
    @RequestMapping(value = "/lecture/first_login", method = RequestMethod.POST)
    public String LectureFirstLogin(@ModelAttribute("lecture") Lecture lecture, Model model  )
    {
        Lecture _lecture = lectureRepository.findByEmail(lecture.getEmail());
        if(_lecture!=null)
        {
            if(_lecture.getPassword().equals(lecture.getPassword()))
            {
                model.addAttribute("lecture",lectureRepository.getOne(_lecture.getId()) );
                return "redirect:/getControlPanel";

            }
        }
        return "redirect:/lectures/firstLogin";
    }


}
