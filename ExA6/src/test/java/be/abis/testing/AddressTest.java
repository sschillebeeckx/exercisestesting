package be.abis.testing;



import be.abis.testing.model.Address;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddressTest {

    @Test
    public void belgianZipCodeShouldBeNumeric() {
        //arrange
        Address a = new Address("Diestsevest","32 bus 4B","3000","Leuven","Belgium","BE");

        //act
        boolean b = a.isBelgianZipCodeNumeric();

        //assert
        assertTrue(b);
    }
}
