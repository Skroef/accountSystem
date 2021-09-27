package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.repo.persistance.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;
import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;
import za.ac.nwu.accountSystem2.domain.persistence.AccountType;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {
    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDto> getAllAccountType(){
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try{
            for (AccountType accountType : accountTypeRepository.findAll()){
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        } catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return accountTypeDtos;
    }

//    @Override
//    public List<AccountTypeDto> getAllAccountTypes() {
//        return null;
//    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto){
        try{
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountType());
            return new AccountTypeDto(accountType);
        } catch(Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }
//
//    @Override
//    public AccountTypeDto getAccountTypesByMnemonicNativeQuery(String mnemonic) {
//        return null;
//    }

}
