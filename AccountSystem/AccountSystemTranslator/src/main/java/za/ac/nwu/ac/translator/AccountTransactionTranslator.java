package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;

import java.util.List;

public interface AccountTransactionTranslator {

    List<AccountTransactionDto> getAllAccountTransactions();

//    AccountTypeDto create(AccountTypeDto accountType);
//
//    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
}
