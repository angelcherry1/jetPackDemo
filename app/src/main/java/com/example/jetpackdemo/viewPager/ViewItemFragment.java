package com.example.jetpackdemo.viewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jetpackdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FLAG = "flag";

    // TODO: Rename and change types of parameters
    private int flag;

    public ViewItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewItemFragment newInstance(int flag) {
        ViewItemFragment fragment = new ViewItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FLAG, flag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flag = getArguments().getInt(ARG_FLAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_view_item, container, false);
        TextView tv_num = inflate.findViewById(R.id.tv_num);
        tv_num.setText(flag + "");
        return inflate;
    }
}