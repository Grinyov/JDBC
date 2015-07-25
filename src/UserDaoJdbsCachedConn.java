import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 26.02.2015.
 */
public class UserDaoJdbsCachedConn implements UserDao
{
    private Connection getCOnnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/production_eshop");}

    private Connection _conn;

    @Override
    public List<User> selectAll() throws SQLException
    {
        if ( _conn == null)
        {
            _conn = getCOnnection();
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = _conn.prepareStatement("SELECT id, login FROM User");
            rs = ps.executeQuery();
            ArrayList<User> result = new ArrayList<User>();

            while (rs.next())
            {
                int id = rs.getInt("id");
                String login = rs.getString("login");;
                User user = new User(id, "Mike, x@a.com");
                user.setLogin(login);
                result.add(user);
            }
            return result;
        }
        finally
        {
            JdbcUtils.closeQuietly((Connection) rs);
            JdbcUtils.closeQuietly((Connection) ps);
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        return 0;
    }

    @Override
    public void insert(User user) throws SQLException {

    }

    @Override
    public void shutdown() {

    }
}
