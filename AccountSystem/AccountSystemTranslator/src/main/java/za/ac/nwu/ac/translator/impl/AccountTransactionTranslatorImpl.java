package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {

    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository accountTransactionRepository){
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<AccountTransactionDto> getAllAccountTransactions(){

        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        try{
            for (AccountTransaction accountTransaction : accountTransactionRepository.findAll()){
                accountTransactionDtos.add(new AccountTransactionDto(accountTransaction));
            }
        }
        catch(Exception e){
//          Remember to do logging for errors
            throw new RuntimeException("Unable to read from the DB", e);
        }

        return accountTransactionDtos;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto){
        try{
            AccountTransaction accountTransaction = accountTransactionRepository.save(accountTransactionDto.getAccountTransaction());
            return new AccountTransactionDto(accountTransaction);
        }
        catch(Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }
//
//    @Override
//    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic){
//        try{
//            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
//            return new AccountTypeDto(accountType);
//        } catch(Exception e){
//            throw new RuntimeException("Unable to read from the DB", e);
//        }
//    }
}
