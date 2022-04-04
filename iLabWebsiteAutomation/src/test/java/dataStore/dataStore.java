package dataStore;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataStore {


    public ResultSet ConnectAndQuerySql(String sDBURL, String sUsername, String sPassword, String sQuery){
        ResultSet sqlDb = null;
        try{
            String dbURL = sDBURL;
            String user = sUsername;
            String password = sPassword;
            String strQuery = sQuery;
            java.sql.Connection conn = DriverManager.getConnection(dbURL,user,password);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlDb = stmt.executeQuery(sQuery);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  sqlDb;
    }

    public  int rowCount(ResultSet resultSet) throws SQLException {
        int count = 0;
        resultSet.last();
        count = resultSet.getRow();
        resultSet.beforeFirst();
        return count;
    }
}
