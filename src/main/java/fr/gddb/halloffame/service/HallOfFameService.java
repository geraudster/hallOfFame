package fr.gddb.halloffame.service;

import org.joda.time.DateTime;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.model.Ranks;
import fr.gddb.halloffame.model.User;

@Named
public class HallOfFameService {
  
  @Inject
  private SubmissionService submissionService;
  
  public Ranks getRanks() {
    Ranks ranks = submissionService.ranks;
    Rank rank;
    User user;
    for(int i = 0; i < 10; i++) {
      user = new User();
      user.uuid = UUID.randomUUID();
      user.firstName = String.format("firstName_%02d", i);
      user.lastName = String.format("lastName_%02d", i);
      user.subscriptionDate = new DateTime();
      rank = new Rank();
      rank.user = user;
      rank.score = i;
      ranks.add(rank);
    }
    return ranks;
  }
}
