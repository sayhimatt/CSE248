package com.guidi.collegesearch.frontEnd.ui.search_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.guidi.collegesearch.frontEnd.ui.result_view.ResultsHandler;
import com.guidi.collegesearch.main.R;
import com.guidi.collegesearch.frontEnd.CheckboxSpinner;
import com.guidi.collegesearch.backCode.util.State;

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
        CheckboxSpinner stateSpinner = root.findViewById(R.id.select_state_spinner);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(root.getContext(), R.layout.support_simple_spinner_dropdown_item, State.getStateNameList());
        stateSpinner.setAdapter(itemsAdapter);
        stateSpinner.setItems(State.getStateNameList());
        stateSpinner.setSelection(State.values().length - 1);
        stateSpinner.setPrompt("State:");
        Button b = (Button)root.findViewById(R.id.search_button);
        final EditText nameOIDET = (EditText)root.findViewById(R.id.school_name_edittext);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ResultsHandler a = new ResultsHandler();
                a.newSearch(nameOIDET.getText().toString());
            }
        });


    }

}