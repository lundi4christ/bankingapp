package security.app.secure.tcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.app.secure.service.SavingsAccountService;

@RestController
@RequestMapping("/api/trans")
public class SavingsTransController {

    @Autowired
    private SavingsAccountService savingsAccountService;

    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<?> deposit(@PathVariable long id, @PathVariable Double amount ){
        savingsAccountService.deposit(amount, id);
        return new ResponseEntity<>("amount deposited successfully", HttpStatus.OK);
    }

    @PutMapping("/debit/{id}/{amount}")
    public ResponseEntity<?> debit(@PathVariable long id, @PathVariable Double amount){
        savingsAccountService.debit(amount, id);
        return new ResponseEntity<>("amount debited successfully", HttpStatus.OK);
    }

}
