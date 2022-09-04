package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Artikl implements IEntity, Serializable {
    
    private Long sifraArtikla;
    private String opis;
    private String naziv;
    private BigDecimal prodajnaCena;
    private BigDecimal prodajnaVrednost;
    private String velicina;
    private String proizvodjac;
    private int kolicinaNaStanju;

    public Artikl() {
    }

    public Artikl(Long sifraArtikla, String opis, String naziv, BigDecimal prodajnaCena, BigDecimal prodajnaVrednost, String velicina, String proizvodjac, int kolicinaNaStanju) {
        this.sifraArtikla = sifraArtikla;
        this.opis = opis;
        this.naziv = naziv;
        this.prodajnaCena = prodajnaCena;
        this.prodajnaVrednost = prodajnaVrednost;
        this.velicina = velicina;
        this.proizvodjac = proizvodjac;
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    @Override
    public String getTableName() {
        return "artikl";
    }

    @Override
    public String getWhereCondition() {
        return "SifraArtikla = " + this.sifraArtikla;
    }

    @Override
    public String getColumnNamesForInsert() {
        return "SifraArtikla, Naziv, Opis, ProdajnaCena, ProdajnaVrednost, Velicina, Proizvodjac, KolicinaNaStanju";
    }

    @Override
    public String getColumnValuesForInsert() {
        return this.sifraArtikla + ", '" + this.naziv + "', '" + this.opis + "', " + this.prodajnaCena + ", " 
                + this.prodajnaVrednost + ", '" + this.velicina + "', '" + this.proizvodjac + "', "
                + this.kolicinaNaStanju;
    }

    @Override
    public String getColumnValuesForUpdate() {
        return "SifraArtikla = " + this.sifraArtikla + ", Naziv = '" + this.naziv + "', Opis = '" + this.opis + "', ProdajnaCena = " 
                + this.prodajnaCena + ", ProdajnaVrednost = " + this.prodajnaVrednost + ", Velicina = '" + this.velicina + "', Proizvodjac = '"
                + this.proizvodjac + "', KolicinaNaStanju = " + this.kolicinaNaStanju;
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        Artikl artikl = new Artikl();
        artikl.setSifraArtikla(rs.getLong("SifraArtikla"));
        artikl.setKolicinaNaStanju(rs.getInt("KolicinaNaStanju"));
        artikl.setNaziv(rs.getString("Naziv"));
        artikl.setOpis(rs.getString("Opis"));
        artikl.setProdajnaCena(rs.getBigDecimal("ProdajnaCena"));
        artikl.setProdajnaVrednost(rs.getBigDecimal("ProdajnaVrednost"));
        artikl.setVelicina(rs.getString("Velicina"));
        artikl.setProizvodjac(rs.getString("Proizvodjac"));
        return artikl;
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraArtikla = id;
    }

    public Long getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Long sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getProdajnaCena() {
        return prodajnaCena;
    }

    public void setProdajnaCena(BigDecimal prodajnaCena) {
        this.prodajnaCena = prodajnaCena;
    }

    public BigDecimal getProdajnaVrednost() {
        return prodajnaVrednost;
    }

    public void setProdajnaVrednost(BigDecimal prodajnaVrednost) {
        this.prodajnaVrednost = prodajnaVrednost;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(int kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    
    
    
}
