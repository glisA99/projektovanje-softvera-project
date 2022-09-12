package login.so;

import domain.IEntity;
import domain.Radnik;
import java.util.List;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class LoginSO extends AbstractSystemOperation<Radnik> {

    public LoginSO() throws Exception {
        super();
    }

    @Override
    protected void precondition(Radnik entity) throws Exception {
        if (entity.getUsername().length() == 0) throw new Exception("Username can not be empty");
        if (entity.getPassword().length() == 0) throw new Exception("Password can not be empty");
    }

    @Override
    protected void executeOperation(Radnik entity) throws Exception {
        String whereCondition = "Username = '" + entity.getUsername() + "' AND " + "Password = '" + entity.getPassword() + "'";
        List<IEntity> radnici = this.repository.findAllCustom(entity, whereCondition);
        if (radnici.size() == 0) {
            throw new Exception("There is no employee with specified username and password.");
        }
        operationResult = (Radnik) radnici.get(0);
    }

}
