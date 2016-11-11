package stockapp.bojo.com.stockmanagementapp.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t.varada on 11/10/2016.
 */


public class StockDAO {
    private List<String> errors = new ArrayList<>();

    public boolean isSuccess(){
        return errors.size() == 0;
    }

    public List<String> getAllErrors(){
        return errors;
    }
}
