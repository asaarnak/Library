package ee.asaarnak.library.pages.book;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import ee.asaarnak.library.dal.CrudServiceDAO;
import ee.asaarnak.library.entities.Book;
import ee.asaarnak.library.pages.base.BasePage;

/**
 * Page for ../book
 * 
 * @author Allar Saarnak
 * 
 */
public class Index extends BasePage {
  @SuppressWarnings("unused")
  @Property
  private List<Book> books;
  @SuppressWarnings("unused")
  @Property
  private Book book;
  @Inject
  private CrudServiceDAO dao;
  @SuppressWarnings("unused")
  @Property
  @Persist(PersistenceConstants.FLASH)
  private String errorMessage;

  /**
   * Delete the book from the database unless it have been modified elsewhere
   */
  void onDelete(UUID id, Integer version) {
    try {// FIXME this should be on the DAO LAYER
      Book book = dao.find(Book.class, id);
      if (!book.getVersion().equals(version)) {
        errorMessage = getMessageByKey("error.delete");
      }
      else {
        dao.delete(book);
      }
    }
    catch (Exception e) {
      // Display the cause. In a real system we would try harder to get a
      // user-friendly message.
      errorMessage = ExceptionUtils.getRootCauseMessage(e);
    }
  }

  void setupRender() {
    books = dao.findWithNamedQuery(Book.ALL);
  }
}
