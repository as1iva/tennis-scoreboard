package org.as1iva.service;

import org.as1iva.dto.MatchScoreDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScoreDto> currentMatches = new HashMap<>();

    public MatchScoreDto getMatchScore(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public UUID setMatchScore(MatchScoreDto matchScoreDto) {
        UUID uuid = UUID.randomUUID();

        currentMatches.put(uuid, matchScoreDto);
        return uuid;
    }
}
