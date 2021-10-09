package za.ac.nwu.ac.logic.flow.impl;


import org.springframework.stereotype.Component;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;

import java.time.LocalDate;

@Component("createAccountTypeFlowName")
public class CreateAccountTypeFlowImpl implements CreateAccountTypeFlow {

    private final AccountTypeTranslator accountTypeTranslator;

    public CreateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountType)
    {
        if(null == accountType.getCreationDate())
        {
            accountType.setCreationDate(LocalDate.now());
        }
        /*accountTypeTranslator.someMethod();*/
        return accountTypeTranslator.create(accountType);
    }
}
