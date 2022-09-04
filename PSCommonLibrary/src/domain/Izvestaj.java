package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Izvestaj implements IEntity, Serializable {
    
    private Long izvestajID;
    private Date datumKreiranja;
    private Date datumOd;
    private Date datumDo;
    private BigDecimal ukupanPrihod;
    private String radnikJMBG;
    private List<StavkaIzvestaja> stavke;

    public Izvestaj() {
    }

    public Izvestaj(Long izvestajID, Date datumKreiranja, Date datumOd, Date datumDo, BigDecimal ukupanPrihod, String radnikJMBG) {
        this.izvestajID = izvestajID;
        this.datumKreiranja = datumKreiranja;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupanPrihod = ukupanPrihod;
        this.radnikJMBG = radnikJMBG;
    }

    public Izvestaj(Long izvestajID, Date datumKreiranja, Date datumOd, Date datumDo, BigDecimal ukupanPrihod, String radnikJMBG, List<StavkaIzvestaja> stavke) {
        this.izvestajID = izvestajID;
        this.datumKreiranja = datumKreiranja;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupanPrihod = ukupanPrihod;
        this.radnikJMBG = radnikJMBG;
        this.stavke = stavke;
    }
    
    

    @Override
    public String getTableName() {
        return "izvestaj";
    }

    @Override
    public String getWhereCondition() {
        return "IzvestajID = " + this.izvestajID;
    }

    @Override
    public String getColumnNamesForInsert() {
        return "IzvestajID, DatumKreiranja, DatumOd, DatumDo, UkupanPrihod, RadnikJMBG";
    }

    @Override
    public String getColumnValuesForInsert() {
        return this.izvestajID + ", " + this.datumKreiranja + ", " + this.datumOd + ", " + this.datumDo + ", "
                + this.ukupanPrihod + ", '" + this.radnikJMBG + "'";
    }

    @Override
    public String getColumnValuesForUpdate() {
        return "IzvestajID = " + this.izvestajID + ", DatumKreiranja = " + this.datumKreiranja
                + ", DatumOd = " + this.datumOd + ", DatumDo = " + this.datumDo + ", UkupanPrihod = " + this.ukupanPrihod
                + ", RadnikJMBG = '" + this.radnikJMBG + "'";
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        Izvestaj izvestaj = new Izvestaj();
        izvestaj.setIzvestajID(rs.getLong("IzvestajID"));
        izvestaj.setDatumKreiranja(rs.getDate("DatumKreiranja"));
        izvestaj.setDatumOd(rs.getDate("DatumOd"));
        izvestaj.setDatumDo(rs.getDate("DatumDo"));
        izvestaj.setUkupanPrihod(rs.getBigDecimal("UkupanPrihod"));
        izvestaj.setRadnikJMBG(rs.getString("RadnikJMBG"));
        return izvestaj;
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.izvestajID = id;
    }

    public Long getIzvestajID() {
        return izvestajID;
    }

    public void setIzvestajID(Long izvestajID) {
        this.izvestajID = izvestajID;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public BigDecimal getUkupanPrihod() {
        return ukupanPrihod;
    }

    public void setUkupanPrihod(BigDecimal ukupanPrihod) {
        this.ukupanPrihod = ukupanPrihod;
    }

    public String getRadnikJMBG() {
        return radnikJMBG;
    }

    public void setRadnikJMBG(String radnikJMBG) {
        this.radnikJMBG = radnikJMBG;
    }

    public List<StavkaIzvestaja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIzvestaja> stavke) {
        this.stavke = stavke;
    }

    
    
}
