package ee.asaarnak.library.entities.base;

import static java.util.UUID.randomUUID;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Type;

/**
 * Abstract superclass for persistent entities.
 *  
 * @id 
 * Using UUID instead of sequence to create id before database even knows
 * that insert is coming :).<br/>
 * This enables to correctly override hashCode() and equals() methods. <br/>
 * Which for example enables to correctly use java.util.Set. <br/>
 * This also enable to insert rows off-line and synchronize later.<br/>
 * And doesn't require to do an extra select to get the sequence id before
 * insert. <br/>
 * <br/>
 * The negative side is that UUID takes little more memory and
 * indexes(joins) are little slower, <br/>
 * its fine for most projects in the world :). <br/>
 * <br/>
 * For more info see {@link http://en.wikipedia.org/wiki/Universally_unique_identifier}<br/>
 * PS: The probability of one duplicate would be about 50% if every person
 * on earth owns 600 million UUIDs.
 * 
 * @version
 * Because we used off-line UUID for our entities, the hibernate will
 * determine transient entity by version. For example session.saveOrUpdate()
 * uses version instead of id.
 * 
 * @author Allar Saarnak
 * 
 */
@MappedSuperclass
public abstract class PersistentHibernateImpl implements Persistent {

  private static final long serialVersionUID = -6781498776713390810L;

  private UUID id = randomUUID();
  private Integer version;

  /**
   * @return True if o.id = this.id
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof PersistentHibernateImpl) {
      PersistentHibernateImpl other = (PersistentHibernateImpl) obj;
      return (id == other.id) || (id != null && id.equals(other.id));
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
  
  @Override
  @Id
  @NonVisual
  @Type(type = "org.hibernate.type.PostgresUUIDType")
  public UUID getId() {
    return id;
  }

  @Override
  @Version
  @NonVisual
  public Integer getVersion() {
    return version;
  }

  /**
   * Used by hibernate when selecting from database.
   */
  @Override
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Used by hibernate when selecting from database.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * A meaningful name for entitys.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + "[id=" + id + "]";
  }
}
