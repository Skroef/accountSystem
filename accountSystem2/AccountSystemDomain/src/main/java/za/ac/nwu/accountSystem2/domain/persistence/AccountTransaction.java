package za.ac.nwu.accountSystem2.domain.persistence;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Project1")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 5849962462540518428L;
    private Long transactionId;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransaction(){

    }

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

    @Column(name = "MEMBER_ID")
    public Long getMemberId(){
        return memberId;
    }

    @Column(name = "AMOUNT")
    public Long getAmount(){
        return amount;
    }
    @Column(name = "TRANSACTION_DATE")
    public LocalDate getTransactionDate(){
        return transactionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){
        return accountType;
    }

    public void setTransactionId(Long transactionId) {

        this.transactionId = transactionId;
    }

    public void setAccountTypeId(AccountType accountType) {

        this.accountType = accountType;
    }

    public void setMemberId(Long memberId) {

        this.memberId = memberId;
    }

    public void setAmount(Long amount) {

        this.amount = amount;
    }

    public void setTransactionDate(LocalDate transactionDate) {

        this.transactionDate = transactionDate;
    }


}
