package cc.chilipo.mw.chilipo_online_exam_system.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;


//@Autowired
    public Question createExam(@RequestBody Question exam) {
        Question object = new Question();
        object.setQuestionasked(exam.getQuestionasked());
        object.setQuestion_marks(exam.getQuestion_marks());
        object.setQuestionnumber(exam.getQuestionnumber());
        object.setOption1(exam.getOption1());
        object.setOption2(exam.getOption2());
        object.setOption3(exam.getOption3());
        object.setOption4(exam.getOption4());
        object.setQuestion_answer(exam.getQuestion_answer());

        return (Question) questionRepository.save(object);
    }
    public QuestionController getExamController(){
        return this.getExamController();
    }

}
