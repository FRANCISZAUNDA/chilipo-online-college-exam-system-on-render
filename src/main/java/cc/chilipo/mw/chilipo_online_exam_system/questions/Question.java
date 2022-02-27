package cc.chilipo.mw.chilipo_online_exam_system.questions;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "questions",uniqueConstraints ={@UniqueConstraint(columnNames = {"questionnumber","questionasked"})})
public class Question
{
    @ManyToOne
    @JoinColumn(name = "Exam_id")
    protected Exam exam ;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @JsonIgnore
    @Id Long questionId;

    @Column(nullable = false)
    protected  int questionnumber;

    @Column(unique = true, nullable = false)
    protected  String questionasked; //unique to avoid repetition of questions

    @Column( nullable = false)
    protected String Option1;
    @Column( nullable = false)
    protected String Option2;
    @Column( nullable = false)
    protected String Option3;
    @Column( nullable = false)
    protected String Option4;
    @Column(nullable = false)
    protected String question_answer;
    @Column( nullable = false)
    protected int question_marks;
    protected  String studentanswer;


   public Question(int Question_no, String askedQuestion, String Option1,
                   String Option2, String Option3, String Option4, String Question_answer,
                   int Question_marks) {
        this.questionnumber =Question_no;
        this.questionasked =askedQuestion;
        this.Option1=Option1;
        this.Option2=Option2;
        this.Option3=Option3;
        this.Option4=Option4;
        this.question_answer =Question_answer;
        this.question_marks =Question_marks;

    }
}
