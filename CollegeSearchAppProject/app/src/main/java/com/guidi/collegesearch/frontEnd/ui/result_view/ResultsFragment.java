package com.guidi.collegesearch.frontEnd.ui.result_view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guidi.collegesearch.backCode.model.School;
import com.guidi.collegesearch.backCode.util.Degree;
import com.guidi.collegesearch.backCode.util.State;
import com.guidi.collegesearch.main.MainActivity;
import com.guidi.collegesearch.main.R;

import java.util.ArrayList;

import static com.guidi.collegesearch.frontEnd.ui.result_view.Results_Handler.getSchoolSearchResults;

public class ResultsFragment extends Fragment {
    private ResultsViewModel resultsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resultsViewModel =
                ViewModelProviders.of(this).get(ResultsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_results, container, false);
        final RecyclerView searchCyclerView = (RecyclerView)root.findViewById(R.id.search_cycler_view);
        searchCyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SchoolAdapter adapter = new SchoolAdapter(getSchoolSearchResults());
        searchCyclerView.setAdapter(adapter);
        return root;
    }

    public class SchoolAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private ArrayList<School>dataSource;
        public SchoolAdapter(ArrayList<School> dataArgs){
            dataSource = dataArgs;
        }
        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TextView(parent.getContext());
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, final int position) {
            holder.textView.setText(dataSource.get(position).getCollegeName());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    School cSch = dataSource.get(position);
                    AlertDialog.Builder alertDialogBuilder =
                            new AlertDialog.Builder(getContext())
                                .setTitle(cSch.getCollegeName() + " Info")
                                .setMessage("Unique ID: " + cSch.getId()+"\n"+
                                            "State: " + (State.valueOfAbbreviation(cSch.getStateAbr())).toString()+"\n"+
                                            "City: " + cSch.getCity()+"\n"+
                                            "Zip: " + cSch.getZip()+"\n"+
                                            "Admissions Rate: " + cSch.getAdmRate()+"\n"+
                                            "Cost In-State: $" + cSch.getCostIn()+"\n"+
                                            "Cost Out-of-State: $" + cSch.getCostOut()+"\n"+
                                            "Primary Degree Given: " + cSch.getMainDegree()

                                )
                                .setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                    AlertDialog alertDialog = alertDialogBuilder.show();


                }
            });
        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }

    }
    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setTextSize(18);
            textView.setPadding(10,30,10,30);

        }

    }

}