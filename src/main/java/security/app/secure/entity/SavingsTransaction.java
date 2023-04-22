package security.app.secure.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "savings_transaction")
public class SavingsTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "savingsaccount_id")
    private SavingsAccount savingsAccount;

    private Date transdate;
    private String description;
    private BigDecimal amount;
    private BigDecimal balance;
    private String status;
    private String type;

    public SavingsTransaction(Date transdate, String description, BigDecimal amount, BigDecimal balance, String status, String type){
        this.transdate = transdate;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
        this.status = status;
        this.type = type;
    }
}
