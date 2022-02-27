package cc.chilipo.mw.chilipo_online_exam_system.exams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ExamController {

    @Autowired
    private ExamRepository questionRepository;


//@Autowired
   // public Exam createExam(@RequestBody Exam exam) {
   //     Exam object = new Exam();
      //  object.setExam_name(exam.getExam_name());
       // return (Exam) questionRepository.save(object);
   // }
    public ExamController getExamController(){
        return this.getExamController();
    }

}
