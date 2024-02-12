package com.ensa.tests.services;

import com.ensa.tests.dtos.OperationDto;
import com.ensa.tests.dtos.Response;
import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.dtos.TransferDto;
import com.ensa.tests.entities.Account;
import com.ensa.tests.entities.Student;
import com.ensa.tests.exceptions.IllegalOperationException;
import com.ensa.tests.exceptions.NoSuchAccountException;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import com.ensa.tests.repos.AccountRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    @Autowired
    public AccountService(AccountRepo accountService) {
        this.accountRepo = accountService;
    }

    public ResponseEntity<Response<Account>> addAccount() {
        Account account= Account.builder()
                .rib(this.generateRIB())
                .balance(0.0)
                .build();

        accountRepo.saveAndFlush(account);
        return new ResponseEntity<>(
                new Response<>("Account has been created successfully, enjoy shopping",
                        account),
                HttpStatus.CREATED);
    }

    public ResponseEntity<Response<Account>> getAccountById(String accountId) throws NoSuchAccountException {
        if(accountRepo.findById(accountId).isEmpty())
            throw new NoSuchAccountException("There is no bank account with this id");
        return new ResponseEntity<>(
                new Response<>("Student has been retrieved successfully",
                        accountRepo.findById(accountId).get()),
                HttpStatus.OK);
    }

    public ResponseEntity<Response<String>> deleteById(String accountId) throws NoSuchAccountException {
        if(accountRepo.findById(accountId).isEmpty())
            throw new NoSuchAccountException("There is no bank account with this id to delete");
        accountRepo.deleteById(accountId);
        return new ResponseEntity<>(
                new Response<>("Account has been deleted successfully",
                        null),
                HttpStatus.OK);
    }

    // Method to get all students
    public List<Account> getAllAccounts(Integer page) {
        if(page<0) throw new IllegalArgumentException("please check the parameters you are passing");
        return accountRepo.findAll(PageRequest.of(page, 10)).toList();
    }

    @Transactional
    public ResponseEntity<Response<String>> transferAmount(TransferDto transferDto) throws NoSuchAccountException, IllegalOperationException {
        this.debitFromAccount(new OperationDto(transferDto.getFromAccountId(), transferDto.getAmount()));
        this.creditAccount(new OperationDto(transferDto.getToAccountId(), transferDto.getAmount()));
        return new ResponseEntity<>(
                new Response<>("Transfer is successful",
                        null),
                HttpStatus.OK);
    }

    private String generateRIB() {
        StringBuilder ribBuilder = new StringBuilder();

        // Generate 24 random digits
        Random random = new Random();
        for (int i = 0; i < 24; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            ribBuilder.append(digit);
        }

        return ribBuilder.toString();
    }

    public void debitFromAccount(OperationDto operationDto) throws NoSuchAccountException, IllegalOperationException {
        Optional<Account> account=accountRepo.findById(operationDto.getTargetId());
        if(operationDto.getAmount()<0) throw new IllegalArgumentException("The debit amount has to be positive");
        if(account.isEmpty()) throw new NoSuchAccountException("There is no bank account with this id");
        if(account.get().getBalance()<operationDto.getAmount()) throw new IllegalOperationException("We cannot debit the specified amount from account, the balance is insufficient");
        Account acc=account.get();
        acc.setBalance(acc.getBalance() - operationDto.getAmount());
        accountRepo.save(acc);
    }

    public void creditAccount(OperationDto operationDto) throws NoSuchAccountException, IllegalOperationException {
        Optional<Account> account=accountRepo.findById(operationDto.getTargetId());
        if(operationDto.getAmount()<0) throw new IllegalArgumentException("The credit amount has to be positive");
        if(account.isEmpty()) throw new NoSuchAccountException("There is no bank account with this specified id");
        Account acc=account.get();
        acc.setBalance(account.get().getBalance() + operationDto.getAmount());
        accountRepo.save(acc);
    }
}
