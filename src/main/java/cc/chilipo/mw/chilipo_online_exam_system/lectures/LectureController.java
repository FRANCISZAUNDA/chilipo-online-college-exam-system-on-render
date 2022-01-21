package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private ExamRepository examRepository;
   // private ExamController lectureExamControler;

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
    @RequestMapping(value = "/lectures/manage_exams", method = RequestMethod.POST)
    public Exam createExam(@RequestBody Exam exam) {
        Exam object = new Exam();
        object.setExam_name(exam.getExam_name());
        object.setQuestion(exam.getQuestion());
        object.setQuestion_marks(exam.getQuestion_marks());
        object.setQuestion_no(exam.getQuestion_no());
        object.setOption1(exam.getOption1());
        object.setOption2(exam.getOption2());
        object.setOption3(exam.getOption3());
        object.setOption4(exam.getOption4());
        object.setQuestion_answer(exam.getQuestion_answer());

        return  examRepository.save(object);
    }


    //lecture editing posted exams
   @RequestMapping(value = "/lectures/manage_exams/{Exam_id}",method=RequestMethod.PUT)
    public Exam updateExam(@RequestBody Exam exam,@PathVariable Long Exam_id)
    { Exam object = examRepository.findById(Exam_id).get();
        object.setExam_name(exam.getExam_name());
        object.setQuestion(exam.getQuestion());
        object.setQuestion_marks(exam.getQuestion_marks());
        object.setQuestion_no(exam.getQuestion_no());
        object.setOption1(exam.getOption1());
        object.setOption2(exam.getOption2());
        object.setOption3(exam.getOption3());
        object.setOption4(exam.getOption4());
        object.setQuestion_answer(exam.getQuestion_answer());

        return  examRepository.save(object);

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


}
