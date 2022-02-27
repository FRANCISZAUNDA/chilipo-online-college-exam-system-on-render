package cc.chilipo.mw.chilipo_online_exam_system.exams;

import cc.chilipo.mw.chilipo_online_exam_system.admin.Admin;
import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Autowired
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    public List<Exam> findByCourse(Course course);

}
