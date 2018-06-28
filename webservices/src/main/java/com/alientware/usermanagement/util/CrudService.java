package com.alientware.usermanagement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rahul.avaghan
 * @param <T>
 */
@Dependent
public class CrudService<T> {

    @PersistenceContext
    private EntityManager em;

    /**
     * Creates an entity by persisting it in the context.
     *
     * @param t the entity to persist of type T.
     */
    public void create(T t) {
        getEntityManager().persist(t);
        em.flush();
    }

    /**
     * Updates an entity. If the entity is not within the context, the method
     * merges the entity in the context and updates it.
     *
     * @param t entity to be updated by type T.
     * @return the updated entity.
     */
    public T update(T t) {
        T m = null;
        if (!em.contains(t)) {
            m = getEntityManager().merge(t);
        }
        getEntityManager().flush();
        return m;
    }

    /**
     * Deletes an entity from the context. Beforehand the entity is merged into
     * the context if not already within the context. If the operation fails any
     * of the listed exceptions will be thrown. Have a look at the EntityManager
     * class for further details on the Exceptions
     *
     * @param t the entity to delete.
     * @see EntityManager
     */
    public void delete(T t) {
        T m;
        if (!em.contains(t)) {
            m = getEntityManager().merge(t);
            getEntityManager().remove(m);
        }

    }

    /**
     * This is a convenience method. You could also call
     * this.getEntityManager.find(entityClass, id);
     *
     * @param entityClass the class of the entity to get.
     * @param id the id of the entity.
     * @return the entity from within the context.
     */
    public T find(Class<T> entityClass, Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Executes a named query and delivers the result as typed list.
     *
     * @param entityClass the class to process.
     * @param namedQuery the name of the query to be executed.
     * @return list of entities queried by the given named query.
     */
    public List<T> executeNamedQuery(Class<T> entityClass, String namedQuery) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, entityClass);
        return query.getResultList();
    }

    /**
     * Executes a named query and replaces the given map of constraints within
     * the named query placeholders.
     *
     * @param entityClass the entity class to process on.
     * @param namedQuery the name of the query.
     * @param params the parameters of the named query to be replaced.
     * @return the resulting list of the query.
     */
    public List<T> executeNamedQuery(Class<T> entityClass, String namedQuery, Map<String, Object> params) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, entityClass);

        for (Map.Entry<String, Object> entrySet : params.entrySet()) {
            query.setParameter(entrySet.getKey(), entrySet.getValue());
        }

        return query.getResultList();
    }

    /**
     * Creates a query depending on the parameters within the map. The map must
     * be filled with property names and values that should be added as query
     * constraints. The method returns the queried values.
     *
     * @param entityClass the class of the entities.
     * @param parameterValueMap a map of property names and corresponding
     * values.
     * @return the resulting query list.
     */
    public List<T> findByConstraints(Class<T> entityClass, Map<String, String> parameterValueMap) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<T> root = cq.from(entityClass);

        // Constructing list of parameters
        List<Predicate> predicates = new ArrayList<>();

        // Adding predicates in case of parameter not being null
        for (Map.Entry<String, String> entrySet : parameterValueMap.entrySet()) {
            predicates.add((Predicate) cb.equal(root.get(entrySet.getKey()), entrySet.getValue()));
        }

        //query itself
        cq.select(root).where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[]{}));

        //execute query and do something with result
        TypedQuery<T> typedQuery = getEntityManager().createQuery(cq);
        return typedQuery.getResultList();
    }

    /**
     * Finds all entities of this type.
     *
     * @param entityClass the class of the entity to process.
     * @return list of all entities of this type.
     */
    public List<T> findAll(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Finds a range of entities. The range must be specified.
     *
     * @param entityClass the class of the type to be process.
     * @param range an array to be found.
     * @return list of found entities.
     */
    public List<T> findRange(Class<T> entityClass, int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * @return the em
     */
    public EntityManager getEntityManager() {
        return em;
    }
}
