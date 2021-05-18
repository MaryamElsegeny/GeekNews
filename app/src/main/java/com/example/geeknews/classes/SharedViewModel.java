package com.example.geeknews.classes;

import com.example.geeknews.models.RadioButtonModel;
import com.example.geeknews.retrofit.ApiInterface;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<>();
    private final MutableLiveData<String> selectedType = new MutableLiveData<>();
    private final MutableLiveData<RadioButtonModel> selectedDateAndType = new MutableLiveData<>();



    public void select(String l) {
        selected.setValue(l);
    }

    public void selectDateAndType(RadioButtonModel radioButtonModel) {
        selectedDateAndType.setValue(radioButtonModel);
    }

    public LiveData<RadioButtonModel> getSelectedDateAndType() {
        return selectedDateAndType;
    }




}
