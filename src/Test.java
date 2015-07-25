import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by green on 25.02.2015.
 */
public class Test
{

        public static void main(String[] args) throws SQLException {
            ClassLoader cl = Test.class.getClassLoader();

            Class<?>[] interfaces = (Connection.class);
            InvocationHandler handler = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                {
                    System.out.println(method);
                    return null;
                }
            };
            /*
            Runnable runnable = (Runnable) Proxy.newProxyInstance(cl, interfaces, handler);
             runnable.run();
             */
            Connection conn = (Connection) Proxy.newProxyInstance(cl, interfaces, handler);
            conn.createClob();
            conn.getCatalog();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



}
