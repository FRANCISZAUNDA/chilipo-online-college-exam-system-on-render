package cc.chilipo.mw.chilipo_online_exam_system.studentAnswers;

import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import cc.chilipo.mw.chilipo_online_exam_system.questions.Question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student_answers")
public class StudentAnswer
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @JsonIgnore
    @Id Long id;

    @OneToOne
    @JoinColumn(name = "questionId")
    protected  Question questions ;

    @OneToOne
    @JoinColumn(name = "student_id")
    protected  Student student ;

    protected String  studentAnswer;
    protected int scoreMark;
    protected int totalScoreMarks;
    protected boolean pass;



}
