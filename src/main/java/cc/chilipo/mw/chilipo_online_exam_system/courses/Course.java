package cc.chilipo.mw.chilipo_online_exam_system.courses;

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
    //should find info on how to make this a foreign key
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected @JsonIgnore @Id Long dept_id;

   // @GeneratedValue(strategy = GenerationType.AUTO)
   // protected @JsonIgnore @Id Long course_id;

    @Column(unique = true, nullable = false)
    protected String course_name;




   public Course(String course_name) {
        this.course_name = course_name;

    }
}
