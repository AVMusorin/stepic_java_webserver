package accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
public class Profile {

    public Profile() {
    }

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;
}
