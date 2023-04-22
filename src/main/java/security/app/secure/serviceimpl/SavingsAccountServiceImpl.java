package security.app.secure.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.app.secure.entity.SavingsAccount;
import security.app.secure.repository.SavingsAccountRepository;
import security.app.secure.service.SavingsAccountService;

import java.math.BigDecimal;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Override
    public SavingsAccount deposit(Double depositamt, long id) {

        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);

        getaccount.setAccount_balance(getaccount.getAccount_balance().add(new BigDecimal(depositamt)));

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }

    @Override
    public SavingsAccount debit(Double debitamt, long id) {
        SavingsAccount getaccount = savingsAccountRepository.findById(id).orElse(null);

        getaccount.setAccount_balance(getaccount.getAccount_balance().subtract(new BigDecimal(debitamt)));

        savingsAccountRepository.save(getaccount);

        return getaccount;
    }
}
