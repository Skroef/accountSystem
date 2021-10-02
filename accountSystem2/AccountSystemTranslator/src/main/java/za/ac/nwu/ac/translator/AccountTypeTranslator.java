package za.ac.nwu.ac.translator;

import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;

import java.util.List;

public interface AccountTypeTranslator {

    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto create(AccountTypeDto accountType);

//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
//
//    AccountTypeDto save(AccountType accountType);
}
