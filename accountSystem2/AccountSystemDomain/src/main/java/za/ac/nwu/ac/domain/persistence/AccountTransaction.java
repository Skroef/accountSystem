package za.ac.nwu.ac.domain.persistence;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Project1")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 5849962462540518428L;
    private Long transactionId;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransaction(Long transactionId, AccountType accountType, Long memberId, Long amount, LocalDate transactionDate){
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    @Id
    @SequenceGenerator(name = "ACCOUNT_TRANSACTION_SEQ", sequenceName = "ACCOUNT_TRANSACTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_TRANSACTION_SEQ")

    @Column(name = "ACCOUNT_TRANSACTION_ID")
    public Long getTransactionId(){

        return transactionId;
    }
    public void setTransactionId(Long transactionId) {

        this.transactionId = transactionId;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId(){

        return memberId;
    }
    public void setMemberId(Long memberId) {

        this.memberId = memberId;
    }

    @Column(name = "AMOUNT")
    public Long getAmount(){

        return amount;
    }
    public void setAmount(Long amount) {

        this.amount = amount;
    }

    @Column(name = "TRANSACTION_DATE")
    public LocalDate getTransactionDate(){

        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {

        this.transactionDate = transactionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){

        return accountType;
    }
    public void setAccountType(AccountType accountType) {

        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountType, memberId, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountType=" + accountType +
                ", memberId=" + memberId +
                ", transactionDate=" + transactionDate +
                '}';
    }



}
