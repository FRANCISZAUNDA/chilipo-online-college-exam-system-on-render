package cc.chilipo.mw.chilipo_online_exam_system.courses;

import cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "dept_id")
    protected Department dept_id;

    @Column(unique = true, nullable = false)
    protected String course_name;

   public Course(String course_name) {
        this.course_name = course_name;

    }
}
