package cc.chilipo.mw.chilipo_online_exam_system.students;

import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
