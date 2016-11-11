package stockapp.bojo.com.stockmanagementapp.inf;

import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;

/**
 * Created by t.varada on 11/10/2016.
 */

public interface Login {
    void doLogin(StockDAO stockDAO, GeneralCallback callback);
}
