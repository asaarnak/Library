package ee.asaarnak.library.pages.book;

import java.util.UUID;

import ee.asaarnak.library.entities.Book;

/**
 * Page for ../author/view
 * 
 * @author Allar Saarnak
 * 
 */
public class ViewBook extends AbstractBook {
  private UUID bookId;

  UUID onPassivate() {
    return bookId;
  }

  void onActivate(UUID bookId) {
    this.bookId = bookId;
    setBook(getDao().find(Book.class, bookId));
  }

  @Override
  public String getTitleMessage() {
    return getMessageByKey("title.review");
  }
}
