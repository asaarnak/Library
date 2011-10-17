package ee.asaarnak.library.pages.book;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.services.SelectModelFactory;

import ee.asaarnak.library.dal.CrudServiceDAO;
import ee.asaarnak.library.entities.Author;
import ee.asaarnak.library.entities.Book;
import ee.asaarnak.library.pages.base.BasePage;

/**
 * Abstract class used by CreateBook and UpdateBook pages.
 * 
 * @author Allar Saarnak
 * 
 */
public abstract class AbstractBook extends BasePage {
  private Book book;
  private SelectModel authorSelectModel;
  @Inject
  private SelectModelFactory selectModelFactory;
  @Inject
  private CrudServiceDAO dao;

  public abstract String getTitleMessage();

  public SelectModel getAuthorSelectModel() {
    return authorSelectModel;
  }

  public Book getBook() {
    return book;
  }

  public CrudServiceDAO getDao() {
    return dao;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  void setupRender() {
    List<Author> authors = dao.findWithNamedQuery(Author.ALL);
    authorSelectModel = selectModelFactory.create(authors, "fullName");
  }
}
