package za.ac.nwu.ac.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.Member;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "MEMBER", description = "A DTO that represents the Discovery Member")

public class MemberDto implements Serializable {

    private static final long serialVersionUID = -3675411777951570019L;

    private String name;
    private String surname;
    private String contactNumber;
    private long miles;
    private LocalDate startDate;

    public MemberDto(){
    }

    public MemberDto(String name, String surname, String contactNumber, long miles, LocalDate startDate) {
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.miles = miles;
        this.startDate = startDate;
    }

    public MemberDto(Member member){
        this.setName(member.getName());
        this.setSurname(member.getSurname());
        this.setContactNumber(member.getContactNumber());
        this.setStartDate(member.getStartDate());
        this.setMiles(member.getMiles());
    }

    @ApiModelProperty(position = 1,
            value = "Member Name",
            name = "Name",
            notes = "The name of the Discovery Member",
            dataType = "java.lang.String",
            example = "Ninno",
            allowEmptyValue = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(position = 2,
            value = "Member Surname",
            name = "Surname",
            notes = "The Surname of the Discovery Member",
            dataType = "java.lang.String",
            example = "Hattingh",
            allowEmptyValue = true)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ApiModelProperty(position = 3,
            value = "Contact Number",
            name = "ContactNumber",
            notes = "The Contact Number of the Discovery Member",
            dataType = "java.lang.String",
            example = "0780243567",
            required = true)
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @ApiModelProperty(position = 4,
            value = "Start Date",
            name = "StartDate",
            notes = "This is the date on which the Member was created",
            dataType = "java.lang.String",
            example = "2021-10-08",
            required = true)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @ApiModelProperty(position = 5,
            value = "Miles",
            name = "Miles",
            notes = "The current amount of Miles the Discovery Member has",
            dataType = "java.lang.long",
            example = "8000",
            required = true)
    public long getMiles() {
        return miles;
    }

    public void setMiles(long miles) {
        this.miles = miles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return contactNumber == memberDto.contactNumber && miles == memberDto.miles && Objects.equals(name, memberDto.name) && Objects.equals(surname, memberDto.surname) && Objects.equals(startDate, memberDto.startDate);
    }

    @JsonIgnore
    public Member getMember(){
        return new Member(getName(), getSurname(), getContactNumber(), getStartDate(), getMiles());
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, surname, contactNumber, miles, startDate);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contactNumber=" + contactNumber +
                ", miles=" + miles +
                ", startDate=" + startDate +
                '}';
    }
}
