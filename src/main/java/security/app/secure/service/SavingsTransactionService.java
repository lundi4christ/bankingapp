package security.app.secure.service;

import security.app.secure.entity.SavingsTransaction;

import java.util.List;

public interface SavingsTransactionService {

    SavingsTransaction saveTrans(SavingsTransaction savingsTransaction);
    List<SavingsTransaction> getTransList();
    SavingsTransaction findById(long id);

}
