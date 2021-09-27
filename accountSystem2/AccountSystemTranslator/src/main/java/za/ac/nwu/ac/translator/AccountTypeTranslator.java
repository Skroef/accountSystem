package za.ac.nwu.ac.translator;

import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;

import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto create(AccountTypeDto accountType);

//    List<AccountTypeDto> getAllAccountType();
//
//    AccountTypeDto getAccountTypesByMnemonicNativeQuery(String mnemonic);
}
