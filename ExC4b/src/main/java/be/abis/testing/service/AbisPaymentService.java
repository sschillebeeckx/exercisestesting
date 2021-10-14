package be.abis.testing.service;


import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Person;
import be.abis.testing.utility.SecurityChecker;

public class AbisPaymentService implements PaymentService{

    SecurityChecker sc  =new SecurityChecker();

    public void paySalary(Person person) throws SalaryTooLowException {

        if (sc.isAbisEmployee(person)) {
            double salary = person.calculateNetSalary();
            System.out.println("Paying " + salary + " euros to " + person.getFirstName());
        }
    }

    public void paySalaryOld(Person person) throws SalaryTooLowException {

            double salary = person.calculateNetSalary();
            System.out.println("Paying " + salary + " euros to " + person.getFirstName());

    }

}
