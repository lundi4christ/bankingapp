package security.app.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.app.secure.entity.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

//    SavingsAccount findSavingsAccountByAccount_no(int accountno);

}
