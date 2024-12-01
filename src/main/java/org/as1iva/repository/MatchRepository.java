package org.as1iva.repository;

import jakarta.persistence.EntityManager;
import org.as1iva.entity.Match;

public class MatchRepository extends BaseRepository<Long, Match> {

    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }
}
