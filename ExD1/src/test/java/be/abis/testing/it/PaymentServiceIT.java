package be.abis.testing.it;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Address;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import be.abis.testing.service.AbisPaymentService;
import be.abis.testing.service.PaymentService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PaymentServiceIT {

    PaymentService ps;
    Person person;

    @Before
    public void setUp(){
        Address a = new Address("Diestsevest","32 bus 4B","3000","Leuven","Belgium","BE");
        Company c = new Company("Abis",a);
        person = new Person(1, "Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),c);
        ps =  new AbisPaymentService();
    }

    @Test(expected= SalaryTooLowException.class)
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException {
        person.setGrossSalary(2000);
        ps.paySalary(person);
    }

    @Test
    public void paySalaryToAbisEmployee() throws SalaryTooLowException {
        person.setGrossSalary(4000);
        ps.paySalary(person);
    }

    @Test
    public void encryptedPasswordIsReversed(){
        assertEquals("sseug",ps.findEncryptedPassword("guess"));
    }


}