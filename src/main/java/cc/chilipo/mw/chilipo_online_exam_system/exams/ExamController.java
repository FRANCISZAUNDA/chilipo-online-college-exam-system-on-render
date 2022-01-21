package cc.chilipo.mw.chilipo_online_exam_system.exams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExamController {

    @Autowired
    private ExamRepository examRepository;


//@Autowired
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

        return (Exam) examRepository.save(object);
    }
    public ExamController getExamController(){
        return this.getExamController();
    }

}
