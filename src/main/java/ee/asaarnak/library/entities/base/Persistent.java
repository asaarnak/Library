package ee.asaarnak.library.entities.base;

import java.io.Serializable;
import java.util.UUID;

/**
 * Interface for persistent entities.
 * Every persistent element should implement this interface.
 * 
 * @author Allar Saarnak
 * 
 */
public interface Persistent extends Serializable {
  public UUID getId();

  public Integer getVersion();

  public void setId(UUID id);

  public void setVersion(Integer version);
}
