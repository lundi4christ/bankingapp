package security.app.secure.service;

import security.app.secure.entity.SavingsAccount;

import java.math.BigDecimal;

public interface SavingsAccountService {

    SavingsAccount deposit(BigDecimal depositamt, long id);

    SavingsAccount debit(BigDecimal debitamt, long id);

    SavingsAccount transferfund(int debitaccount, BigDecimal debitamt, int depositaccount);

    SavingsAccount findbyaccountbyid(Long id);

    SavingsAccount findSavingsAccountByAccount (String account);

    SavingsAccount findSavingsAccountByAccountno(int accountno);

   // long transfer(long id);

}
