package fr.gddb.halloffame.ui;

import org.joda.time.DateTime;

import java.util.UUID;

import javax.inject.Inject;

import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalSplitPanel;
import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.model.Submission;
import fr.gddb.halloffame.model.User;
import fr.gddb.halloffame.service.HallOfFameService;
import fr.gddb.halloffame.service.SubmissionService;

@CDIUI("")
@Title("Hall of Fame")
public class HallOfFameUI extends UI {
    private Table             scoreBoard          = new Table();
    private Table             previousSubmissions = new Table();
    private IndexedContainer  datasource;
    private IndexedContainer  submissionsDatasource;
    private FormLayout        formLayout          = new FormLayout();
    private BeanFieldGroup<Submission>        editorFields        = new BeanFieldGroup<>(Submission.class);

    @Inject
    private HallOfFameService hallOfFameService;

    @Inject
    private SubmissionService submissionService;
    private String[]          fieldNames          = new String[]{"UUID",};

    @Override
    protected void init(VaadinRequest req) {
        // TODO Auto-generated method stub
        datasource = initDataSource();
        submissionsDatasource = initSubmissionsDataSource();
        initLayout();
        initForm();
        initScoreBoard();
        initPreviousSubmissions();
    }

    /**
     * 
     */
    private void initPreviousSubmissions() {
        previousSubmissions.setContainerDataSource(submissionsDatasource);
        previousSubmissions.setVisibleColumns(new String[]{"Date", "Comment"});
        previousSubmissions.setSelectable(true);
        previousSubmissions.setImmediate(true);
    }

    /**
     * @return
     */
    private IndexedContainer initDataSource() {
        IndexedContainer ic = new IndexedContainer();

        ic.addContainerProperty("User", String.class, "");
        ic.addContainerProperty("Score", Integer.class, 0);

        for (Rank rank : hallOfFameService.getRanks()) {
            Object id = ic.addItem();
            ic.getContainerProperty(id, "User").setValue(rank.getUser().firstName);
            ic.getContainerProperty(id, "Score").setValue(rank.getScore());
        }
        return ic;
    }

    private IndexedContainer initSubmissionsDataSource() {
        IndexedContainer ic = new IndexedContainer();

        ic.addContainerProperty("Date", DateTime.class, null);
        ic.addContainerProperty("Comment", String.class, "<no comment>");

        User u = new User();
        u.uuid = UUID.fromString("f3a5431e-ea89-11e4-954f-34e6d7071067");

        for (Submission submission : submissionService.submissions.findByUser(u)) {
            Object id = ic.addItem();
            ic.getContainerProperty(id, "Date").setValue(submission.getSubmissionDate());
            ic.getContainerProperty(id, "Comment").setValue(submission.getComment());
        }
        return ic;

    }

    private void initLayout() {
        VerticalSplitPanel vSplitPanel = new VerticalSplitPanel();

        HorizontalLayout splitPanel = new HorizontalLayout();
        vSplitPanel.addComponent(splitPanel);
        setContent(splitPanel);
        splitPanel.addComponent(scoreBoard);
        splitPanel.addComponent(previousSubmissions);
        splitPanel.addComponent(formLayout);
    }

    private void initForm() {
      final Submission submission = new Submission();
      BeanItem<Submission> submissionBean = new BeanItem<>(submission);
		editorFields.setItemDataSource(submissionBean);

            formLayout.addComponent(editorFields.buildAndBind("Comment", "comment"));
        SubmissionUploader receiver = new SubmissionUploader();
        Upload upload = new Upload("Upload submission", receiver);
        //upload.setButtonCaption("Start upload");
        upload.addSucceededListener(receiver);
        formLayout.addComponent(upload);

        Button submitButton = new Button();
        submitButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                User u = new User();
                u.uuid = UUID.fromString("f3a5431e-ea89-11e4-954f-34e6d7071067");
                submission.setUser(u);
              	 submission.setSubmissionDate(new DateTime());
                submissionService.submit(submission);
             Object id = submissionsDatasource.addItem();
                submissionsDatasource.getContainerProperty(id, "Date").setValue(submission.getSubmissionDate());
                submissionsDatasource.getContainerProperty(id, "Comment").setValue(submission.getComment());
                Notification.show(String.format("Submission added [%s]", submission.getComment()));
            }
        });
        formLayout.addComponent(submitButton);
        editorFields.setBuffered(false);
    }

    private void initScoreBoard() {
        scoreBoard.setContainerDataSource(datasource);
        scoreBoard.setVisibleColumns(new String[]{"User", "Score"});
        scoreBoard.setSelectable(true);
        scoreBoard.setImmediate(true);
    }
}
