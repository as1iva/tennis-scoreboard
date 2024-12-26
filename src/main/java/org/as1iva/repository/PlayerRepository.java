package org.as1iva.repository;

import lombok.Getter;
import org.as1iva.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    @Getter
    private static final PlayerRepository INSTANCE = new PlayerRepository();

    private PlayerRepository() {
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
