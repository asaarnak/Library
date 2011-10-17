package ee.asaarnak.library.pages.author;

import javax.inject.Inject;

import ee.asaarnak.library.dal.CrudServiceDAO;
import ee.asaarnak.library.entities.Author;
import ee.asaarnak.library.pages.base.BasePage;

/**
 * Page for ../author/create
 * 
 * @author Allar Saarnak
 * 
 */
public abstract class AbstractAuthor extends BasePage {
  private Author author;
  @Inject
  private CrudServiceDAO dao;

  public abstract String getTitleMessage();

  public Author getAuthor() {
    return author;
  }

  public CrudServiceDAO getDao() {
    return dao;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}
