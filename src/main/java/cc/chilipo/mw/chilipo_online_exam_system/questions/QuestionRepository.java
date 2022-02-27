package cc.chilipo.mw.chilipo_online_exam_system.questions;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Autowired
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{

   // public Question findByExam(Exam exam);
    public List<Question> findByExamAndQuestionnumber(Exam exam, Long questionNumber);
    public List<Question> findByExam(Exam exam);

}
