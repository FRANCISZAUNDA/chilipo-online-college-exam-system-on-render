package cc.chilipo.mw.chilipo_online_exam_system.studentExamInfo;

import cc.chilipo.mw.chilipo_online_exam_system.exams.Exam;
import cc.chilipo.mw.chilipo_online_exam_system.students.Student;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class studentExamInfo
{
    protected Exam exam;
    protected Student student;
    protected int TotalStudentScore;
    protected boolean passed;
}
