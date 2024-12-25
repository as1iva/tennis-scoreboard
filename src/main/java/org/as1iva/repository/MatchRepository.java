package org.as1iva.repository;

import org.as1iva.entity.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Long, Match> {

    public MatchRepository() {
        super(Match.class);
    }

    public List<Match> findAllWithPagination(int page, int pageSize) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Match ORDER BY id DESC", Match.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    public List<Match> findByNameWithPagination(int page, int pageSize, String playerName) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Match WHERE firstPlayer.name LIKE :name OR secondPlayer.name LIKE :name ORDER BY id DESC", Match.class)
                    .setParameter("name", "%" + playerName + "%")
                    .setFirstResult((page - 1 ) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    public long count() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class).getSingleResult();
        }
    }

    public long countByName(String playerName) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match m " +
                    " WHERE firstPlayer.name LIKE :name OR secondPlayer.name LIKE :name", Long.class)
                    .setParameter("name", "%" + playerName + "%")
                    .getSingleResult();
        }
    }
}
