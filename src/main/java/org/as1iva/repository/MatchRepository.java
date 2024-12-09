package org.as1iva.repository;

import org.as1iva.entity.Match;

public class MatchRepository extends BaseRepository<Long, Match> {

    public MatchRepository() {
        super(Match.class);
    }
}
