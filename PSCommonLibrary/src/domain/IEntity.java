package domain;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public interface IEntity {

    public String getTableName();
    public String getWhereCondition();
    public String getColumnNamesForInsert();
    public String getColumnValuesForInsert();
    public String getColumnValuesForUpdate();
    public IEntity getNewRecord(ResultSet rs) throws SQLException;
    public boolean isIdAutoincrement();
    public void setAutoincrementId(Long id);
    
}
