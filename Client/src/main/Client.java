package main;

import controller.LoginController;
import forms.FrmLogin;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Client {

    public static void main(String[] args) {
        FrmLogin frm = new FrmLogin();
        frm.setVisible(true);
        new LoginController(frm);
    }
    
}
