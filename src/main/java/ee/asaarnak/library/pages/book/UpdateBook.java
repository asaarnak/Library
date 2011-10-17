package ee.asaarnak.library.pages.book;

import java.util.UUID;

import ee.asaarnak.library.entities.Book;

/**
 * Page for ../author/update
 * 
 * @author Allar Saarnak
 * 
 */
public class UpdateBook extends AbstractBook {
  private UUID bookId;

  UUID onPassivate() {
    return bookId;
  }

  void onActivate(UUID bookId) {
    this.bookId = bookId;
    setBook(getDao().find(Book.class, bookId));
  }

  Object onSuccess() {
    getDao().update(getBook());
    return Index.class;
  }

  @Override
  public String getTitleMessage() {
    return getMessageByKey("title.update");
  }

  public String getSubmitButtonMessage() {
    return getMessageByKey("submit.save");
  }
}
