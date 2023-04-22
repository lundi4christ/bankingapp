package security.app.secure.service;

import security.app.secure.entity.SavingsAccount;

public interface SavingsAccountService {

    SavingsAccount deposit(Double depositamt, long id);
    SavingsAccount debit(Double debitamt, long id);

}
