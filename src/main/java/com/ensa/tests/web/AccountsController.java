package com.ensa.tests.web;

import com.ensa.tests.dtos.OperationDto;
import com.ensa.tests.dtos.Response;
import com.ensa.tests.dtos.TransferDto;
import com.ensa.tests.entities.Account;
import com.ensa.tests.exceptions.IllegalOperationException;
import com.ensa.tests.exceptions.NoSuchAccountException;
import com.ensa.tests.services.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountsController {
    private final AccountService accountService;
    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Response<Account>> addAccount() {
        return accountService.addAccount();
    }

    // Method to get a student by ID
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CONTINUE)
    public ResponseEntity<Response<Account>> getAccountById(@RequestParam("id") String id) throws NoSuchAccountException {

        return accountService.getAccountById(id);
    }

    // Method to get all students
    @RequestMapping(method = RequestMethod.GET, value = "all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Account> getAllAccounts(@RequestParam(value = "page", defaultValue = "0") Integer page ){
        return accountService.getAllAccounts(page);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Response<String>> deleteById(@RequestParam("id") String id) throws NoSuchAccountException {
        return accountService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "transfers")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Response<String>> transferAmount(@RequestBody() TransferDto transferDto) throws NoSuchAccountException, IllegalOperationException {
        return accountService.transferAmount(transferDto);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PATCH, value = "deposits")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Response<String>> transferAmount(@RequestBody()OperationDto operationDto) throws NoSuchAccountException, IllegalOperationException {
        accountService.creditAccount(operationDto);
        return new ResponseEntity<>(
                new Response<>("Your deposit was successful",
                        null),
                HttpStatus.OK);
    }

}
