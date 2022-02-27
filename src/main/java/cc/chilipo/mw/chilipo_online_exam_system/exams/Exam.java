package cc.chilipo.mw.chilipo_online_exam_system.exams;

import cc.chilipo.mw.chilipo_online_exam_system.questions.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import cc.chilipo.mw.chilipo_online_exam_system.courses.Course;
import org.springframework.beans.factory.annotation.Autowired;

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
   //@OneToOne
   // protected Department department;
    @ManyToOne
    protected Course course;
    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Autowired
    @JsonIgnore
    protected List<Question> questions;

   @GeneratedValue(strategy = GenerationType.AUTO)
    protected @JsonIgnore @Id Long Exam_id;

    @Column(nullable = false)
    protected String Exam_name;
    protected boolean posted=false;
    protected int requiredpassmarks;


   public Exam(String Exam_name,int requiredPassMarks)
   {
        this.Exam_name = Exam_name;
        this.requiredpassmarks =requiredPassMarks;
    }
}
