package ee.asaarnak.library.commons;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.Transient;

import ee.asaarnak.library.entities.base.Persistent;

/**
 * Simple class with static methods for determining if entity is persistent or
 * transient. Is entity saved to database or not.
 * 
 * @author Allar Saarnak
 * 
 */
public class PersistentUtil {
  /**
   * @return true if list=(empty or null) or at least one list.item == null or
   *         list.item.version == null
   */
  public static final boolean isNotPersistent(List<? extends Persistent> entitys) {
    return !isPersistent(entitys);
  }

  /**
   * @return true if list=(empty or null) or at least one list.item == null or
   *         list.item.version == null
   */
  @Transient
  public static final boolean isNotPersistent(Persistent... entitys) {
    return !isPersistent(entitys);
  }

  /**
   * @return true if list !(empty or null) and list.items != null and
   *         list.items.version != null
   */
  public static final boolean isPersistent(List<? extends Persistent> entitys) {
    if (isEmpty(entitys)) {
      return false;
    }
    return isPersistent(entitys.toArray(new Persistent[entitys.size()]));
  }

  /**
   * @return true if list !(empty or null) and list.items != null and
   *         list.items.version != null
   */
  @Transient
  public static final boolean isPersistent(Persistent... entitys) {
    if (entitys == null || entitys.length < 1) {
      return false;
    }
    for (Persistent entity : entitys) {
      if (entity == null || entity.getVersion() == null) {
        return false;
      }
    }
    return true;
  }
}
