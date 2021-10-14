package be.abis.testing.ut;


import be.abis.testing.exception.PersonShouldBeAdultException;
import be.abis.testing.exception.SalaryTooLowException;
import be.abis.testing.model.Company;
import be.abis.testing.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

   private Person p1;
    @Mock
    Company mockCompany;

   @Before
   public void setUp(){
       p1 = new Person(1,"Ann","Smits", LocalDate.of(1979, 6, 28));
       //System.out.println("person created before test");
   }

    @Test
    @Category(MyTestCategories.AgeTests.class)
    public void person1ShouldBe42YearsOld() throws PersonShouldBeAdultException {
       //assertEquals(42,p1.calculateAge()); //correct value
        assertThat(p1.calculateAge(), is (equalTo(42)));
    }

    @Test
    public void toStringSentenceStartsWithPerson(){
        String sentence = p1.toString();
        assertThat(sentence, org.hamcrest.CoreMatchers.startsWith("Person"));
    }

    @Test(expected = PersonShouldBeAdultException.class)
    @Category(MyTestCategories.AgeTests.class)
    public void personUnder18ShouldThrowException() throws PersonShouldBeAdultException {
        Person p2 = new Person(2,"John","Doe",LocalDate.of(2007, 8, 10));
        p2.calculateAge();
    }

    @Test
    public void calculateNetSalaryOfBelgianPersonUsingMockCompany() throws SalaryTooLowException {
        Person p3 = new Person(3, "Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),mockCompany);
        p3.setGrossSalary(4000);
        when(mockCompany.calculateTaxToPay()).thenReturn(51.0);
        assertEquals(p3.calculateNetSalary(), 1960, 0.01);
        verify(mockCompany,times(1)).calculateTaxToPay();
    }

}