package controller.general;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import forms.FrmMain;
import java.io.IOException;
import javax.swing.JPanel;
import session.Session;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractController {

    protected FrmMain form;

    public AbstractController(FrmMain form) {
        this.form = form;
    }
    
    public void initPanel(JPanel panel) {
        setPanel(panel);
        panel.setVisible(true);
        form.pack();
        form.revalidate();
    }
    
    public void setPanel(JPanel panel) {
        this.form.setPnlMain(panel);
    }
    
    protected Response sendRequest(Request request) throws Exception {
        session.Session session = Session.getInstance();
        new Sender(session.getSocket()).send(request);
        return (Response) new Receiver(session.getSocket()).receive();
    }
    
}
