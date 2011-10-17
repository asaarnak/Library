package ee.asaarnak.library.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import ee.asaarnak.library.entities.base.PersistentHibernateImpl;

/**
 * Author entity.
 * 
 * @author Allar Saarnak
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = Author.ALL, query = "Select a from Author a"),
    @NamedQuery(name = Author.BY_BOOK, query = "Select b.author from Book b where b.id = :bookId") })
public class Author extends PersistentHibernateImpl {
  private static final long serialVersionUID = -2222844499651559265L;
  @NonVisual
  public static final String ALL = "Author.all";
  @NonVisual
  public static final String BY_BOOK = "Author.byBook";

  private List<Book> books;
  private String firstName;
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
  public List<Book> getBooks() {
    return books;
  }

  @Validate(value = "required")
  @Column(nullable = false)
  public String getFirstName() {
    return firstName;
  }

  @Transient
  public String getFullName() {
    return firstName + " " + lastName;
  }

  @Validate(value = "required")
  @Column(nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
