package za.ac.nwu.ac.logic.flow.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {

    private final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator = accountTypeTranslator;
    }


    @Override
    public AccountTypeDto deleteAccountType(String mnemonic){
        AccountTypeDto result = accountTypeTranslator.deleteAccountType(mnemonic);
        return result;
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountType){
        AccountTypeDto result = accountTypeTranslator.updateAccountType(accountType);
        return result;
    }
}
