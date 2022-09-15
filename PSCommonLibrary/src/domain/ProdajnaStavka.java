package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ProdajnaStavka implements IEntity, Serializable {
    
    private Long prodajnaStavkaID;
    private Date datumProdaje;
    private int kolicina;
    private BigDecimal iznos;
    private Long klijentID;
    private Long sifraArtikla;
    private String radnikJMBG;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ProdajnaStavka() {
    }

    public ProdajnaStavka(Long prodajnaStavkaID, Date datumProdaje, int kolicina, BigDecimal iznos, Long klijentID, Long sifraArtikla, String radnikJMBG) {
        this.prodajnaStavkaID = prodajnaStavkaID;
        this.datumProdaje = datumProdaje;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.klijentID = klijentID;
        this.sifraArtikla = sifraArtikla;
        this.radnikJMBG = radnikJMBG;
    }

    @Override
    public String getTableName() {
        return "prodajnastavka";
    }

    @Override
    public String getWhereCondition() {
        return "ProdajnaStavkaID = " + this.prodajnaStavkaID;
    }

    @Override
    public String getColumnNamesForInsert() {
        return "DatumProdaje, Kolicina, Iznos, KlijentID, SifraArtikla, RadnikJMBG";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + sdf.format(this.datumProdaje) + "', " + this.kolicina + ", " + this.iznos + ", " + this.klijentID + ", " + this.sifraArtikla + ", '" + this.radnikJMBG + "'";
    }

    @Override
    public String getColumnValuesForUpdate() {
        String clientID = this.klijentID != null ? this.klijentID.toString() : "NULL";
        return "ProdajnaStavkaID = " + this.prodajnaStavkaID + ", DatumProdaje = " + this.datumProdaje + ", Kolicina = "
                + this.kolicina + ", Iznos = " + this.iznos + ", KlijentID = " + clientID + ", SifraArtikla = "
                + this.sifraArtikla + ", RadnikJMBG = '" + this.radnikJMBG + "'";
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        ProdajnaStavka prodajnaStavka = new ProdajnaStavka();
        prodajnaStavka.setProdajnaStavkaID(rs.getLong("ProdajnaStavkaID"));
        prodajnaStavka.setDatumProdaje(rs.getDate("DatumProdaje"));
        prodajnaStavka.setIznos(rs.getBigDecimal("Iznos"));
        prodajnaStavka.setKlijentID(rs.getLong("KlijentID"));
        prodajnaStavka.setKolicina(rs.getInt("Kolicina"));
        prodajnaStavka.setRadnikJMBG(rs.getString("RadnikJMBG"));
        prodajnaStavka.setSifraArtikla(rs.getLong("SifraArtikla"));
        return prodajnaStavka;
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.setProdajnaStavkaID(id);
    }

    public Long getProdajnaStavkaID() {
        return prodajnaStavkaID;
    }

    public void setProdajnaStavkaID(Long prodajnaStavkaID) {
        this.prodajnaStavkaID = prodajnaStavkaID;
    }

    public Date getDatumProdaje() {
        return datumProdaje;
    }

    public void setDatumProdaje(Date datumProdaje) {
        this.datumProdaje = datumProdaje;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
    }

    public Long getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Long sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public String getRadnikJMBG() {
        return radnikJMBG;
    }

    public void setRadnikJMBG(String radnikJMBG) {
        this.radnikJMBG = radnikJMBG;
    }
    
    

}
