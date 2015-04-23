package fr.gddb.halloffame.ui;

import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class SubmissionUploader implements Receiver, SucceededListener {

    @Override
    public void uploadSucceeded(SucceededEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public OutputStream receiveUpload(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }
}
