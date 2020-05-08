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
import android.widget.TextView;

import com.example.jetpackdemo.R;

import java.util.List;

public class WorkFragment extends Fragment {

    private WorkViewModel mViewModel;

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.work_fragment, container, false);
    }
    private Button search,delete_all;
    private TextView xiansi;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);
        final NavController  navController= Navigation.findNavController(getView());

        // TODO: Use the ViewModel
        search=   getView().findViewById(R.id.search_all);
        delete_all=   getView().findViewById(R.id.delete_all);
        xiansi=   getView().findViewById(R.id.xianshi_tv);
        getView().findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_workFragment2_to_addFragment2);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searcha();
            }
        });
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkRepository.getInstance(getContext()).getWorkDao().deleteAll();
            }
        });
    }

    private void searcha() {
        List<WorkEntity> geworks = WorkRepository.getInstance(getContext()).getWorkDao().geworks();
        xiansi.setText(geworks.toString());
    }

}
