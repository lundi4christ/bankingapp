package security.app.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.app.secure.entity.SavingsTransaction;

public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long> {

}
