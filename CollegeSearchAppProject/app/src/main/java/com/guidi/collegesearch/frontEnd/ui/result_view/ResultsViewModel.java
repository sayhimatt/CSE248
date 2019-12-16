package com.guidi.collegesearch.frontEnd.ui.result_view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class ResultsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private static int myCount = 0;
    private static MutableLiveData<Integer> numCount;
    public ResultsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        numCount = new MutableLiveData<Integer>();

    }
    public LiveData<Integer> getNumCount(){
        return numCount;
    }
    public LiveData<String> getText() {
        return mText;
    }
}