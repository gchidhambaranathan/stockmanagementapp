package stockapp.bojo.com.stockmanagementapp.dao;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;

/**
 * Created by cganesan on 11/11/2016.
 */

public class LocationDAO extends StockDAO {
    List<String> shops;
    Map<String,List<SoftReference>> branches;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String shopName;
    private String branchName;

    public List<String> getShops() {
        return shops;
    }

    public void setShops(List<String> shops) {
        this.shops = shops;
    }

    public Map<String, List<SoftReference>> getBranches() {
        return branches;
    }

    public void setBranches(Map<String, List<SoftReference>> branches) {
        this.branches = branches;
    }
}
