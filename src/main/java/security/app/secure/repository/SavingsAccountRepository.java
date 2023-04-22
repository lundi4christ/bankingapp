package security.app.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.app.secure.entity.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
