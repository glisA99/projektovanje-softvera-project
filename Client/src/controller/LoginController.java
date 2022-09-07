package controller;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import domain.Radnik;
import forms.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        // create sender object and send request object
        session.Session session = Session.getInstance();
        Sender sender = new Sender(session.getSocket());
        sender.send(request);
        
        // read response
        Receiver receiver = new Receiver(session.getSocket());
        Response response = (Response) receiver.receive();
        
        if (response.getResponseType().equals(ResponseType.FAILURE)) {
            throw new Exception(response.getException().getMessage());
        }
        // else
        Radnik r = (Radnik) response.getResponse();
        session.setLoggedRadnik(r);
        return r;
    }
    
}
