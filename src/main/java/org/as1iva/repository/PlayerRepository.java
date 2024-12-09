package org.as1iva.repository;

import org.as1iva.entity.Player;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository() {
        super(Player.class);
    }
}
