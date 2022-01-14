package cc.chilipo.mw.chilipo_online_exam_system.admin;

import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "admin")
public class Admin extends User {
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private @JsonIgnore @Id Long Student_id;


    private double gpa;



    public Admin(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
