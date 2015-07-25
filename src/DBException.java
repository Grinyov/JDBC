/**
 * Created by green on 24.02.2015.
 */
public class DBException extends Exception
{
    public DBException(String message)
    {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
