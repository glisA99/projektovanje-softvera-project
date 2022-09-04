/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class StavkaIzvestaja implements IEntity, Serializable {

    private Long izvestajID;
    private int RB;
    private BigDecimal prihodStavke;

    public StavkaIzvestaja() {
    }

    public StavkaIzvestaja(Long izvestajID, int RB, BigDecimal prihodStavke) {
        this.izvestajID = izvestajID;
        this.RB = RB;
        this.prihodStavke = prihodStavke;
    }

    public Long getIzvestajID() {
        return izvestajID;
    }

    public void setIzvestajID(Long izvestajID) {
        this.izvestajID = izvestajID;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public BigDecimal getPrihodStavke() {
        return prihodStavke;
    }

    public void setPrihodStavke(BigDecimal prihodStavke) {
        this.prihodStavke = prihodStavke;
    }

    @Override
    public String getTableName() {
        return "stavkaizvestaja";
    }

    @Override
    public String getWhereCondition() {
        return "IzvestajID = " + this.izvestajID + " AND RB = " + this.RB; 
    }

    @Override
    public String getColumnNamesForInsert() {
        return "IzvestajID, RB, PrihodStavke";
    }

    @Override
    public String getColumnValuesForInsert() {
        return this.izvestajID + ", " + this.RB + ", " + this.prihodStavke;
    }

    @Override
    public String getColumnValuesForUpdate() {
        return "IzvestajID = " + this.izvestajID + ", RB = " + this.RB + ", PrihodStavke = " + this.prihodStavke; 
    }

    @Override
    public IEntity getNewRecord(ResultSet rs) throws SQLException {
        StavkaIzvestaja stavkaIzvestaja = new StavkaIzvestaja();
        stavkaIzvestaja.setIzvestajID(rs.getLong("IzvestajID"));
        stavkaIzvestaja.setRB(rs.getInt("RB"));
        stavkaIzvestaja.setPrihodStavke(rs.getBigDecimal("PrihodStavke"));
        return stavkaIzvestaja;
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        return;
    }
    
    
    
}
