package stockapp.bojo.com.stockmanagementapp.ctlr;

import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.Login;

/**
 * Created by t.varada on 11/10/2016.
 */

public class LoginController implements Login {
    @Override
    public void doLogin(StockDAO stockDAO, GeneralCallback callback) {
        //TODO hook the server for SOAP request, on finish use the callback to notify that operation is complete.
        callback.onComplete(stockDAO);
    }
}
