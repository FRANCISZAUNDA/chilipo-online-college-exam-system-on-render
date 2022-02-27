package cc.chilipo.mw.chilipo_online_exam_system.students;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import  cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import  cc.chilipo.mw.chilipo_online_exam_system.courses.Course;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "students")
public class Student extends cc.chilipo.mw.chilipo_online_exam_system.users.User {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private @JsonIgnore @Id Long Student_id;
    @OneToOne
    private  Department department;
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolled_student")
    private List<Course> courses;

   //private double gpa;



    public Student(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
