package fr.gddb.halloffame.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.service.HallOfFameService;
import fr.gddb.halloffame.service.SubmissionService;

@CDIUI("")
@Title("Hall of Fame")
public class HallOfFameUI extends UI {
    private Table             scoreBoard   = new Table();
    private IndexedContainer  datasource;
    private FormLayout        formLayout   = new FormLayout();
    private FieldGroup        editorFields = new FieldGroup();

    @Inject
    private HallOfFameService hallOfFameService;

    @Inject
    private SubmissionService submissionService;
    private String[]          fieldNames   = new String[]{"UUID",};

    @Override
    protected void init(VaadinRequest req) {
        // TODO Auto-generated method stub
        datasource = initDataSource();
        initLayout();
        initForm();
        initScoreBoard();
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
            ic.getContainerProperty(id, "User").setValue(rank.user.firstName);
            ic.getContainerProperty(id, "Score").setValue(rank.score);
        }
        return ic;
    }

    private void initLayout() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        setContent(splitPanel);
        splitPanel.addComponent(scoreBoard);
        splitPanel.addComponent(formLayout);
    }

    private void initForm() {
        TextField field = new TextField("UUID");
        formLayout.addComponent(field);
        field.setWidth("100%");
        editorFields.bind(field, "UUID");
        SubmissionUploader receiver = new SubmissionUploader();
        Upload upload = new Upload("Upload submission", receiver);
        upload.setButtonCaption("Start upload");
        upload.addSucceededListener(receiver);
        formLayout.addComponent(upload);

        editorFields.setBuffered(false);
    }

    private void initScoreBoard() {
        scoreBoard.setContainerDataSource(datasource);
        scoreBoard.setVisibleColumns(new String[]{"User", "Score"});
        scoreBoard.setSelectable(true);
        scoreBoard.setImmediate(true);
    }
}
