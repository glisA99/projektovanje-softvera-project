package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Radnik implements IEntity, Serializable {
    
    private String jmbg;
    private String ime;
    private String prezime;
    private Date datumPocetkaRada;
    private BigDecimal plata;

    public Radnik() {
    }

    public Radnik(String jmbg, String ime, String prezime, Date datumPocetkaRada, BigDecimal plata) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.datumPocetkaRada = datumPocetkaRada;
        this.plata = plata;
    }

    @Override
    public String getTableName() {
        return "radnik";
    }

    @Override
    public String getWhereCondition() {
        return "JMBG = " + this.jmbg;
    }

    @Override
    public String getColumnNamesForInsert() {
        return "JMBG, Ime, Prezime, DatumPocetkaRada, Plata";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + this.jmbg + "', '" + this.ime + "', '" + this.prezime + "', " + this.datumPocetkaRada + ", " + this.plata;
    }

    @Override
    public String getColumnValuesForUpdate() {
        return "JMBG = '" + this.jmbg + "', Ime = '" + this.ime + "', Prezime = '" + this.prezime + "', " 
                + "DatumPocetkaRada = " + this.datumPocetkaRada + ", Plata = " + this.plata;
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        Radnik radnik = new Radnik();
        radnik.setJmbg(rs.getString("JMBG"));
        radnik.setIme(rs.getString("Ime"));
        radnik.setPrezime(rs.getString("Prezime"));
        radnik.setDatumPocetkaRada(rs.getDate("DatumPocetkaRada"));
        radnik.setPlata(rs.getBigDecimal("Plata"));
        return radnik;
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        return;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumPocetkaRada() {
        return datumPocetkaRada;
    }

    public void setDatumPocetkaRada(Date datumPocetkaRada) {
        this.datumPocetkaRada = datumPocetkaRada;
    }

    public BigDecimal getPlata() {
        return plata;
    }

    public void setPlata(BigDecimal plata) {
        this.plata = plata;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }
    
    
    
}
