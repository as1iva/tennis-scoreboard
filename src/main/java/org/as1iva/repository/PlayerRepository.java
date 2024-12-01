package org.as1iva.repository;

import jakarta.persistence.EntityManager;
import org.as1iva.entity.Player;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }
}
