package fr.gddb.halloffame.model;

import java.util.ArrayList;

public class Submissions extends ArrayList<Submission> {
  public Submissions findByUser(User u) {
    Submissions submissions = new Submissions();
    for(Submission submission : this) {
      if(submission.getUser().equals(u)) {
        submissions.add(submission);
      }
    }
    return submissions;
  }
}
