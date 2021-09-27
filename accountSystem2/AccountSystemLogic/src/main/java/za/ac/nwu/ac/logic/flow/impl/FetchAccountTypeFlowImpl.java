package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;
import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component

public class FetchAccountTypeFlowImpl implements FetchAccountTypeFlow {

   private final AccountTypeTranslator accountTypeTranslator;

   @Autowired
    public FetchAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
       this.accountTypeTranslator = accountTypeTranslator;
   }

//   @Override
//   public List<AccountTypeDto> getAllAccountTypes(){
//       List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
//       accountTypeDtos.add(new AccountTypeDto("MILES","Miles", LocalDate.now()));
//       return accountTypeDtos;
//   }

   @Override
    public List<AccountTypeDto> getAllAccountTypes(){

       return accountTypeTranslator.getAllAccountTypes();
   }
//
//   @Override
//    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic){
//       return accountTypeTranslator.getAccountTypesByMnemonicNativeQuery(mnemonic);
//   }
}
