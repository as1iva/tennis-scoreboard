package org.as1iva.repository;

import org.as1iva.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository() {
        super(Player.class);
    }

    public Optional<Player> findByName(String name) {
        try(Session session = sessionFactory.openSession()) {

            return Optional.ofNullable(
                    session.createQuery("FROM Player WHERE name = :name", Player.class)
                            .setParameter("name", name)
                            .uniqueResult()
            );
        }
    }
}
