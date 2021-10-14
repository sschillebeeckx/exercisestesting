package be.abis.testing;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import be.abis.testing.service.AbisPaymentService;
import be.abis.testing.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    PaymentService ps;

    @Mock
    Person person;

    @Before
    public void setUp(){
        ps = new AbisPaymentService();
    }

    @Test(expected= SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        when(person.calculateNetSalary()).thenThrow(SalaryTooLowException.class);
        ps.paySalary(person);
    }


}