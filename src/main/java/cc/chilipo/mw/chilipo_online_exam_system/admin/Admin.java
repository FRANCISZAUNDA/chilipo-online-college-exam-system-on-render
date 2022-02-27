package cc.chilipo.mw.chilipo_online_exam_system.admin;

import cc.chilipo.mw.chilipo_online_exam_system.users.User;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "admin")
public class Admin extends User {
  private double age;

    public Admin(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }



}
