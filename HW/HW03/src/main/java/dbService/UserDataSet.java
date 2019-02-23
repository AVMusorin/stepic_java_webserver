package dbService;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserDataSet implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    @Column(name = "name", unique = true, updatable = false)
    private String name;

    public UserDataSet() {
    }

    public UserDataSet(long id, String name) {
        setId(id);
        setName(name);
    }

    public UserDataSet(String name) {
        this.setId(-1);
        this.setName(name);
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
