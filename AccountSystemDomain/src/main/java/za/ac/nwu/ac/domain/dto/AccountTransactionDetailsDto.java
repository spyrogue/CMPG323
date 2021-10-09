package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

public class AccountTransactionDetailsDto {
    String partnerName;
    Long numberOfItems;

    public AccountTransactionDetailsDto(AccountTransactionDetails details){

    }

    public AccountTransactionDetailsDto(String partnerName, Long numberOfItems) {
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;
    }


    @JsonIgnore
    public AccountTransactionDetails buildAccountTransactionDetails(AccountTransaction accountTransaction){
        return  new AccountTransactionDetails(accountTransaction,this.partnerName,this.numberOfItems);
    }

    @JsonIgnore
    public AccountTransactionDetails buildAccountTransactionDetails(){
        return new AccountTransactionDetails(this.partnerName,this.numberOfItems);
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Long getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
