package stockapp.bojo.com.stockmanagementapp.inf;

import java.util.List;

import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;

/**
 * Created by cganesan on 11/11/2016.
 */

public interface Location {
    public void showDialog();
    public List<String> getShops();
    public List<String> getBranches(String shopName);
    public void doApply(StockDAO stockDAO, GeneralCallback callback);
}
