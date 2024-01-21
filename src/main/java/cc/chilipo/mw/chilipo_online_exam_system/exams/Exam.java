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
public class Exam {
    @ManyToOne
    protected Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Autowired
    @JsonIgnore
    protected List<Question> questions;

    @GeneratedValue(strategy = GenerationType.AUTO)
    protected @JsonIgnore @Id Long Exam_id;

    @Column(nullable = false)
    protected String Exam_name;

    protected boolean posted = false;
    protected int requiredpassmarks;

    public Exam(String Exam_name, int requiredPassMarks) {
        this.Exam_name = Exam_name;
        this.requiredpassmarks = requiredPassMarks;
    }

    // Getter and Setter methods

    public Long getExam_id() {
        return Exam_id;
    }

    public void setExam_id(Long exam_id) {
        Exam_id = exam_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getExam_name() {
        return Exam_name;
    }

    public void setExam_name(String exam_name) {
        Exam_name = exam_name;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public int getRequiredpassmarks() {
        return requiredpassmarks;
    }

    public void setRequiredpassmarks(int requiredpassmarks) {
        this.requiredpassmarks = requiredpassmarks;
    }
}
