package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    //    Getting Data from only 1 Table
//    @Query(value = "SELECT "
//            + "at"
//            + " FROM "
//            + " AccountTransaction at "
//            + "WHERE at.memberId = :memberId")
//    AccountType getAccountTypeByMnemonic(String mnemonic);



}
