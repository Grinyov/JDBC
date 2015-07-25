import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by green on 26.02.2015.
 */
public class ConnectionFactoryJdbc implements ConnectionFactory
{
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:33006/product_eshop";
    private static final String LOGIN = "username";
    private static final String PASSWORD = "passworde";

    @Override
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
    }

    @Override
    public void close() throws SQLException {

    }
}
