package stockapp.bojo.com.stockmanagementapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import stockapp.bojo.com.module.LocationFragment;
import stockapp.bojo.com.module.LoginFragment;
import stockapp.bojo.com.module.StockInFragment;
import stockapp.bojo.com.module.WelcomeFragment;
import stockapp.bojo.com.stockmanagementapp.ctlr.LocationController;
import stockapp.bojo.com.stockmanagementapp.ctlr.LoginController;
import stockapp.bojo.com.stockmanagementapp.ctlr.StockOutCtrl;
import stockapp.bojo.com.stockmanagementapp.ctlr.StockinCtrl;
import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;
import stockapp.bojo.com.stockmanagementapp.dao.StockOutDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.Location;
import stockapp.bojo.com.stockmanagementapp.inf.Login;
import stockapp.bojo.com.stockmanagementapp.inf.StockIn;
import stockapp.bojo.com.stockmanagementapp.inf.StockOut;

public class MainActivity extends AppCompatActivity implements Login,StockIn,StockOut,Location{

    private static String TAG = MainActivity.class.getName();

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FloatingActionButton fab;
    //list of fragments
    public static final String TAG_HOME = "home";
    public static final String TAG_SIGNIN = "signin";
    public static final String TAG_STOCKIN = "stockin";
    public static final String TAG_STOCKOUT = "stockout";
    private Handler handler;
    private LoginController loginController;
    private StockinCtrl stockinCtrl;
    private StockOutCtrl stockOutCtrl;
    private LocationController locationController;
    private Bundle featureName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        initFeaureNames();

        loginController = new LoginController();
        stockinCtrl = new StockinCtrl();
        stockOutCtrl = new StockOutCtrl();
        locationController = new LocationController();

        handler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO hook mail option to upload the report/
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //initialize Navigation listener
        setUpNavigationView();



    }

    private void initFeaureNames(){
        featureName = new Bundle();
        featureName.putString(TAG_HOME , "Welcome");
        featureName.putString(TAG_SIGNIN , "Sign In");
        featureName.putString(TAG_STOCKIN , "Stock In");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     * TODO Import data should be hooked here if the fragment selected is
     * welcome screen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    private void setUpNavigationView(){
        //NavigationView navigationViewLocal = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG,"Callback on the item selected  with menu ID as " + item.getItemId() + " and expected is " + R.id.app_signin);
                String currentTag = TAG_HOME;
                int selIndex = 0;
                switch (item.getItemId()){
                    case R.id.app_signin:
                        Log.d(TAG,"Sign In Screen Navigation Selected ");
                        currentTag = TAG_SIGNIN;
                        selIndex = 0;
                        break;
                    case R.id.app_stockin:
                        currentTag = TAG_STOCKIN;
                        selIndex = 1;
                        break;
                }
                loadFragment(currentTag,selIndex);
                getSupportActionBar().setTitle(featureName.getString(currentTag));
                return true;
            }
        });
    }

    private void loadFragment(final String fragmentTag, int selIndex){
        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(fragmentTag) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab(selIndex);
            return;
        }

        Runnable fragmentRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getFragmentByAppTag(fragmentTag);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, fragmentTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };


        if (fragmentRunnable != null) {
            handler.post(fragmentRunnable);
        }

        // show or hide the fab button
        toggleFab(selIndex);
        //Closing drawer on item click
        drawer.closeDrawers();
    }

    private Fragment getFragmentByAppTag(String moduleTag){
        if(moduleTag.equals(TAG_SIGNIN)){
            LoginFragment loginFragment = new LoginFragment();
            Log.d(TAG,"Returning LoginFragment" );
            return loginFragment;
        }else if(moduleTag.equals(TAG_STOCKIN)){
            StockInFragment stockInFragment = new StockInFragment();
            Log.d(TAG,"Returning Stock in Fragment");
            return stockInFragment;
        }else{
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            Log.d(TAG,"Returing Welcome Fragment");
            return welcomeFragment;
        }
    }


    // show or hide the fab
    private void toggleFab(int selIndex) {
        FloatingActionButton fabLocal = (FloatingActionButton) findViewById(R.id.fab);
        if (selIndex == 0)
            fabLocal.show();
        else
            fabLocal.hide();
    }

    @Override
    public void doLogin(StockDAO stockDAO, GeneralCallback callback) {
        if(loginController != null){
            loginController.doLogin(stockDAO,callback);
        }
    }

    @Override
    public void onStockInTaskComplete(StockIn stockIn, GeneralCallback callback) {
        if(stockinCtrl != null){
            stockinCtrl.onStockInTaskComplete(stockIn,callback);
        }
    }

    @Override
    public void onStockOutComplete(StockOutDAO stockOutDAO, GeneralCallback callback) {
        if(stockOutCtrl != null){
            stockOutCtrl.onStockOutComplete(stockOutDAO,callback);
        }
    }

    @Override
    public void showDialog() {
        DialogFragment dialogFragment = new LocationFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
    }

    @Override
    public List<String> getShops() {
        if(locationController != null){
            return locationController.getShops();
        }
        return null;
    }

    @Override
    public List<String> getBranches(String shopName) {
        if(locationController != null){
            return locationController.getBranches(shopName);
        }
        return null;
    }

    @Override
    public void doApply(StockDAO stockDAO, GeneralCallback callback) {
        if(locationController != null){
            locationController.doApply(stockDAO,callback);
        }

    }
}
