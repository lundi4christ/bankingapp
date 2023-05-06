package security.app.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.app.secure.entity.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    SavingsAccount findSavingsAccountById(Long id);
    SavingsAccount findSavingsAccountByAccount(String account);
    SavingsAccount findSavingsAccountByAccountno(int accountno);

    // this will fail because of the underscore
//    SavingsAccount findSavingsAccountByAccount_balance(BigDecimal account_balance);

}
