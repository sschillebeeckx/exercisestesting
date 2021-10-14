package be.abis.testing.exception;

public class SalaryTooLowException extends Exception {
    public SalaryTooLowException(String message) {
         super(message);
    }
}
