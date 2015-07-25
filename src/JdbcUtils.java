import java.sql.*;

/**
 * Created by green on 25.02.2015.
 */

public final class JdbcUtils
{
    private static boolean initialized;

    private JdbcUtils(){}

    public static synchronized void initDriver(String driverClass) {
        if (!initialized) {
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't initialize driver: " + driverClass);
            }
            initialized = true;
        }
    }

    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {

                conn.close();

            } catch (SQLException e) {
                //NOP
            }
        }
    }

    public static void rollbackQuietly(Connection conn) {
        if (conn != null) {
            try {

                conn.rollback();

            } catch (SQLException e) {
                //NOP
            }
        }
    }

}