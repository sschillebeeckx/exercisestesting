package be.abis.testing;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Address;
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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    PaymentService ps=  new AbisPaymentService();

    Person person;

    @Mock Person person2;

    @Spy
    SecurityChecker securityChecker = new SecurityChecker();

    @Before
    public void setUp(){
        Address a = new Address("Diestsevest","32 bus 4B","3000","Leuven","Belgium","BE");
        Company c = new Company("Abis",a);
        person = new Person(1, "Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),c);
        ps =  new AbisPaymentService();
    }

    @Test(expected= SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        person.setGrossSalary(2000.0);
        //when(securityChecker.isAbisEmployee(person)).thenReturn(true);
        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
    }

    @Test
    public void paySalaryToAbisEmployee() throws SalaryTooLowException {
        person.setGrossSalary(4000);
        ps.paySalary(person);
        verify(securityChecker,atLeastOnce()).isAbisEmployee(person);
    }

    @Test
    public void simplepPaySalary() throws SalaryTooLowException {
        when(person2.calculateNetSalary()).thenReturn(4000.0);
        ps.paySalaryOld(person2);
        verify(person2,atLeastOnce()).calculateNetSalary();
    }
}