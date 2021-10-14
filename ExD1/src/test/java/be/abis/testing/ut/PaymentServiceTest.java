package be.abis.testing.ut;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Address;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import be.abis.testing.service.AbisPaymentService;
import be.abis.testing.utility.SecurityChecker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AbisPaymentService.class,SecurityChecker.class})
public class PaymentServiceTest {

    AbisPaymentService ps;

    Person person;

    @Spy
    SecurityChecker securityChecker = new SecurityChecker();

    @Before
    public void setUp(){
        Address a = new Address("Diestsevest","32 bus 4B","3000","Leuven","Belgium","BE");
        Company c = new Company("Abis",a);
        person = new Person(1, "Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),c);
        ps =  new AbisPaymentService();
    }

    @Test(expected=SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        person.setGrossSalary(2000);
        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
    }

    @Test
    public void paySalaryToAbisEmployee() throws SalaryTooLowException {
        person.setGrossSalary(4000);
        ps.paySalary(person);
        //verify(securityChecker).isAbisEmployee(person);
    }

    @Test
    public void canYouMockTheStaticMethod(){
        mockStatic(SecurityChecker.class);
        when(SecurityChecker.encryptPassword(any(String.class))).thenReturn("blabla");
        assertEquals("blabla",ps.findEncryptedPassword("guess"));
        // verify(securityChecker,atLeastOnce()).encryptPassword(any(String.class));
    }

}