package klijent.so;

import domain.Klijent;
import so.AbstractSystemOperation;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class KreirajKlijenta extends AbstractSystemOperation<Klijent>   {

    public KreirajKlijenta() throws Exception {
        super();
    }
    
    @Override
    protected void precondition(Klijent entity) throws Exception {
        return;
    }

    @Override
    protected void executeOperation(Klijent entity) throws Exception {
        Klijent k = new Klijent();
        k.setEmail("");
        k.setPrezime("");
        k.setIme("");
        this.operationResult = k;
    }
}
