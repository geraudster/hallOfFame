package fr.gddb.halloffame.model;

import java.util.ArrayList;

public class Ranks extends ArrayList<Rank>{
  public Rank findByUser(User user) {
    //this.indexOf(user)
    return  null;
  }
  
  public int getUserRank(User user) {
    int pos = 0;
    for(Rank rank : this) {
      if(rank.user.equals(user)) {
        break;
      }
      pos++;
    }
    return pos;
  }
}
