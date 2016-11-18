package stockapp.bojo.com.module;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import stockapp.bojo.com.stockmanagementapp.R;
import stockapp.bojo.com.stockmanagementapp.dao.LocationDAO;
import stockapp.bojo.com.stockmanagementapp.dao.LoginDAO;
import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.Location;

/**
 * Created by cganesan on 11/11/2016.
 */

public class LocationFragment extends DialogFragment implements GeneralCallback {
    private Location location;
    private String selectedCustomer = "";
    private String selectedLocation = "";
    private Spinner cusSpinner;
    private Spinner locSpinner;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        List<String> shops = location.getShops();

        View view = inflater.inflate(R.layout.fragment_location, null);
        // Spinner element
        cusSpinner = (Spinner) view.findViewById(R.id.cusspinner);

        // Spinner click listener
        cusSpinner.setOnItemSelectedListener(new CustomerSprinnerListener());



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, shops);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        cusSpinner.setAdapter(dataAdapter);

        selectedCustomer = cusSpinner.getAdapter().getItem(0).toString();

        // Spinner element
        locSpinner = (Spinner) view.findViewById(R.id.locspinner);

        // Spinner click listener
        locSpinner.setOnItemSelectedListener(new LocationSprinnerListener());

        List<String> branches = location.getBranches(selectedCustomer);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, branches);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        locSpinner.setAdapter(dataAdapter1);

        selectedLocation = locSpinner.getAdapter().getItem(0).toString();

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        LocationDAO locationDAO = LocationFragment.this.getDAO();
                        location.doApply(locationDAO,LocationFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LocationFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  Location){
            location = (Location) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onComplete(StockDAO stockDAO) {
        LocationDAO locationDAO = (LocationDAO) stockDAO;
        Location location = (Location) getContext();
       // location.showDialog();
        Toast.makeText(getContext(), "Branch " + locationDAO.getShopName() + " Location"+ locationDAO.getBranchName(), Toast.LENGTH_LONG).show();
    }

    public LocationDAO getDAO() {
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.setShopName(selectedCustomer);
        locationDAO.setBranchName(selectedLocation);
        return locationDAO;
    }


    private class CustomerSprinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            selectedCustomer = item;

           /* ArrayAdapter<String> spinnerAdapter  = (ArrayAdapter<String>) locSpinner.getAdapter();
            spinnerAdapter.addAll(customerDataMap.get(selectedCustomer));
            spinnerAdapter.notifyDataSetChanged();*/

            // Creating adapter for spinner
            List locations = location.getBranches(selectedCustomer);
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, locations);

            // Drop down layout style - list view with radio button
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            locSpinner.setAdapter(dataAdapter1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class LocationSprinnerListener   implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            selectedLocation = item;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
