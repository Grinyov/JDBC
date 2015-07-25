/**
 * Created by green on 24.02.2015.
 *
 */

import java.sql.SQLException;
import java.util.List;

public interface UserDao
{
    public List<User> selectAll() throws SQLException;

    public int deleteById(int id) throws  SQLException;

    public void insert(User user) throws  SQLException;/*, NotUniqueUserLoginException, NotUniqueUserEmailException*/

    void shutdown();
}
