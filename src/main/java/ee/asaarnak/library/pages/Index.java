package ee.asaarnak.library.pages;

import java.util.Date;

import ee.asaarnak.library.pages.base.BasePage;

/**
 * Start page of application Library.
 * 
 * @author Allar Saarnak
 */
public class Index extends BasePage {
  public Date getCurrentTime() {
    return new Date();
  }
}
