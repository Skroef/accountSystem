package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;

import java.util.List;

public interface AccountTransactionTranslator {

    List<AccountTransactionDto> getAllAccountTransactions();

    AccountTransactionDto create(AccountTransactionDto accountTransaction);
//
//    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
}
