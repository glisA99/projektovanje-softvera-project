package controller;

import domain.Radnik;
import login.so.LoginSO;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Authentication {
    
    private static Authentication instance;

    private Authentication() {
    }

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }
    
    public Radnik login(Radnik radnik) throws Exception {
        LoginSO loginSO = new LoginSO();
        
        loginSO.execute(radnik);
        
        Radnik radnik1 = (Radnik) loginSO.operationResult;
        return radnik1;
    }

}
