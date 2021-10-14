package be.abis.testing;

import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Person;
import be.abis.testing.service.AbisPaymentService;
import be.abis.testing.service.PaymentService;
import be.abis.testing.utility.SecurityChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({AbisPaymentService.class,SecurityChecker.class})
public class PaymentServiceTest {

    @InjectMocks
    PaymentService ps = new AbisPaymentService();

    @Mock
    Person person;

    @Mock
    SecurityChecker securityChecker;


    @Test(expected=SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        when(securityChecker.isAbisEmployee(any(Person.class))).thenReturn(true);
        when(person.calculateNetSalary()).thenThrow(SalaryTooLowException.class);

        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(any(Person.class));
    }

    @Test
    public void paySalaryToAbisEmployee() throws SalaryTooLowException {
        when(securityChecker.isAbisEmployee(person)).thenReturn(true);
        when(person.calculateNetSalary()).thenReturn(4000.0);
        ps.paySalary(person);
        verify(securityChecker).isAbisEmployee(person);
    }

    @Test
    public void canYouMockTheStaticMethod(){
        mockStatic(SecurityChecker.class);
        when(SecurityChecker.encryptPassword(any(String.class))).thenReturn("guess");
        assertEquals("guess",ps.findEncryptedPassword("blabla"));
        PowerMockito.verifyStatic(SecurityChecker.class);
        SecurityChecker.encryptPassword(any(String.class));
    }

}