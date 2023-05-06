package security.app.secure.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "savings_account")
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal account_balance;

    @Column(name = "accountno")
    private int accountno;

    @Column(name="account", unique = true)
    private String account;

    @OneToOne(mappedBy = "savingsAccount")
    private User user;

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL)
    //@JoinColumn(name = "save_trans_id")
    private List<SavingsTransaction> savetransitems;
}
