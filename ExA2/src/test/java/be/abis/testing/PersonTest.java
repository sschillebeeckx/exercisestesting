package be.abis.testing;


import be.abis.testing.exception.PersonShouldBeAdultException;
import be.abis.testing.model.Address;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void person1ShouldBe42YearsOld() throws PersonShouldBeAdultException {
      Person p1 = new Person(1,"Ann","Smits", LocalDate.of(1979, 6, 28));

        assertEquals(42,p1.calculateAge()); //correct value
        //assertEquals(41,p1.calculateAge()); //wrong value
    }
}