package ee.asaarnak.library.pages.author;

import ee.asaarnak.library.entities.Author;

/**
 * Page for ../author/insert
 * 
 * @author Allar Saarnak
 * 
 */
public class InsertAuthor extends AbstractAuthor {
  void onPrepareFromAuthor() {
    if (getAuthor() == null) {
      setAuthor(new Author());
    }
  }

  Object onSuccess() {
    getDao().insert(getAuthor());
    return Index.class;
  }

  @Override
  public String getTitleMessage() {
    return getMessages().get("title.insert");
  }

  public String getSubmitButtonMessage() {
    return getMessageByKey("submit.insert");
  }
}
