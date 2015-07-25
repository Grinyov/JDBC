import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by green on 26.02.2015.
 */
public class TestSpeed
{
    private static final int COUNT = 100;

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDaoJdbc();
        //UserDao dao = new UserDaoJdbcProxol();

        List<Long> time = new ArrayList<>();

        for (int k = 0; k < COUNT; k++)
        {
            long tic = System.nanoTime();

            List<User> userList = dao.selectAll();

            System.out.println(userList);

            Long toc = System.nanoTime();
            Long dT = (toc - tic) / 1000;
            System.out.println(dT);
            time.add(dT);
        }

        Collections.sort(time);
        long sum = 0;
        for (int k = COUNT / 10; k < COUNT - COUNT / 10; k++)
        {
            sum += time.get(k);
        }
        System.out.println("avg = " + 10 * sum / (COUNT * 8));

        dao.shutdown();
    }
}
