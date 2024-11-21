package com.PigeonSkyRace.Auth.repository;

import com.PigeonSkyRace.Auth.models.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitionRepository extends MongoRepository<Competition, String> {


}
