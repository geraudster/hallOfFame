package fr.gddb.halloffame.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.model.Submission;
import fr.gddb.halloffame.model.User;


public class SubmissionServiceTest {
  private SubmissionService submissionService;
   
  @Before
  public void init() {
  	  submissionService = new SubmissionService();
  }
  
  @Test
  public void shouldAddSubmission() {
    User u1 = new User();
    u1.uuid = UUID.randomUUID();
    u1.firstName = "firstName";
    u1.lastName = "lastName";
    u1.subscriptionDate = new DateTime();
    
    Submission submission = new Submission();
    submission.setUser(u1);
    submission.setPayload("payload");
    Assert.assertEquals(7, submission.evaluateScore());
    
    submissionService.submit(submission);
    Assert.assertEquals(1, submissionService.submissions.size());
    Assert.assertEquals(1, submissionService.ranks.size()); 
  }
  
  @Test
  public void shouldBeFirst(){
    User u = new User();
    u.uuid = UUID.randomUUID();
    u.firstName = "firstName1";
    u.lastName = "lastName1";
    u.subscriptionDate = new DateTime();
    
    Rank rankUser = new Rank();
    rankUser.setUser(u);
    rankUser.setScore(1);
    submissionService.ranks.add(rankUser);
    
    u = new User();
    u.uuid = UUID.randomUUID();
    u.firstName = "firstName2";
    u.lastName = "lastName2";
    u.subscriptionDate = new DateTime();
    
    rankUser = new Rank();
    rankUser.setUser(u);
    rankUser.setScore(10);
    submissionService.ranks.add(rankUser);
    
    User u2 = new User();
    u2.uuid = UUID.randomUUID();
    u2.firstName = "firstName-newsubmit";
    u2.lastName = "lastName-newsubmit";
    u2.subscriptionDate = new DateTime();
    
    Submission submission = new Submission();
    submission.setUser(u2);
    submission.setPayload("payload");
    
    submissionService.submit(submission);
    Assert.assertEquals("There should be 3 ranks", 3, submissionService.ranks.size());
    Assert.assertEquals("User should be second (idx=1)", 1, submissionService.ranks.getUserRank(u2));
  }
}
