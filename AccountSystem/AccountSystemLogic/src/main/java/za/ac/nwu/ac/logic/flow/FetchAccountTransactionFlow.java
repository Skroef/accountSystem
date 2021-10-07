package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;

import java.util.List;

public interface FetchAccountTransactionFlow {

    List<AccountTransactionDto> getAllAccountTransactions();

//    AccountTypeDto create(AccountTypeDto accountType);

//    AccountTypeDto getAccountTransactionsByMemberId(String memberId);
}
