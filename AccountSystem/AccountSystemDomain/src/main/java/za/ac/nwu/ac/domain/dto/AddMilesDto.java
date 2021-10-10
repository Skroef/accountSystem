package za.ac.nwu.ac.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.Member;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "MEMBER", description = "A DTO that represents the Discovery Member")

public class AddMilesDto implements Serializable {

    private static final long serialVersionUID = -3675411777951570019L;

    private String contactNumber;
    private long miles;
//    private LocalDate startDate;

    public AddMilesDto(){
    }

    public AddMilesDto(String contactNumber, long miles) {

        this.contactNumber = contactNumber;
        this.miles = miles;
//        this.startDate = startDate;
    }

    public AddMilesDto(Member member){
        this.setContactNumber(member.getContactNumber());
//        this.setStartDate(member.getStartDate());
        this.setMiles(member.getMiles());
    }

    @ApiModelProperty(position = 1,
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


    @ApiModelProperty(position = 2,
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
        AddMilesDto that = (AddMilesDto) o;
        return miles == that.miles && Objects.equals(contactNumber, that.contactNumber);
    }

//    @JsonIgnore
//    public Member getMember(){
//        return new Member(getContactNumber(), getMiles());
//    }


    @Override
    public int hashCode() {
        return Objects.hash(contactNumber, miles);
    }


    @Override
    public String toString() {
        return "AddMilesDto{" +
                "contactNumber='" + contactNumber + '\'' +
                ", miles=" + miles +
                '}';
    }
}
