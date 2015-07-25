import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by green on 26.02.2015.
 */
public interface ConnectionFactory
{
    public Connection newConnection() throws SQLException;

    public void close() throws SQLException;
}
