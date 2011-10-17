package ee.asaarnak.library.pages.base;

import javax.inject.Inject;

import org.apache.tapestry5.ioc.Messages;

/**
 * Base page for all pages.
 * 
 * @author Allar Saarnak
 * 
 */
public abstract class BasePage {
  @Inject
  private Messages messages;

  /**
   * @return Messages resource bundle.
   */
  public Messages getMessages() {
    return messages;
  }

  /**
   * @param key message key
   * @return message value
   */
  public String getMessageByKey(String key) {
    return getMessages().get(key);
  }
}
