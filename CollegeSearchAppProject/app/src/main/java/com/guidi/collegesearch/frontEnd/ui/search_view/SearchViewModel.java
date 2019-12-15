package com.guidi.collegesearch.frontEnd.ui.search_view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public SearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search Area");

    }
    public LiveData<String> getText() {
        return mText;
    }

}