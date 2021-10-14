package be.abis.testing;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import be.abis.testing.service.AbisPaymentService;
import be.abis.testing.service.PaymentService;
import be.abis.testing.utility.SecurityChecker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks PaymentService ps = new AbisPaymentService();

    @Mock Person person;
    @Mock SecurityChecker securityChecker;

    @Before
    public void setUp(){
      //ps = new AbisPaymentService();
    }

    @Test(expected= SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        when(securityChecker.isAbisEmployee(person)).thenReturn(true);
        when(person.calculateNetSalary()).thenThrow(SalaryTooLowException.class);
        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
       // verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
    }

    @Test
    public void paySalaryToAbisEmployee() throws SalaryTooLowException {
        when(securityChecker.isAbisEmployee(any(Person.class))).thenReturn(true);
        when(person.calculateNetSalary()).thenReturn(3000.0);
        when(person.getFirstName()).thenReturn("John");
        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
    }

}