package cc.chilipo.mw.chilipo_online_exam_system.courses;

import cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import cc.chilipo.mw.chilipo_online_exam_system.departments.DepartmentRepository;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "courses")
public class Course
{
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected @JsonIgnore @Id Long course_id;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToMany
    @JoinTable
            (
            name="students_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
                    inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private List<Student>  enrolled_student;

    @Column(unique = true, nullable = false)
    protected String course_name;

   public Course(String course_name) {
        this.course_name = course_name;

    }

    public void enrollStudent(Student student)
    {
        enrolled_student.add(student);
    }
}
