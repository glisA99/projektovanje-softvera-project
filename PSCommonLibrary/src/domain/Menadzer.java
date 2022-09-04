package domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Menadzer extends Radnik {

    private BigDecimal bonus;

    public Menadzer() {
        super();
    }

    public Menadzer(BigDecimal bonus) {
        super();
        this.bonus = bonus;
    }

    public Menadzer(BigDecimal bonus, String jmbg, String ime, String prezime, Date datumPocetkaRada, BigDecimal plata) {
        super(jmbg, ime, prezime, datumPocetkaRada, plata);
        this.bonus = bonus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
    
}
