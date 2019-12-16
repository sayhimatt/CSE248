package com.guidi.collegesearch.frontEnd.ui.account_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.backCode.util.AccountHandler;
import com.guidi.collegesearch.backCode.util.OnClickAssigner;
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

        Button b = (Button)root.findViewById(R.id.logout_button);
        setLogOutF(b);

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
                getActivity().finish();
                OnClickAssigner.backToLogin();
            }
        });
    }
}