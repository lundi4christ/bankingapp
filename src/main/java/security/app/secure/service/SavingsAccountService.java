package security.app.secure.service;

import security.app.secure.entity.SavingsAccount;

import java.math.BigDecimal;

public interface SavingsAccountService {

    SavingsAccount deposit(BigDecimal depositamt, long id);

    SavingsAccount debit(BigDecimal debitamt, long id);

    SavingsAccount transferfund(long debitid, BigDecimal debitamt, long depositid);

//    SavingsAccount findByAcct(BigDecimal acct);

   // long transfer(long id);

}
