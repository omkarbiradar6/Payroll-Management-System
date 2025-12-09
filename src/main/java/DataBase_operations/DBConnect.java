package DataBase_operations;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {

    public static Connection connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbuname, DBInfo.dbUpsd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
