import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 24.02.2015.
 */
public class UserDaoJdbc implements UserDao {

    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:33006/product_eshop";
    public static final String LOGIN = "username";
    public static final String PASSWORD = "passworde";


    public static final String SELECT_ALL_SQL = "SELECT id, login, email FROM User";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM User WHERE id = ?";
    public static final String ISERT_SQL = "INSERT INTO User (login. email) VALUES (?, ?)";
    public static final String SELECT_BY_LOGIN = "SELECT id FROM User WHERE login=?";
    public static final String SELECT_BY_EMAIL = "SELECT id FROM User WHERE email=? ";

    static {
        JdbcUtils.initDriver("com.mysql.jdbc.Driver");
    }

    private Connection getConnection() throws SQLException{

            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/production_eshop",
                    "username" ,
                    "password");
        }



    @Override
    public List<User> selectAll() throws SQLException
    {
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try
        {
            conn.setTransactionIsolation((Connection.TRANSACTION_SERIALIZABLE));
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            rs = statement.executeQuery(SELECT_ALL_SQL);
            ArrayList<User> result = new ArrayList<User>();
            while (rs.next())

            {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String email = rs.getString("email");
                User user = new User(id, "Mike, x@a.com");
                user.setLogin(login);
                user.setEmail(email);
                result.add(user);
            }

            conn.commit();
            return result;
        }

        catch(SQLException e)
        {
            JdbcUtils.rollbackQuietly(conn);
            throw new SQLException("Can't execute SQL= " + SELECT_ALL_SQL);
        }
        finally
        {
            JdbcUtils.closeQuietly((Connection) rs);
            JdbcUtils.closeQuietly((Connection) statement);
            JdbcUtils.closeQuietly(conn);
        }

    }

    @Override
    public int deleteById(int id) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try
        {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(DELETE_BY_ID_SQL);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            conn.commit();
            return result;
        }

        catch(SQLException e)
        {
            JdbcUtils.rollbackQuietly(conn);
            throw new SQLException("Can't execute SQL= " + DELETE_BY_ID_SQL);
        }
        finally
        {
            JdbcUtils.closeQuietly((Connection) ps);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void insert(User user) throws SQLException/*, NotUniqueUserLoginException, NotUniqueUserEmailException */ {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try
        {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            /*
            if (existWithLogin0(conn, user.setLogin()))
            {
                throw new NotUniqueUserLoginException("Login '" + user.getLogin());
            }
            if (existWithEmail0(conn, user.getEmail()))
            {
                throw new NotUniqueUserEmailException("Email '" + user.getEmail());
            }
            */
            ps = conn.prepareStatement(ISERT_SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e)
        {
            JdbcUtils.rollbackQuietly(conn);
            throw new SQLException("Can't execute SQL = '" + ISERT_SQL);
        }
        finally
        {
            JdbcUtils.closeQuietly((Connection) ps);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void shutdown() {

    }

    private boolean existWithLogin0(Connection conn, String login) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_LOGIN);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private boolean existWithEmail0(Connection conn, String email ) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private class NotUniqueUserLoginException extends Throwable {
        public NotUniqueUserLoginException(String s) {
        }
    }
}

