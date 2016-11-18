package stockapp.bojo.com.stockmanagementapp.ctlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stockapp.bojo.com.stockmanagementapp.dao.LocationDAO;
import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.Location;

/**
 * Created by cganesan on 11/11/2016.
 */

public class LocationController implements Location {
    static Map<String,List<String>> customerDataMap = new HashMap<>();
    @Override
    public void showDialog() {
        //Nothing
    }

    @Override
    public List<String> getShops() {

        return new ArrayList(customerDataMap.keySet());
    }

    @Override
    public List<String> getBranches(String shopName) {
        return customerDataMap.get(shopName);
    }

    @Override
    public void doApply(StockDAO stockDAO, GeneralCallback callback) {
        callback.onComplete(stockDAO);
    }


    static {
        List<String> firstBranches =  new ArrayList<>();
        firstBranches.add("Main");
        firstBranches.add("Medavakkam");
        customerDataMap.put("Bata",firstBranches);

        List<String> secondBranches =  new ArrayList<>();
        secondBranches.add("Velachery");
        secondBranches.add("Vadapalani");
        customerDataMap.put("Addidas",secondBranches);


        List<String> thirdBranches =  new ArrayList<>();
        thirdBranches.add("Kk Nagar");
        thirdBranches.add("OMR");
        customerDataMap.put("Puma",thirdBranches);
    }
}
