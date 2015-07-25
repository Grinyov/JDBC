/**
 * Created by green on 24.02.2015.
 */
public class User
{
    private int id;
    private String login;
    private String email;

    public User(int id, String s)
    {
        this.id = id;
    }

    public User(String s) {
    }


    public int getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
