package ee.asaarnak.library.pages.book;

import ee.asaarnak.library.entities.Book;

/**
 * Page for ../book/insert
 * 
 * @author Allar Saarnak
 * 
 */
public class InsertBook extends AbstractBook {
  void onPrepareFromBook() {
    if (getBook() == null) {
      setBook(new Book());
    }
  }

  Object onSuccess() {
    getDao().insert(getBook());
    return Index.class;
  }

  @Override
  public String getTitleMessage() {
    return getMessageByKey("title.insert");
  }

  public String getSubmitButtonMessage() {
    return getMessageByKey("submit.insert");
  }
}
