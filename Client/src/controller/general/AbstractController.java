package controller.general;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import forms.FrmMain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractController<T extends JDialog> {

    protected FrmMain form;
    protected T dialog;

    protected AbstractController(FrmMain form) {
        this.form = form;
    }

    public void initDialog(T dialog) {
        this.dialog = dialog;
        try {
            prepareView();
            this.dialog.pack();
            this.dialog.revalidate();
            this.dialog.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.form, ex.getMessage(), "Error in form initialization", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected abstract void prepareView() throws Exception;

    protected Response sendRequest(Request request) throws Exception {
        session.Session session = Session.getInstance();
        new Sender(session.getSocket()).send(request);
        return (Response) new Receiver(session.getSocket()).receive();
    }

}
