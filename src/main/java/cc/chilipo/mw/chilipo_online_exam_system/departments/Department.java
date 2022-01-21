package cc.chilipo.mw.chilipo_online_exam_system.departments;

import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected @JsonIgnore @Id Long dept_id;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Autowired
    @JsonIgnore
    protected List<Course> courses;

    @Column(unique = true, nullable = false)
    @Autowired
    protected String dept_name;

    protected String dean;


    protected String building;

    protected String room;


   public Department(String dept_name, String dean, String building, String room) {
        this.dept_name = dept_name;
        this.dean = dean;
        this.building = building;
        this.room = room;
    }
}
