package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.ac.nwu.ac.domain.persistence.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //    Getting Data from only 1 Table
    @Query(value = "SELECT "
            + "at"
            + " FROM "
            + " Member at "
            + "WHERE at.contactNumber = :contactNumber")
    Member getMemberByContactNumber(long contactNumber);
}
