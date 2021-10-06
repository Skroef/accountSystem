package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import org.springframework.stereotype.Repository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    //    Getting Data from only 1 Table
    @Query(value = "SELECT "
            + "at"
            + " FROM "
            + " AccountType at "
            + "WHERE at.mnemonic = :mnemonic")
    AccountType getAccountTypeByMnemonic(String mnemonic);


//    Getting Data from more than 1 Table
//    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto("
//            + " at.mnemonic,"
//            + " at.accountTypeName,"
//            + " at.creationDate)"
//            + "FROM" + " AccountType at"
//            + " WHERE at.mnemonic = :mnemonic")
//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
}
