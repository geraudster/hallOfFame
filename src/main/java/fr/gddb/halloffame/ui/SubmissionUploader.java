package fr.gddb.halloffame.ui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class SubmissionUploader implements Receiver, SucceededListener {
    public static final Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "hallOfFame");
    File                     uploadedFile;

    
  public SubmissionUploader() {
        try {
            Files.createDirectories(tempDir);
        } catch (IOException e) {
            new Notification("Could not create temp directory<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                .show(Page.getCurrent());
        }
  }
  
    @Override
    public void uploadSucceeded(SucceededEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public OutputStream receiveUpload(String arg0, String arg1) {
        OutputStream output = null;
        try {
            uploadedFile = Files.createTempFile(tempDir, "upload_", null).toFile();
            output = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        } catch (IOException e) {
            new Notification("Could not open file<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                .show(Page.getCurrent());
            return null;
        }
        return output;
    }
}
