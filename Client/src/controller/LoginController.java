package controller;

import communication.Operations;
import communication.Request;
import communication.Sender;
import domain.Radnik;
import forms.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
                    login(username, password);
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
    
    private void login(String username, String password) throws Exception {
        // create request object
        Request request = new Request();
        request.setOperation(Operations.LOGIN);
        // create radnik
        Radnik radnik = new Radnik();
        radnik.setUsername(username);
        radnik.setPassword(password);
        
        // create sender object
        Sender sender = new Sender();
    }
    
}
