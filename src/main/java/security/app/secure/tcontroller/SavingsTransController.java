package security.app.secure.tcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.app.secure.repository.SavingsAccountRepository;
import security.app.secure.service.SavingsAccountService;
import security.app.secure.service.UserService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/trans")
public class SavingsTransController {

    @Autowired
    private SavingsAccountService savingsAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<?> deposit(@PathVariable long id, @PathVariable BigDecimal amount) {
        savingsAccountService.deposit(amount, id);
        return new ResponseEntity<>("Amount deposited successfully", HttpStatus.OK);
    }

    @PutMapping("/debit/{id}/{amount}")
    public ResponseEntity<?> debit(@PathVariable long id, @PathVariable BigDecimal amount) {
        savingsAccountService.debit(amount, id);
        return new ResponseEntity<>("Amount debited successfully", HttpStatus.OK);
    }

    @PutMapping("/transferamount/{debitaccount}/{debitamount}/{depositaccount}")
    public ResponseEntity<?> transfer(@PathVariable int debitaccount, @PathVariable BigDecimal debitamount, @PathVariable int depositaccount) {
        savingsAccountService.transferfund(debitaccount, debitamount, depositaccount);
        return new ResponseEntity<>("Amount debited and deposited successfully", HttpStatus.OK);
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<?> balance (@PathVariable long id){
//        savingsAccountService.checkbalance(id);
        return new ResponseEntity<>("this is the balance", HttpStatus.OK);
    }
}
