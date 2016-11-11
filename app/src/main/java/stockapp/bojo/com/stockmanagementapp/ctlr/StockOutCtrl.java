package stockapp.bojo.com.stockmanagementapp.ctlr;

import stockapp.bojo.com.stockmanagementapp.dao.StockOutDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.StockOut;

/**
 * Created by t.varada on 11/10/2016.
 */

public class StockOutCtrl implements StockOut {
    @Override
    public void onStockOutComplete(StockOutDAO stockOutDAO, GeneralCallback callback) {
        //TODO hook the stock out logic and give the callback to module or result or error
    }
}
