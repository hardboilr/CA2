package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Tobias Jacobsen
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({test.RestApiCompanyTest.class, test.RestApiPersonTest.class})
public class RestApiSuite {
}
