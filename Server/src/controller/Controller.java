package controller;

import domain.Radnik;
import login.so.LoginSO;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Radnik login(Radnik radnik) throws Exception {
        LoginSO so = new LoginSO();
        so.execute(radnik);
        Radnik radnik1 = (Radnik) so.operationResult;
        if (radnik1 == null) {
            throw new Exception("Radnik not found");
        }
        return radnik1;
    }

}
