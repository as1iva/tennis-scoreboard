package org.as1iva.service;

import lombok.Getter;
import org.as1iva.dto.MatchScoreDto;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScoreDto> currentMatches = new ConcurrentHashMap<>();

    @Getter
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private OngoingMatchesService() {
    }

    public MatchScoreDto getMatchScore(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public UUID setMatchScore(MatchScoreDto matchScoreDto) {
        UUID uuid = UUID.randomUUID();

        currentMatches.put(uuid, matchScoreDto);
        return uuid;
    }

    public void removeMatchScore(UUID uuid) {
        currentMatches.remove(uuid);
    }
}
