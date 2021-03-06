package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.nwu.ac.domain.persistence.Member;

import java.time.LocalDate;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //    Getting Data from only 1 Table
    @Query(value = "SELECT "
            + "at"
            + " FROM "
            + " Member at "
            + "WHERE at.contactNumber = :contactNumber")
    Member getMemberByContactNumber(String contactNumber);

    //   Delete entity from DB VIA mnemonic
    @Modifying
    @Query(value = "DELETE "
            + " FROM "
            + " Member at "
            + "WHERE at.contactNumber = :contactNumber")
    void deleteMember(String contactNumber);


    //      add miles to member in the DB
    @Modifying
    @Query(value = "UPDATE "
            + "Member at"
            + " SET at.name = :newName,"
            + " at.surname = :newSurname,"
            + " at.contactNumber = :contactNumber,"
            + " at.miles = :newMiles,"
            + " at.startDate = :newStartDate"
            + " WHERE at.contactNumber = :contactNumber")
    void addMiles(String newName, String newSurname, String contactNumber, Integer newMiles, LocalDate newStartDate);

    //      subtract miles to member in the DB
    @Modifying
    @Query(value = "UPDATE "
            + "Member at"
            + " SET at.name = :newName,"
            + " at.surname = :newSurname,"
            + " at.contactNumber = :contactNumber,"
            + " at.miles = :newMiles,"
            + " at.startDate = :newStartDate"
            + " WHERE at.contactNumber = :contactNumber")
    void subtractMiles(String newName, String newSurname, String contactNumber, Integer newMiles, LocalDate newStartDate);

}
