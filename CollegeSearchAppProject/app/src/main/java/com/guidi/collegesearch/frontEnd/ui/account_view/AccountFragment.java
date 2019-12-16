package com.guidi.collegesearch.frontEnd.ui.account_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.backCode.model.Username;
import com.guidi.collegesearch.backCode.util.AccountHandler;
import com.guidi.collegesearch.backCode.util.OnClickAssigner;
import com.guidi.collegesearch.backCode.util.SchoolDataPuller;
import com.guidi.collegesearch.main.MainActivity;
import com.guidi.collegesearch.main.R;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private AccountHandler aH;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        pullActData(root);

        Button logOut_b = (Button)root.findViewById(R.id.logout_button);
        setLogOutF(logOut_b);

        Button change_act_b = (Button)root.findViewById(R.id.confirm_button);

        final View root2 = root;

        change_act_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewActData(root2);
            }
        });

        ImageButton load_boar_b = (ImageButton) root.findViewById(R.id.data_boar_b_);

        System.out.println(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equalsIgnoreCase("sayhimatt@gmail.com")){
            setSchoolDataPullAction(load_boar_b);
        }else{
            load_boar_b.setVisibility(View.GONE);
        }
        return root;
    }
    private void setActData(Account a, View root){

        TextView emailTV = (TextView) (root.findViewById(R.id.email_view));
        emailTV.setText("Email: " + a.getUsername());
        EditText fNameT = (EditText) root.findViewById(R.id.fname_et);
        fNameT.setText(a.getFirstName());
        EditText lNameT = (EditText) root.findViewById(R.id.lname_et);
        lNameT.setText(a.getLastName());
        EditText satWRT = (EditText) root.findViewById(R.id.sat_rw_et);
        satWRT.setText(String.valueOf(a.getSatRScore()));
        EditText satM = (EditText)root.findViewById(R.id.sat_m_et);
        satM.setText(String.valueOf(a.getSatMScore()));
    }
    private void pullActData(View root){
        final View rView = root;
        if(aH.getCurrentAccount() == null) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            aH.loadAccount();
                            while(!aH.doneLoading) {
                                this.wait(10);
                            }
                            Account a = aH.getCurrentAccount();
                            setActData(a, rView);
                        }
                    } catch (Exception e) {

                    }

                }

            };
            Thread myThread = new Thread(runnable);
            myThread.start();
        }else{
            setActData(aH.getCurrentAccount(), rView);
        }
    }
    private void setLogOutF(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() == null){
                            //Do anything here which needs to be done after signout is complete
                            System.exit(1);
                        }
                        else {
                        }
                    }
                };
                FirebaseAuth.getInstance().signOut();

            }
        });
    }
    private void setNewActData(View root){
        TextView emailTV = (TextView) (root.findViewById(R.id.email_view));
        String email = emailTV.getText().toString().substring(7);
        EditText fNameT = (EditText) root.findViewById(R.id.fname_et);
        String fN = fNameT.getText().toString();
        EditText lNameT = (EditText) root.findViewById(R.id.lname_et);
        String lN = lNameT.getText().toString();
        EditText satWRT = (EditText) root.findViewById(R.id.sat_rw_et);
        int satWR = Integer.parseInt(satWRT.getText().toString());
        EditText satMT = (EditText)root.findViewById(R.id.sat_m_et);
        int satM = Integer.parseInt(satMT.getText().toString());
        Account a = new Account(new Username(email), fN, lN, satM, satWR);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.child(id).setValue(a);
        aH.setCurrentAccount(a);
    }
    private void setSchoolDataPullAction(ImageButton b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv = new ImageView(v.getContext());
                iv.setMinimumHeight(500);
                iv.setMinimumWidth(300);
                Glide.with(v.getContext()).load(R.drawable.loading_boar).into(iv);
                android.app.AlertDialog.Builder alertDialogBuilder =
                        new android.app.AlertDialog.Builder(getContext())
                                .setTitle("The info is currently arriving!\nLoading BOAR")
                                .setCancelable(false).setView(iv);
                final AlertDialog alertDialog = alertDialogBuilder.show();
                final Context c = v.getContext();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        SchoolDataPuller dp = new SchoolDataPuller();
                        try {
                            int i = 0;
                            synchronized (this) {
                                dp.reformTheDatabase(c);
                                while(i < 10){

                                    Thread.sleep(1000);
                                    i++;
                                }
                                alertDialog.cancel();

                            }
                        } catch (Exception e) {

                        }

                    }

                };
                Thread myThread = new Thread(runnable);
                myThread.start();

            }
        });
    }
}