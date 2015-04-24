package fr.gddb.halloffame.service;

import java.util.Collections;

import javax.inject.Named;

import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.model.Ranks;
import fr.gddb.halloffame.model.Submission;
import fr.gddb.halloffame.model.Submissions;

@Named
public class SubmissionService {
  public Submissions submissions = new Submissions();
  public Ranks ranks = new Ranks();
  
  public void submit(Submission submission) {
    submissions.add(submission);
    
    Rank previousRank = ranks.findByUser(submission.getUser());
    if(previousRank != null) {
      previousRank.setPreviousRank(previousRank.getScore());
    } else {
      previousRank = new Rank();
      previousRank.setUser(submission.getUser());
      ranks.add(previousRank);
    }
    previousRank.setScore(submission.evaluateScore());
    Collections.sort(ranks);
  }
  
}
