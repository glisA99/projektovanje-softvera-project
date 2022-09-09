package controller;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import domain.Radnik;
import forms.FrmLogin;
import forms.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import session.Session;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class LoginController {

    private FrmLogin loginForm;

    public LoginController(FrmLogin loginForm) {
        this.loginForm = loginForm;
        prepareLoginView();
        try {
            initializeSession();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(loginForm, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            // disable button if connection is not established
            this.loginForm.getBtnLogin().setEnabled(false);
            this.loginForm.getLblError().setText("Connection with server not established...");
        }
    }

    private void prepareLoginView() {
        loginForm.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JTextField txtUsername = loginForm.getTxtUsername();
                JPasswordField pswd = loginForm.getPswdPassword();
                String username = txtUsername.getText().trim();
                String password = String.valueOf(pswd.getPassword());
                try {
                    validate(username,password);
                    Radnik radnik = login(username, password);
                    JOptionPane.showMessageDialog(loginForm, "Welcome, " + radnik.getIme() + "!");
                    loginForm.dispose();
                    new FrmMain().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    txtUsername.setText("");
                    pswd.setText("");
                    JOptionPane.showMessageDialog(loginForm, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void validate(String username, String password) throws Exception {
        if (username.length() == 0) throw new Exception("Username can not be empty!");
        if (password.length() == 0) throw new Exception("Password can not be empty!");
    }
    
    private Radnik login(String username, String password) throws Exception {
        // create request object
        Request request = new Request();
        request.setOperation(Operations.LOGIN);
        // create radnik
        Radnik radnik = new Radnik();
        radnik.setUsername(username);
        radnik.setPassword(password);
        
        // set radnik as data of req
        request.setData(radnik);
        
        // create sender object and send request object
        Session session = Session.getInstance();
        Sender sender = new Sender(session.getSocket());
        sender.send(request);
        
        // read response
        Receiver receiver = new Receiver(session.getSocket());
        Response response = (Response) receiver.receive();
        
        if (response.getResponseType().equals(ResponseType.FAILURE)) {
            throw new Exception("Invalid username/password");
        }
        // else
        Radnik r = (Radnik) response.getResponse();
        session.setLoggedRadnik(r);
        return r;
    }

    private void initializeSession() throws IOException {
        Session.getInstance();
    }
    
}
