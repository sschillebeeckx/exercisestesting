package be.abis.testing.service;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Person;

public class AbisPaymentService implements PaymentService{

    public void paySalary(Person person) throws SalaryTooLowException {
            double salary = person.calculateNetSalary();
            System.out.println("Paying " + salary + " euros to " + person.getFirstName());
    }
}
