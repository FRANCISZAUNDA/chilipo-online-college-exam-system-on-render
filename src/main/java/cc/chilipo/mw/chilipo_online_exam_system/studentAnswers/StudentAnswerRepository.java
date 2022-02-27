package cc.chilipo.mw.chilipo_online_exam_system.studentAnswers;

import cc.chilipo.mw.chilipo_online_exam_system.questions.Question;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Autowired
@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>
{

     public StudentAnswer findByQuestionsAndStudent(Question question, Student student);

     public List<StudentAnswer> findByStudent(Student student);


}
