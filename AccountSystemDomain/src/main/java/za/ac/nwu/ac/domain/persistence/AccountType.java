package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DEMO_ACCOUNT_TYPE",schema = "HR")
public class AccountType implements Serializable {
    private static final long serialVersionUID = 806829754665685140L;

    private Long memberId;
    private String memberPassword;
    private String memberName;
    private LocalDate joiningDate;

    private Set<AccountTransaction> accountTransactions;

    public AccountType() {
    }

    public AccountType(Long memberId, String memberPassword, String memberName, LocalDate joiningDate) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.joiningDate = joiningDate;
    }

    public AccountType(String memberPassword, String memberName, LocalDate joiningDate) {
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.joiningDate = joiningDate;
    }

    @Id
    @SequenceGenerator(name = "VIT_RSA_GENERIC_SEQ",sequenceName = "HR.VIT_RSA_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIT_RSA_GENERIC_SEQ")

    @Column(name = "ACCOUNT_TYPE_ID")
    public Long getAccountTypeId() {
        return memberId;
    }

    @Column(name = "MNEMONIC")
    public String getMnemonic() {
        return memberPassword;
    }

    @Column(name = "ACCOUNT_TYPE_NAME")
    public String getAccountTypeName() {
        return memberName;
    }

    @Column(name = "CREATION_DATE")
    public LocalDate getCreationDate() {
        return joiningDate;
    }


    @OneToMany(targetEntity = AccountTransaction.class, fetch = FetchType.LAZY,mappedBy = "accountType")
    public Set<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public void setAccountTypeId(Long memberId) {
        this.memberId = memberId;
    }
    public void setMnemonic(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    public void setAccountTypeName(String memberName) {
        this.memberName = memberName;
    }
    public void setCreationDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(memberPassword, that.memberPassword) && Objects.equals(memberName, that.memberName) && Objects.equals(joiningDate, that.joiningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberPassword, memberName, joiningDate);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "memberId=" + memberId +
                ", Password ='" + memberPassword + '\'' +
                ", Member name='" + memberName + '\'' +
                ", Joining date=" + joiningDate +
                '}';
    }
}
