package stockapp.bojo.com.stockmanagementapp.inf;

import stockapp.bojo.com.stockmanagementapp.dao.StockOutDAO;

/**
 * Created by t.varada on 11/10/2016.
 */

public interface StockOut {
    void onStockOutComplete(StockOutDAO stockOutDAO , GeneralCallback callback);
}
