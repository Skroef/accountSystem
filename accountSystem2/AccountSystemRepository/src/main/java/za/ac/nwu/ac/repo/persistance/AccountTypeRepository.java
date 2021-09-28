package za.ac.nwu.ac.repo.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.accountSystem2.domain.dto.AccountTypeDto;
import za.ac.nwu.accountSystem2.domain.persistence.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>{

    @Query(value = "SELECT"
            + "at"
            + "From"
            + "AccountType at"
            + "WHERE at.mnemonic = :mnemonic")
    AccountType getAccountTypeByMnemonic(String mnemonic);

    @Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto("
            + "at.mnemonic,"
            + "at.accountTypeName,"
            + "at.creationDate)"
            + "FROM" + "AccountType at"
            + "WHERE at.mnemonic = :mnemonic")
    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

}
