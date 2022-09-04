package domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Zaposleni extends Radnik {
    
    private String nadredjeniJmbg;

    public Zaposleni() {
        super();
    }

    public Zaposleni(String nadredjeniJmbg) {
        super();
        this.nadredjeniJmbg = nadredjeniJmbg;
    }

    public Zaposleni(String nadredjeniJmbg, String jmbg, String ime, String prezime, Date datumPocetkaRada, BigDecimal plata) {
        super(jmbg, ime, prezime, datumPocetkaRada, plata);
        this.nadredjeniJmbg = nadredjeniJmbg;
    }
    

    public String getNadredjeniJmbg() {
        return nadredjeniJmbg;
    }

    public void setNadredjeniJmbg(String nadredjeniJmbg) {
        this.nadredjeniJmbg = nadredjeniJmbg;
    }
    
    
    
}
