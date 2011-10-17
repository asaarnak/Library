package ee.asaarnak.library.pages.author;

import java.util.UUID;

import ee.asaarnak.library.entities.Author;
import ee.asaarnak.library.entities.Book;

/**
 * Page for ../author/update
 * 
 * @author Allar Saarnak
 * 
 */
public class ViewAuthor extends AbstractAuthor {
  private UUID authorId;
  private Book book;

  UUID onPassivate() {
    return authorId;
  }

  void onActivate(UUID authorId) {
    this.authorId = authorId;
    setAuthor(getDao().find(Author.class, authorId));
  }

  @Override
  public String getTitleMessage() {
    return getMessages().get("title.review");
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

}
