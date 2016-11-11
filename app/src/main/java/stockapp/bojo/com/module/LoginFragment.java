package stockapp.bojo.com.module;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import stockapp.bojo.com.stockmanagementapp.R;
import stockapp.bojo.com.stockmanagementapp.dao.LoginDAO;
import stockapp.bojo.com.stockmanagementapp.dao.StockDAO;
import stockapp.bojo.com.stockmanagementapp.inf.GeneralCallback;
import stockapp.bojo.com.stockmanagementapp.inf.Login;

public class LoginFragment extends Fragment implements GeneralCallback{
    private static String TAG = "stockapp";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Login login;

    private EditText userName;
    private EditText password;
    //private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userName = (EditText)view.findViewById(R.id.app_username);
        password = (EditText)view.findViewById(R.id.app_password);
        final Button loginBtn = (Button) view.findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login != null){
                    //Log.d(TAG,"Logging into server " + username + " with password " + password + " View Type" + v.getClass().getName());
                    //this.LoginFormat.this.

                    LoginDAO loginDAO = LoginFragment.this.getLoginDAO();

                    login.doLogin(loginDAO,LoginFragment.this);
                }
            }
        });

        return view;
    }

    public LoginDAO getLoginDAO(){
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.setPassword(password.getText().toString());
        loginDAO.setUsername(userName.getText().toString());
        return loginDAO;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  Login){
            login = (Login) context;
            Log.d(TAG,"Received Controller Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onComplete(StockDAO stockDAO) {
        LoginDAO loginDAO = (LoginDAO) stockDAO;
        Toast.makeText(getContext(), "Login to server Successful, Hello " + loginDAO.getUsername() + "!", Toast.LENGTH_LONG).show();
    }
}
