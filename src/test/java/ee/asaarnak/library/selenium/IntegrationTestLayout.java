package ee.asaarnak.library.selenium;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class IntegrationTestLayout extends SeleniumTestCase {

	/**
	 * Used to test if link Books works.
	 */
    @Test
    public void navigationBar()
    {
        open("/");
        clickAndWait("link=Books");
    }
}
