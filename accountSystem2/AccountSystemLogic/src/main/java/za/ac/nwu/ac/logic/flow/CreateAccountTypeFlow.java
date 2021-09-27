package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;

public interface CreateAccountTypeFlow {
    default AccountTypeDto create(AccountTypeDto accountTypeDto);
}
