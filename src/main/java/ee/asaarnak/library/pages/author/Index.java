package ee.asaarnak.library.pages.author;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;

import ee.asaarnak.library.dal.CrudServiceDAO;
import ee.asaarnak.library.entities.Author;
import ee.asaarnak.library.pages.base.BasePage;

/**
 * Page for ../author
 * 
 * @author Allar Saarnak
 */
public class Index extends BasePage {
  @Inject
  private CrudServiceDAO dao;
  private List<Author> authors;
  private Author author;
  @Persist(PersistenceConstants.FLASH)
  private String errorMessage;

  /**
   * Delete the author from the database unless they've been modified elsewhere
   */
  void onDelete(UUID id, Integer version) {
    try {// FIXME this should be on the DAO LAYER
      Author author = dao.find(Author.class, id);
      if (!author.getVersion().equals(version)) {
        errorMessage = getMessageByKey("error.delete");
      }
      else {
        dao.delete(author);
      }
    }
    catch (Exception e) {
      // Display the cause. In a real system we would try harder to get a
      // user-friendly message.
      errorMessage = ExceptionUtils.getRootCauseMessage(e);
    }
  }

  void setupRender() {
    authors = dao.findWithNamedQuery(Author.ALL);
  }

  /**
   * FIXME This is extreamly inefficient, refactor by creating a dto with
   * field hasBooks. Or consider using EAGER loading type, which is also bad
   * practice usually.
   */
  public boolean getCanDeleteAuthor() {
    return isEmpty(author.getBooks());
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Author getAuthor() {
    return author;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}
