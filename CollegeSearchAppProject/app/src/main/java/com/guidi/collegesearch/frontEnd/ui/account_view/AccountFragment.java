package com.guidi.collegesearch.frontEnd.ui.account_view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.guidi.collegesearch.backCode.model.Account;
import com.guidi.collegesearch.main.R;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private AccountHandler aH;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_account, container, false);
        if(aH.getCurrentAccount() == null) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            aH.loadAccount();
                            this.wait(1000);
                            Account a = aH.getCurrentAccount();
                            setActData(a, root);

                        }
                    } catch (Exception e) {

                    }

                }

            };
            Thread myThread = new Thread(runnable);
            myThread.start();
        }else{
           setActData(aH.getCurrentAccount(), root);
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

}