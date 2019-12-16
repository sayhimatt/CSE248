package com.guidi.collegesearch.frontEnd.ui.search_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.guidi.collegesearch.backCode.util.ResultsHandler;
import com.guidi.collegesearch.main.MainActivity;
import com.guidi.collegesearch.main.R;
import com.guidi.collegesearch.frontEnd.CheckboxSpinner;
import com.guidi.collegesearch.backCode.util.State;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = new TextView(getActivity().getBaseContext());
        searchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        getIt(root);
        return root;
    }

    private void getIt(@NonNull View root) {
        final View view = root;
        CheckboxSpinner stateSpinner = root.findViewById(R.id.select_state_spinner);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(root.getContext(), R.layout.support_simple_spinner_dropdown_item, State.getStateNameList());
        stateSpinner.setAdapter(itemsAdapter);
        stateSpinner.setItems(State.getStateNameList());
        stateSpinner.setSelection(State.values().length - 1);
        stateSpinner.setPrompt("State:");
        Button b = (Button)root.findViewById(R.id.search_button);
        final EditText nameOIDET = (EditText)root.findViewById(R.id.school_name_ac);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                ResultsHandler a = new ResultsHandler();
                                a.NameSchoolSearch(nameOIDET.getText().toString());
                                while(!a.nameSDone)
                                {
                                    Thread.sleep(100);
                                }
                                ArrayList<Integer> degL = new ArrayList<Integer>();
                                CheckBox c0 = (CheckBox)view.findViewById(R.id.c0);
                                if(c0.isChecked()){
                                    degL.add(1);
                                }
                                CheckBox c1 = (CheckBox)view.findViewById(R.id.c1);
                                if(c1.isChecked()){
                                    degL.add(2);
                                }
                                CheckBox c2 = (CheckBox)view.findViewById(R.id.c2);
                                if(c2.isChecked()){
                                    degL.add(3);
                                }
                                CheckBox c3 = (CheckBox)view.findViewById(R.id.c3);
                                if(c3.isChecked()){
                                    degL.add(4);
                                }
                                a.DegreeSchoolSearch(degL);

                            }
                        } catch (Exception e) {

                        }

                    }

                };
                Thread myThread = new Thread(runnable);
                myThread.start();


            }
        });


        AutoCompleteTextView collegeSearchName = root.findViewById(R.id.school_name_ac);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1, MainActivity.schoolNameList);
        collegeSearchName.setAdapter(adapter);
    }



}