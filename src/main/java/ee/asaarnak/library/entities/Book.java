package ee.asaarnak.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import ee.asaarnak.library.entities.base.PersistentHibernateImpl;

/**
 * Book entity.
 * 
 * @author Allar Saarnak
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = Book.ALL, query = "Select b from Book b"),
    @NamedQuery(name = Book.BY_AUTHOR, query = "Select b from Book b where b.author.id = :authorId") })
public class Book extends PersistentHibernateImpl {
  private static final long serialVersionUID = 2333965933536629687L;
  @NonVisual
  public static final String ALL = "Book.all";
  @NonVisual
  public static final String BY_AUTHOR = "Book.byAuthor";

  private Author author;
  private String heading;

  @Validate(value = "required")
  @JoinColumn(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  public Author getAuthor() {
    return author;
  }

  @Validate(value = "required")
  @Column(nullable = false)
  public String getHeading() {
    return heading;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public void setHeading(String heading) {
    this.heading = heading;
  }
}
