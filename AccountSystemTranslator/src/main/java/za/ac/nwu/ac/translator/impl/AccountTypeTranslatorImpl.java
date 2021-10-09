package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {
    /*public static final String[] ENTITY_PACKAGES_TO_SCAN = {"za.ac.nwu..ac.domain.persistence"};
    public static final String PERSISTENCE_UNIT_NAME = "account.system.persistence";*/
    private final AccountTypeRepository  accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic){
        try {
            AccountType accountType =  accountTypeRepository.getAccountTypeByMnemonicNativeQuery(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from DB",e);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic){
        try {
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from DB",e);
        }
    }


    @Override
    public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic){
        try {
            return accountTypeRepository.getAccountTypeDtoByMnemonic(mnemonic);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from DB",e);
        }
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes(){
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try{
            for (AccountType accountType : accountTypeRepository.findAll()) {
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        }catch (Exception e) {
            // TODO: Log
            throw new RuntimeException("Unable to read from DB",e);
        }
        return accountTypeDtos;
    }

    @Override
    public AccountTypeDto deleteAccountType(String mnemonic) {
        try {
            return accountTypeRepository.deleteAccountType(mnemonic);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from DB",e);
        }
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto) {
        try{
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountTypes());
            return new AccountTypeDto(accountType);
        }catch(Exception e)
        {
            throw new RuntimeException("Unable to save data the DB",e);
        }
    }

    @Override
    public void someMethod() {

    }

    /*public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("script/schema.sql")
                .addScript("script/data.sql")
                .build();
    }

    private Properties buildJpaProperties(){
        final Properties properties = new Properties();

        properties.setProperty("hibernate.dialect","org.hibernate.dialect.HSQL2Dialect");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        properties.setProperty("hibernate.connection.driver.class","org.hsqldb.jdbcDriver");
        properties.setProperty("hibernate.hibernate.hbm2ddl.auto","update");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(buildJpaProperties());
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        return entityManagerFactoryBean;
    }*/
}
