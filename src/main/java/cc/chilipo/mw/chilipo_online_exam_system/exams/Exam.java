package cc.chilipo.mw.chilipo_online_exam_system.exams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import cc.chilipo.mw.chilipo_online_exam_system.departments.Department;
import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "exams")
public class Exam
{
    //should find info on how to make this a foreign key
   @OneToOne
    protected Department department;
    @OneToOne
    protected Course course;

   @GeneratedValue(strategy = GenerationType.AUTO)
    protected @JsonIgnore @Id Long Exam_id;

    @Column(unique = true, nullable = false)
    protected String Exam_name;

    @Column(unique = true, nullable = false)
    protected String Question_no; // string not int or long to allow writing 1a, 1b etc

    @Column(unique = true, nullable = false)
    protected String Question; //unique to avoid repetition

    @Column(unique = true, nullable = false)
    protected String Option1;
    @Column(unique = true, nullable = false)
    protected String Option2;
    @Column(unique = true, nullable = false)
    protected String Option3;
    @Column(unique = true, nullable = false)
    protected String Option4;
    @Column(unique = true, nullable = false)
    protected String Question_answer;
    @Column(unique = true, nullable = false)
    protected int Question_marks;



   public Exam(String Exam_name, String Question_no,String Question,String Option1,
               String Option2,String Option3,String Option4,String Question_answer,
               int Question_marks) {
        this.Exam_name = Exam_name;
        this.Question_no=Question_no;
        this.Question=Question;
        this.Option1=Option1;
        this.Option2=Option2;
        this.Option3=Option3;
        this.Option4=Option4;
        this.Question_answer=Question_answer;
        this.Question_marks=Question_marks;

    }
}
