package fr.gddb.halloffame.model;

import org.joda.time.DateTime;

public class Submission {
  private User user;
  private String payload = "";
  public int evaluateScore() {
    return getPayload().length();
  }
  private String comment = "";
  private DateTime submissionDate;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public DateTime getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(DateTime submissionDate) {
    this.submissionDate = submissionDate;
  }
}
