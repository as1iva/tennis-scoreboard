package org.as1iva.repository;

import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.as1iva.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable, E> implements CrudRepository<K, E> {

    private final Class<E> entityClass;
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public E save(E entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try(Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        }
    }

    @Override
    public List<E> findAll() {
        try(Session session = sessionFactory.openSession()) {
            CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
            criteria.from(entityClass);
            return session.createQuery(criteria).getResultList();
        }
    }
}
