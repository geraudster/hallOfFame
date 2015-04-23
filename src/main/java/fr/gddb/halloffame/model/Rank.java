package fr.gddb.halloffame.model;

import org.joda.time.DateTime;

public class Rank implements Comparable<Rank>{
  public int score;
  public int previousRank;
  public DateTime submissionDate;
  
  // id
  public User user;

  @Override

  public int compareTo(Rank rank) {
    // TODO Auto-generated method stub
    return this.score < rank.score ? -1 : this.score == rank.score ? 0 : 1;
  }
    
}
