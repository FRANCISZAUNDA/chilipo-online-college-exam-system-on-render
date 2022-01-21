package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.exams.ExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Autowired
@Repository
public interface AdminExamRepository extends ExamRepository {



}
