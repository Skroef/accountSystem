package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountTransaction", description = "A DTO that represents the AccountTransaction")
public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransactionDto(AccountType accountType, Long memberId, Long amount, LocalDate transactionDate){
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public AccountTransactionDto(AccountTransaction accountTransaction){
        this.setAccountType(accountTransaction.getAccountType());
        this.setMemberId(accountTransaction.getMemberId());
        this.setAmount(accountTransaction.getAmount());
    }

    @ApiModelProperty(position = 1,
            value = "AccountType Id",
            name = "AccountType",
            notes = "Uniquely identifies the account type",
            dataType = "java.lang.String",
            example = "1",
            required = true)
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @ApiModelProperty(position = 2,
            value = "AccountTransaction Member Id",
            name = "MemberId",
            notes = "Uniquely identifies the member",
            dataType = "java.lang.String",
            example = "2",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 3,
            value = "AccountTransaction Amount",
            name = "Amount",
            notes = "This is the amount of accountType",
            dataType = "java.lang.String",
            example = "200",
            required = true)
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @ApiModelProperty(position = 4,
            value = "AccountTransaction Transaction Date",
            name = "TransactionDate",
            notes = "This is the date on which the AccountTransaction was created",
            dataType = "java.lang.String",
            example = "2020-09-14",
            required = true)
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountType, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "accountType=" + accountType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
