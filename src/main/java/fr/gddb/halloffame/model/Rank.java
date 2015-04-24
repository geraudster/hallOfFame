package fr.gddb.halloffame.model;

import org.joda.time.DateTime;

public class Rank implements Comparable<Rank>{
  private int score;
  private int previousRank;
  private DateTime submissionDate;
  
  // id
  private User user;

  @Override

  public int compareTo(Rank rank) {
    // TODO Auto-generated method stub
    return this.getScore() < rank.getScore() ? -1 : this.getScore() == rank.getScore() ? 0 : 1;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getPreviousRank() {
    return previousRank;
  }

  public void setPreviousRank(int previousRank) {
    this.previousRank = previousRank;
  }

  public DateTime getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(DateTime submissionDate) {
    this.submissionDate = submissionDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
