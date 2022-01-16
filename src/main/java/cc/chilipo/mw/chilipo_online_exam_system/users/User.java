package cc.chilipo.mw.chilipo_online_exam_system.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@Entity
//@Table(name = "users")
@MappedSuperclass
public abstract class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @JsonIgnore @Id Long id;

    @Column(unique = true, nullable = false)
    protected String username;

    protected @JsonIgnore String password;

    @Column(unique = true, nullable = false)
    protected String email;

    protected String firstname;

    protected String lastname;

    @Column(name = "phone_number", unique = true, nullable = false)
    @JsonProperty(value = "phone_number")
    protected String phoneNumber;

   public User(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
