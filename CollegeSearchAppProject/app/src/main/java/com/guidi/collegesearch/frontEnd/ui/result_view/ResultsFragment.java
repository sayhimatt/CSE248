package com.guidi.collegesearch.frontEnd.ui.result_view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guidi.collegesearch.backCode.model.School;
import com.guidi.collegesearch.backCode.util.Degree;
import com.guidi.collegesearch.backCode.util.Region;
import com.guidi.collegesearch.backCode.util.State;
import com.guidi.collegesearch.main.R;

import java.util.ArrayList;

import static com.guidi.collegesearch.frontEnd.ui.result_view.ResultsHandler.getSchoolSearchResults;

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
                                .setMessage(schoolInfoToString(cSch))
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
    public static String schoolInfoToString(School cSch){
        String s = "Unique ID: " + cSch.getId()+"\n"+
                "State: " + (State.valueOfAbbreviation(cSch.getStateAbr())).toString()+"\n"+
                "City: " + cSch.getCity()+"\n"+
                "Zip: " + cSch.getZip()+"\n"+
                "Region: " + Region.getRegionS(cSch.getRegID())+"\n"+
                "Admissions Rate: " + cSch.getAdmRate()+"\n"+
                "Cost In-State: $" + cSch.getCostIn()+"\n"+
                "Cost Out-of-State: $" + cSch.getCostOut()+"\n"+
                "Primary Degree Awarded: " + Degree.getdNameByKey(cSch.getMainDegree())+"\n"+
                "Maximum Degree Awarded: " + Degree.getdNameByKey(cSch.getMaxDegree())+"\n"+
                "School URL: " + cSch.getSchoolURL()+"\n"+
                "Student Size: " + cSch.getStudentSize()+"\n";
        if((cSch.getSatM25() != 0) && (cSch.getSatM75() != 0))
                s += "Math SAT Scores: \n75th: " + cSch.getSatM75() + "\t25th: " + cSch.getSatM25() +"\n";
        if((cSch.getSatR25() != 0) && (cSch.getSatR75() != 0))
            s += "Reading SAT Scores: \n75th: " + cSch.getSatR75() + "\t25th: " + cSch.getSatR25() +"\n";
        if((cSch.getSatW25() != 0) && (cSch.getSatW75() != 0))
            s += "Writing SAT Scores: \n75th: " + cSch.getSatW75() + "\t25th: " + cSch.getSatW25() +"\n";
        return s;
    }

}