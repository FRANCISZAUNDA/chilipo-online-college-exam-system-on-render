package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {


}