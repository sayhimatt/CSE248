package com.guidi.collegesearch.frontEnd.ui.result_view;

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

import com.guidi.collegesearch.main.MainActivity;
import com.guidi.collegesearch.main.R;

public class ResultsFragment extends Fragment {
    private ResultsViewModel resultsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resultsViewModel =
                ViewModelProviders.of(this).get(ResultsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_results, container, false);
        RecyclerView searchCyclerView = (RecyclerView)root.findViewById(R.id.search_cycler_view);
        searchCyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchCyclerView.setAdapter(new SimpleRVAdapter(makeMyView()));
        return root;
    }
    private static String[] makeMyView(){
        String [] test = new String[(MainActivity.myCounter++)];
        for(int i = 0; i < test.length; i++){
            test[i] = String.valueOf(i);
        }
        return test;
    }


    private class SimpleRVAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private String[] dataSource;
        public SimpleRVAdapter(String[] dataArgs){
            dataSource = dataArgs;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TextView(parent.getContext());
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.textView.setText(dataSource[position]);
        }

        @Override
        public int getItemCount() {
            return dataSource.length;
        }

    }
    private static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}