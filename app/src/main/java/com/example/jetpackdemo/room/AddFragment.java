package com.example.jetpackdemo.room;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jetpackdemo.R;

public class AddFragment extends Fragment {

    private AddViewModel mViewModel;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_fragment, container, false);
    }

    private EditText acount_et, name_et;
    private String acount, name;
    private Button coomit;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        acount_et=  getView().findViewById(R.id.acount_et);
        name_et=  getView().findViewById(R.id.name_et);
        coomit= getView().findViewById(R.id.coomit);
        // TODO: Use the ViewModel

        coomit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acount=acount_et.getText().toString();
                name=name_et.getText().toString();
                insert();
                final NavController navController= Navigation.findNavController(getView());
                navController.navigate(R.id.action_addFragment2_to_workFragment2);
            }
        });
    }

    private void insert() {
        WorkRepository.getInstance(getContext()).getWorkDao().insert(new WorkEntity(acount,name));
    }

}
