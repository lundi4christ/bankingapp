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
    public SavingsAccount deposit(Double depositamt, long id) {

        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);

        getaccount.setAccount_balance(getaccount.getAccount_balance().add(new BigDecimal(depositamt)));

        Date date = new Date();

        List<SavingsTransaction> savetranslist = new ArrayList<>();

        SavingsTransaction saveTrans = new SavingsTransaction(date,"transaction", getaccount.getAccount_balance(),
                getaccount.getAccount_balance(),"processed", "deposited");
        saveTrans.setSavingsAccount(getaccount);
        savetranslist.add(saveTrans);
        getaccount.setSavetransitems(savetranslist);

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }

    @Override
    public SavingsAccount debit(Double debitamt, long id) {
        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);

        getaccount.setAccount_balance(getaccount.getAccount_balance().subtract(new BigDecimal(debitamt)));

        Date date = new Date();

        List<SavingsTransaction> savetranslist = new ArrayList<>();

        SavingsTransaction saveTrans = new SavingsTransaction(date,"transaction", getaccount.getAccount_balance(),
                getaccount.getAccount_balance(),"processed", "debited");
        saveTrans.setSavingsAccount(getaccount);
        savetranslist.add(saveTrans);
        getaccount.setSavetransitems(savetranslist);

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }
}
