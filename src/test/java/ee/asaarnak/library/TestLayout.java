package ee.asaarnak.library;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLayout extends Assert {

	/**
	 * Used to test the copyright sign in main page :)
	 */
	@Test
	public void copyright(){
		String appPackage = "ee.asaarnak.library";
		String appName = "App";
		PageTester tester = new PageTester(appPackage, appName, "src/main/webapp");
		Document doc = tester.renderPage("Index");
		assertEquals(doc.getElementById("copyright").getChildMarkup(), StringEscapeUtils.unescapeHtml4("&copy; Allar Saarnak 2011"));
	}
}
