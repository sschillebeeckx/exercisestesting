package be.abis.testing;


import be.abis.testing.exception.PersonShouldBeAdultException;
import be.abis.testing.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {

   private Person p1;

   @Before
   public void setUp(){
       p1 = new Person(1,"Ann","Smits", LocalDate.of(1979, 6, 28));
       System.out.println("person created before test");
   }

    @Test
    public void person1ShouldBe42YearsOld() throws PersonShouldBeAdultException {
       //assertEquals(42,p1.calculateAge()); //correct value
        assertThat(p1.calculateAge(), is (equalTo(42)));
    }

    @Test
    public void toStringSentenceStartsWithPerson(){
        String sentence = p1.toString();
        assertThat(sentence, startsWith("Person"));
    }

    @Test(expected = PersonShouldBeAdultException.class)
    public void personUnder18ShouldThrowException() throws PersonShouldBeAdultException {
        Person p2 = new Person(2,"John","Doe",LocalDate.of(2007, 8, 10));
        p2.calculateAge();
    }

}