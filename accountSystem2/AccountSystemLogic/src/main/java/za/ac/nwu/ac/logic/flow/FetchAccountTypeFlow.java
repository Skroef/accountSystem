package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;
import java.util.List;

public interface FetchAccountTypeFlow {
    List<AccountTypeDto> getAllAccountTypes();

//    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);
//
//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
}
