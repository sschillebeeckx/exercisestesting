package be.abis.testing.service;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Person;

public interface PaymentService {

    void paySalary(Person person) throws SalaryTooLowException;
    public String findEncryptedPassword(String pwd);
}
