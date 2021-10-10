package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    //    Getting Data from only 1 Table
    @Query(value = "SELECT "
            + "at"
            + " FROM "
            + " AccountType at "
            + "WHERE at.mnemonic = :mnemonic")
    AccountType getAccountTypeByMnemonic(String mnemonic);


    //   Delete entity from DB VIA mnemonic
    @Modifying
    @Query(value = "DELETE "
            + " FROM "
            + " AccountType at "
            + "WHERE at.mnemonic = :mnemonic")
    void deleteAccountType(String mnemonic);


//      Update entity in the DB
    @Modifying
    @Query(value = "UPDATE "
        + "AccountType at"
        + " SET at.mnemonic = :mnemonic, "
            + "at.accountTypeName = :newAccountTypeName, "
            + "at.creationDate = :newCreationDate"
        + " WHERE at.mnemonic = :mnemonic ")
    void updateAccountType(String mnemonic, String newAccountTypeName, LocalDate newCreationDate);



//    Getting Data from more than 1 Table
//    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto("
//            + " at.mnemonic,"
//            + " at.accountTypeName,"
//            + " at.creationDate)"
//            + "FROM" + " AccountType at"
//            + " WHERE at.mnemonic = :mnemonic")
//    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);
}
