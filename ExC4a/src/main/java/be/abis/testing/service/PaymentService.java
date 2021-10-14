package be.abis.testing.service;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Person;
import be.abis.testing.utility.SecurityChecker;

public interface PaymentService {

    void paySalary(Person person) throws SalaryTooLowException;

}
