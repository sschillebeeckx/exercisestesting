package be.abis.testing.ut;



import be.abis.testing.model.Address;
import be.abis.testing.model.Company;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyTest {

    @Test
    public void taxForBelgianCompanyShouldBe51() {
        //arrange
        Address a = new Address("Diestsevest","32 bus 4B","3000","Leuven","Belgium","BE");
        Company c = new Company("ABIS",a);
        //act
        double taxToPay = c.calculateTaxToPay();

        //assert
        assertEquals(51.00,taxToPay,0.01);
    }


}
