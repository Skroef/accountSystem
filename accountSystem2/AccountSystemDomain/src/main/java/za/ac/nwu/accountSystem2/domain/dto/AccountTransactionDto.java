package za.ac.nwu.accountSystem2.domain.dto;

import za.ac.nwu.accountSystem2.domain.persistence.AccountTransaction;
import za.ac.nwu.accountSystem2.domain.persistence.AccountType;

import java.time.LocalDate;
import java.util.Objects;

public class AccountTransactionDto {

    private Long transactionId;
    private AccountType accountType;
    private Long memberId;
    private LocalDate transactionDate;

    public AccountTransactionDto(Long transactionId, AccountType accountType, Long memberId, LocalDate transactionDate){
        this.accountType = accountType;
        this.memberId = memberId;
        this.transactionDate = transactionDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(accountType, that.getAccountType()) && Objects.equals(memberId, that.getMemberId()) && Objects.equals(transactionDate, that.getTransactionDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(transactionId, accountType, memberId, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                ", accountType=" + accountType +
                ", memberId=" + memberId +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
