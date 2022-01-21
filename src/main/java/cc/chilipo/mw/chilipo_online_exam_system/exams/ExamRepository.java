package cc.chilipo.mw.chilipo_online_exam_system.exams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Autowired
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {



}
