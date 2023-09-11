package security.app.secure.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.app.secure.entity.SavingsAccount;
import security.app.secure.entity.SavingsTransaction;
import security.app.secure.repository.SavingsAccountRepository;
import security.app.secure.service.SavingsAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Override
    public SavingsAccount deposit(BigDecimal depositamt, long id) {

        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);
        getaccount.setAccount_balance(getaccount.getAccount_balance().add(new BigDecimal(String.valueOf(depositamt))));

        Date date = new Date();

        List<SavingsTransaction> savetranslist = new ArrayList<>();

        SavingsTransaction saveTrans = new SavingsTransaction(date, "Transaction", getaccount.getAccount_balance(),
                getaccount.getAccount_balance(), "Processed", "Deposited");
        saveTrans.setSavingsAccount(getaccount);
        savetranslist.add(saveTrans);
        getaccount.setSavetransitems(savetranslist);

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }

    @Override
    public SavingsAccount debit(BigDecimal debitamt, long id) {
        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);

        getaccount.setAccount_balance(getaccount.getAccount_balance().subtract(new BigDecimal(String.valueOf(debitamt))));

        Date date = new Date();

        List<SavingsTransaction> savetranslist = new ArrayList<>();

        SavingsTransaction saveTrans = new SavingsTransaction(date, "transaction", getaccount.getAccount_balance(),
                getaccount.getAccount_balance(), "processed", "debited");
        saveTrans.setSavingsAccount(getaccount);
        savetranslist.add(saveTrans);
        getaccount.setSavetransitems(savetranslist);

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }

    @Override
    public SavingsAccount transferfund(int debitaccount, BigDecimal debitamt, int depositaccount) {

        SavingsAccount getdebitaccount = savingsAccountRepository.findSavingsAccountByAccountno(debitaccount);
        System.out.println("=========debitaccount=========== " + getdebitaccount.getAccountno());
        getdebitaccount.setAccount_balance(getdebitaccount.getAccount_balance().subtract(new BigDecimal(String.valueOf(debitamt))));

        Date date = new Date();

        List<SavingsTransaction> savetranslist = new ArrayList<>();

        SavingsTransaction saveTrans = new SavingsTransaction(date, "transaction", debitamt,
                getdebitaccount.getAccount_balance(), "processed", "debited");
        saveTrans.setSavingsAccount(getdebitaccount);
        savetranslist.add(saveTrans);
        getdebitaccount.setSavetransitems(savetranslist);

        savingsAccountRepository.save(getdebitaccount);

        //////////////////////////////////
        SavingsAccount getdepositaccount = savingsAccountRepository.findSavingsAccountByAccountno(depositaccount);
        getdepositaccount.setAccount_balance(getdepositaccount.getAccount_balance().add(new BigDecimal(String.valueOf(debitamt))));

        Date dates = new Date();

        List<SavingsTransaction> savetranslists = new ArrayList<>();

        SavingsTransaction saveTranss = new SavingsTransaction(dates, "transaction", debitamt,
                getdepositaccount.getAccount_balance(), "processed", "deposited");
        saveTranss.setSavingsAccount(getdepositaccount);
        savetranslist.add(saveTranss);
        getdepositaccount.setSavetransitems(savetranslists);

        savingsAccountRepository.save(getdepositaccount);

        return getdepositaccount;

    }

    @Override
    public SavingsAccount findbyaccountbyid(Long id) {
        return savingsAccountRepository.findSavingsAccountById(id);
    }

    @Override
    public SavingsAccount findSavingsAccountByAccount(String account) {
        return savingsAccountRepository.findSavingsAccountByAccount(account);
    }

    @Override
    public SavingsAccount findSavingsAccountByAccountno(int accountno) {
        return savingsAccountRepository.findSavingsAccountByAccountno(accountno);
    }

    /*public SavingsAccount findByAccountno(int account) {
        return savingsAccountRepository.findSavingsAccountByAccount_no(account);
    }*/

}
