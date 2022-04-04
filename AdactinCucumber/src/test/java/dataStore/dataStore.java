package dataStore;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataStore {

    public ResultSet ConnectAndQuerySql(String sDBURL, String sUserName, String sPassword,String sQuery) {
        //ResultSet rs1=null;
        ResultSet sqlDb = null;
        try {

            String dbURL = sDBURL;
            String user = sUserName;
            String pass = sPassword;
            java.sql.Connection conn = DriverManager.getConnection(dbURL, user, pass);
            Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlDb = stmt.executeQuery(sQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sqlDb;
    }

    public int rowCount(ResultSet resultset) throws Exception {
        int count = 0;
        resultset.last();
        count = resultset.getRow();
        resultset.beforeFirst();
        return count;
    }


}
