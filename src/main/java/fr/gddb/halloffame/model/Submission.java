package fr.gddb.halloffame.model;

public class Submission {
  public User user;
  public String payload;
  public int evaluateScore() {
    return payload.length();
  }
}
