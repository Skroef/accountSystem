package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import za.ac.nwu.ac.domain.persistence.Member;

import java.time.LocalDate;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //    Getting Data from only 1 Table
    @Query(value = "SELECT "
            + "at"
            + " FROM "
            + " Member at "
            + "WHERE at.contactNumber = :contactNumber")
    Member getMemberByContactNumber(long contactNumber);

    //   Delete entity from DB VIA mnemonic
    @Modifying
    @Query(value = "DELETE "
            + " FROM "
            + " Member at "
            + "WHERE at.contactNumber = :contactNumber")
    void deleteMember(long contactNumber);


    //      Update entity in the DB
//    @Modifying
//    @Query(value = "UPDATE "
//            + "Member at"
//            + " SET at.miles = :newMiles, "
//            + " WHERE at.contactNumber = :contactNumber ")
//    void updateMiles(long newMiles, long contactNumber);
}
