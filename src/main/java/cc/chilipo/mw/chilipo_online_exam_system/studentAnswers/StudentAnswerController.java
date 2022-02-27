package cc.chilipo.mw.chilipo_online_exam_system.studentAnswers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class StudentAnswerController {

    @Autowired
    private StudentAnswerRepository questionRepository;


//@Autowired

    public StudentAnswerController getStudentAnswerController(){
        return this.getStudentAnswerController();
    }

}
