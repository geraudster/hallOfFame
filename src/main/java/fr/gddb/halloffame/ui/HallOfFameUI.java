package fr.gddb.halloffame.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Title;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import fr.gddb.halloffame.model.Rank;
import fr.gddb.halloffame.service.HallOfFameService;
import fr.gddb.halloffame.service.SubmissionService;

@Title("Hall of Fame")
public class HallOfFameUI extends UI {
    private Table             scoreBoard = new Table();
    private IndexedContainer  datasource = initDataSource();
    private FormLayout        formLayout = new FormLayout();

    @Inject
    private HallOfFameService hallOfFameService;

    @Inject
    private SubmissionService submissionService;

    @Override
    protected void init(VaadinRequest req) {
        // TODO Auto-generated method stub
        initLayout();
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

    private void initScoreBoard() {
        scoreBoard.setContainerDataSource(datasource);
        scoreBoard.setVisibleColumns(new String[]{"User", "Score"});
        scoreBoard.setSelectable(true);
        scoreBoard.setImmediate(true);
    }
}
