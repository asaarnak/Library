package ee.asaarnak.library.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.asaarnak.library.entities.base.Persistent;

/**
 * Generic DAO with simple insert,update,delete,find methods for Hibernate ORM.
 * 
 * @author karesti
 * @author Allar Saarnak
 */
public class HibernateCrudServiceDAO implements CrudServiceDAO {
  private static final Logger LOG = LoggerFactory.getLogger(HibernateCrudServiceDAO.class);

  @Inject
  private Session session;

  @Override
  public <T extends Persistent> void insert(T entity) {
    LOG.debug("Inserting object: " + entity.toString());
    session.persist(entity);
  }

  @Override
  public <T extends Persistent> T insertAndRefresh(T entity) {
    insert(entity);
    session.flush();
    session.refresh(entity);
    return entity;
  }

  @Override
  public <T extends Persistent> T update(T entity) {
    LOG.debug("Updating object: " + entity.toString());
    session.merge(entity);
    return entity;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent, PK extends Serializable> void delete(Class<T> type, PK id) {
    LOG.debug("Deleting object: " + type.getSimpleName() + " id: " + id);
    T ref = (T) session.get(type, id);
    session.delete(ref);
  }

  @Override
  public <T extends Persistent> void delete(T entity) {
    LOG.debug("Deleting object: " + entity.toString());
    session.delete(entity);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent, PK extends Serializable> T find(Class<T> type, PK id) {
    LOG.debug("Searching object: " + type.getSimpleName() + " id: " + id);
    return (T) session.get(type, id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent> T findUniqueWithNamedQuery(String queryName) {
    return (T) session.getNamedQuery(queryName).uniqueResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params) {
    Set<Entry<String, Object>> rawParameters = params.entrySet();
    Query query = session.getNamedQuery(queryName);

    for (Entry<String, Object> entry : rawParameters) {
      query.setParameter(entry.getKey(), entry.getValue());

    }
    return (T) query.uniqueResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent> List<T> findWithNamedQuery(String queryName) {
    return session.getNamedQuery(queryName).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Persistent> List<T> findWithNamedQuery(String queryName, Map<String, Object> params) {
    Set<Entry<String, Object>> rawParameters = params.entrySet();
    Query query = session.getNamedQuery(queryName);

    for (Entry<String, Object> entry : rawParameters) {
      query.setParameter(entry.getKey(), entry.getValue());

    }
    return query.list();
  }
}
