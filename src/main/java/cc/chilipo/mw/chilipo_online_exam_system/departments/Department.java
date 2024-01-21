package cc.chilipo.mw.chilipo_online_exam_system.departments;

import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long dept_id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Course> courses;

    @Column(unique = true, nullable = false)
    private String dept_name;

    private String dean;
    private String building;
    private String room;

    public Department(String dept_name, String dean, String building, String room) {
        this.dept_name = dept_name;
        this.dean = dean;
        this.building = building;
        this.room = room;
    }
}
