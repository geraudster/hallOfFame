package fr.gddb.halloffame.model;

import org.joda.time.DateTime;

public class Submission {
  public User user;
  public String payload;
  public int evaluateScore() {
    return payload.length();
  }
  public String comment;
  public DateTime submissionDate;
}
