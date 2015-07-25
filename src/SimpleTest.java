import java.sql.SQLException;

/**
 * Created by green on 24.02.2015.
 */
public class SimpleTest
{
    UserDao dao;

    public void setDao(UserDao dao)
    {
        this.dao = dao;
    }

    public  void main(String[] args) throws DBException, SQLException {
       // UserDao dao = new UserDaoJdbc();

        System.out.println("ALL CURRENT USERS");
        for (User user : dao.selectAll())
        {
            System.out.println("    " + user.toString());
        }

        System.out.println("DELETE");
        for (User user : dao.selectAll())
        {
            dao.deleteById(user.getId());
            System.out.println("    User with id = " + user.getId() + "delete!");
        }

        System.out.println("INSERT NEW: ");
        dao.insert(new User(1, "Mike, x@a.com"));
        System.out.println("         'Mike' iserted ");

    }
}
