package be.abis.testing.ut;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PersonTest.class, AddressTest.class, CompanyTest.class, FileAddressRepositoryTest.class})
public class MyTestSuite {
}
