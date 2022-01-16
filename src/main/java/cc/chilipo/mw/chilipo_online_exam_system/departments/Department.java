package cc.chilipo.mw.chilipo_online_exam_system.departments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @Column(unique = true, nullable = false)
    protected String dept_name;

    protected @JsonIgnore String dean;


    protected String building;

    protected String room;


   public Department(String dept_name, String dean, String building, String room) {
        this.dept_name = dept_name;
        this.dean = dean;
        this.building = building;
        this.room = room;
    }
}
