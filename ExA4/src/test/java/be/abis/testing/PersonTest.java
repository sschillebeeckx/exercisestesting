package be.abis.testing;


import be.abis.testing.exception.PersonShouldBeAdultException;
import be.abis.testing.model.Address;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {

    @Test
    public void person1ShouldBe42YearsOld() throws PersonShouldBeAdultException {
       Person p1 = new Person(1,"Ann","Smits", LocalDate.of(1979, 6, 28));
        //assertEquals(42,p1.calculateAge()); //correct value
        assertThat(p1.calculateAge(), is (equalTo(42)));
    }

    @Test
    public void toStringSentenceStartsWithPerson(){
        Person p1 = new Person(1,"Ann","Smits", LocalDate.of(1979, 6, 28));
        String sentence = p1.toString();
        assertThat(sentence, startsWith("Person"));
    }
}