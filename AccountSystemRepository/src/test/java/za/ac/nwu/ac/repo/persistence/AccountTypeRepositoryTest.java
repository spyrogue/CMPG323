package za.ac.nwu.ac.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.config.RepositoryTestConfig;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class AccountTypeRepositoryTest {
    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Before
    public void setUp() throws Exception {
// What happens before each test
    }
    @After
    public void tearDown() throws Exception {
// What happens after each test
    }
    @Test
    public void getAccountTypeByMnemonicNativeQueryMiles() {
    AccountType miles = accountTypeRepository.getAccountTypeByMnemonicNativeQuery("MILES");
    assertNotNull(miles);
    assertEquals("MILES",miles.getMnemonic());

    }

    @Test
    public void getAccountTypeByMnemonicNativeQuery(){
        AccountType miles = accountTypeRepository.getAccountTypeByMnemonicNativeQuery("MILESSS");
        assertNull(miles);
    }

    @Test
    public void getAccountTypeByMnemonicMiles(){
        AccountType miles = accountTypeRepository.getAccountTypeByMnemonic("MILES");
        assertNotNull(miles);
        assertEquals("MILES",miles.getMnemonic());
    }

    @Test
    public void getAccountTypeByMnemonicPlay(){
        AccountType miles = accountTypeRepository.getAccountTypeByMnemonic("PLAY");
        assertNotNull(miles);
        assertEquals("PLAY",miles.getMnemonic());
    }

    @Test
    public void getAccountTypeByMnemonic(){
        AccountType miles = accountTypeRepository.getAccountTypeByMnemonic("R");
        assertNull(miles);
    }

    @Test
    public void getAccountTypeDtoByMnemonicMiles(){
        AccountTypeDto miles = accountTypeRepository.getAccountTypeDtoByMnemonic("MILES");
        assertNotNull(miles);
        assertEquals("MILES",miles.getMnemonic());
    }

    @Test
    public void getAccountTypeDtoByMnemonicPlay(){
        AccountTypeDto miles = accountTypeRepository.getAccountTypeDtoByMnemonic("PLAY");
        assertNotNull(miles);
        assertEquals("PLAY",miles.getMnemonic());
    }

    @Test
    public void getAccountTypeDtoByMnemonic(){
        AccountTypeDto miles = accountTypeRepository.getAccountTypeDtoByMnemonic("R");
        assertNull(miles);
    }


}
