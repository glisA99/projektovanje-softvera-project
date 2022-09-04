package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Klijent implements IEntity, Serializable {
    
    private Long klijentID;
    private String ime;
    private String prezime;
    private String email;

    public Klijent() {
    }

    public Klijent(Long klijentID, String ime, String prezime, String email) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    @Override
    public String getTableName() {
        return "klijent";
    }

    @Override
    public String getWhereCondition() {
        return "KlijentID = " + this.klijentID;
    }

    @Override
    public String getColumnNamesForInsert() {
        return "KlijentID, Ime, Prezime, Email";
    }

    @Override
    public String getColumnValuesForInsert() {
        return this.klijentID + ", '" + this.ime + "', '" + this.prezime + "', '" + this.email + "'";
    }

    @Override
    public String getColumnValuesForUpdate() {
        return "KlijentID = " + this.klijentID + ", Ime = '" + this.ime + "', Prezime = '" + this.prezime + "', Email = '" + this.email + "'";
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        Klijent klijent = new Klijent();
        klijent.setKlijentID(rs.getLong("KlijentID"));
        klijent.setIme(rs.getString("Ime"));
        klijent.setPrezime(rs.getString("Prezime"));
        klijent.setEmail(rs.getString("Email"));
        return klijent;
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.klijentID = id;
    }

    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
