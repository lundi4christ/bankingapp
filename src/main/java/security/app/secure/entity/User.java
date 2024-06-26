package security.app.secure.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
                                            @UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    /* @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "savings_acct_id")*/
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private SavingsAccount savingsAccount;


  /*  @Override
    public String toString() {
        return "User{" +
                "id="  +
                ", name='"  + '\'' +
                ", username='" + '\'' +
                ", email='"  + '\'' +
                ", password='"  + '\'' +
                ", roles="  +
                ", savingsAccount="  +
                '}';
    }
*/

}
