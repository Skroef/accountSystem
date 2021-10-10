package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MEMBER", schema = "NINNO")

public class Member implements Serializable {

    private static final long serialVersionUID = 5604864958111056329L;

    private Long memberId;
    private String name;
    private String surname;
    private long contactNumber;
    private long miles;
    private LocalDate startDate;

//    private Set<AccountTransaction> accountTransactions;



    public Member(String name, String surname, long contactNumber, LocalDate startDate, long miles) {
        this.memberId = memberId;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.miles = miles;
        this.startDate = startDate;
    }

    public Member(long contactNumber, long miles){
    }

    @Id
    @SequenceGenerator(name = "MEMBER_SEQ", sequenceName = "NINNO.MEMBER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "CONTACT_NUMBER")
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name = "MILES")
    public long getMiles() {
        return miles;
    }

    public void setMiles(long miles) {
        this.miles = miles;
    }

    @Column(name = "STRAT_DATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

//    @OneToMany(targetEntity = AccountTransaction.class, fetch = FetchType.LAZY, mappedBy = "accountType", orphanRemoval = true, cascade = CascadeType.PERSIST)
//    public Set<AccountTransaction> getAccountTransactions(){
//        return accountTransactions;
//    }
//
//    public void setAccountTransactions(Set<AccountTransaction> accountTransactions){
//        this.accountTransactions = accountTransactions;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return contactNumber == member.contactNumber && miles == member.miles && Objects.equals(memberId, member.memberId) && Objects.equals(name, member.name) && Objects.equals(surname, member.surname) && Objects.equals(startDate, member.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, surname, contactNumber, miles, startDate);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contactNumber=" + contactNumber +
                ", miles=" + miles +
                ", startDate=" + startDate +
                '}';
    }
}
