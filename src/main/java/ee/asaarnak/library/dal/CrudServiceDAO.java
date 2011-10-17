package ee.asaarnak.library.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import ee.asaarnak.library.entities.base.Persistent;

/**
 * CrudServiceDAO interface.
 * 
 * @author karesti
 * @author Allar Saarnak
 */
public interface CrudServiceDAO {

  /**
   * After a call to this method the entity will be persisted into database.
   * 
   * @param <T>
   * @param t
   * @return persisted Object
   */
  @CommitAfter
  <T extends Persistent> void insert(T t);

  /**
   * Creates a new object for the given type. After a call to this method the
   * entity will be persisted into database and then refreshed. Also current
   * persistent Session will be flushed.
   * 
   * @param <T>
   * @param t
   * @return persisted Object
   */
  @CommitAfter
  <T extends Persistent> T insertAndRefresh(T t);

  /**
   * Updates the given object.
   * 
   * @param <T>
   * @param t
   * @return persisted object
   */
  @CommitAfter
  <T extends Persistent> T update(T t);
  
  /**
   * Deletes the given object by id.
   * 
   * @param <T>
   * @param <PK>
   * @param type
   *          , entity class type
   * @param id
   */
  @CommitAfter
  <T extends Persistent, PK extends Serializable> void delete(Class<T> type, PK id);

  /**
   * Deletes the given object by entity.
   * 
   * @param <T>
   * @param <PK>
   * @param type
   *          , entity class type
   * @param id
   */
  @CommitAfter
  <T extends Persistent> void delete(T object);

  /**
   * Finds an object by id.
   * 
   * @param <T>
   * @param <PK>
   * @param type
   * @param id
   * @return the object
   */
  <T extends Persistent, PK extends Serializable> T find(Class<T> type, PK id);

  /**
   * Returns one result, query without parameters.
   * 
   * @param <T>
   * @param queryName
   * @return T object
   */
  <T extends Persistent> T findUniqueWithNamedQuery(String queryName);

  /**
   * Returns just one result with a named query and parameters.
   * 
   * @param <T>
   * @param queryName
   * @param params
   * @return T object
   */
  <T extends Persistent> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params);

  /**
   * Finds a list of objects for the given query name.
   * 
   * @param <T>
   * @param queryName
   * @return returns a list of objects
   */
  <T extends Persistent> List<T> findWithNamedQuery(String queryName);

  /**
   * Find a query with parameters.
   * 
   * @param <T>
   * @param queryName
   * @param params
   * @return resulting list
   */
  <T extends Persistent> List<T> findWithNamedQuery(String queryName, Map<String, Object> params);

}
