package stockapp.bojo.com.stockmanagementapp.dao;

/**
 * Created by t.varada on 11/10/2016.
 */

public class LoginDAO extends StockDAO{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
