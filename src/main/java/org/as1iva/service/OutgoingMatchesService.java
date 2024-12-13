package org.as1iva.service;

import org.as1iva.dto.MatchScoreDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OutgoingMatchesService {

    private static final Map<UUID, MatchScoreDto> currentMatches = new HashMap<>();

    public MatchScoreDto getMatchScore(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public void setMatchScore(UUID uuid, MatchScoreDto matchScoreDto) {
        currentMatches.put(uuid, matchScoreDto);
    }
}
