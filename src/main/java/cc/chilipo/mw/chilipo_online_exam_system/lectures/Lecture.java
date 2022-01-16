package cc.chilipo.mw.chilipo_online_exam_system.lectures;

import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import cc.chilipo.mw.chilipo_online_exam_system.admin.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "lectures")
public class Lecture extends User {
   // @GeneratedValue(strategy = GenerationType.AUTO)
    //private @JsonIgnore @Id Long lecture_id;

   // @ManyToOne
   // private  Admin admin;
   private  double age;
   public Lecture(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

  // */ //commented this as we dont want lecture to create himself
}
