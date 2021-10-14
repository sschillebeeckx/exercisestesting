package be.abis.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PersonTest.class, AddressTest.class, CompanyTest.class})
public class MyTestSuite {
}
