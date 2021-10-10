package za.ac.nwu.ac.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountType;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "Member",
        description = "A DTO that represents the Member" )
public class AccountTypeDto implements Serializable {

    private static final long serialVersionUID = 375561885870006305L;

    private String password;
    private String memberName;
    private LocalDate joiningDate;

    public AccountTypeDto() {
    }

    public AccountTypeDto(String password, String memberName, LocalDate joiningDate) {
        this.password = password;
        this.memberName = memberName;
        this.joiningDate = joiningDate;
    }

    public AccountTypeDto(AccountType accountType) {
        this.setAccountTypeName(accountType.getAccountTypeName());
        this.setCreationDate(accountType.getCreationDate());
        this.setMnemonic(accountType.getMnemonic());
    }

    @ApiModelProperty(position = 1,
            value = "Member name",
            name ="Password",
            notes ="uniquely identifies the member",
            dataType ="java.lang.String",
            example ="MILES",
            required = true)
    public String getMnemonic(){return password;
    }

    public void setMnemonic(String password) {
        this.password = password;
    }

    @ApiModelProperty(position = 2,
            value = "Member Name",
            name ="Name",
            notes ="Name of the member",
            dataType ="java.lang.String",
            example ="MILES",
            allowEmptyValue = false,
            required = true)
    public String getAccountTypeName() {
        return memberName;
    }

    public void setAccountTypeName(String memberName) {
        this.memberName = memberName;
    }

    @ApiModelProperty(position = 3,
            value = "Member creation date",
            name = "joiningDate",
            notes = "This is the date on which the Member was created",
            dataType = "java.lang.String",
            /*example = "2020-01-01",*/
            allowEmptyValue = true,
            required = false)
    public LocalDate getCreationDate() {
        return joiningDate;
    }

    public void setCreationDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }



    @JsonIgnore
    public AccountType getAccountTypes(){
        return new AccountType(getMnemonic(), getAccountTypeName(), getCreationDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypeDto that = (AccountTypeDto) o;
        return Objects.equals(password, that.password) && Objects.equals(memberName, that.memberName) && Objects.equals(joiningDate, that.joiningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, memberName, joiningDate);
    }

    @Override
    public String toString() {
        return "AccountTypeDto{" +
                "Password ='" + password + '\'' +
                ", Member name='" + memberName + '\'' +
                ", Joining Date=" + joiningDate +
                '}';

    }
}
