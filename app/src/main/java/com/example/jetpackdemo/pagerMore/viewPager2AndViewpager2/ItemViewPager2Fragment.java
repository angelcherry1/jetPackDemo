package com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jetpackdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemViewPager2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemViewPager2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemViewPager2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemViewPager2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemViewPager2Fragment newInstance(String param1, String param2) {
        ItemViewPager2Fragment fragment = new ItemViewPager2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_item_view_pager2, container, false);
        // Inflate the layout for this fragment
        TextView viewById = inflate.findViewById(R.id.tv_user_id);
        viewById.setText("我是item" + mParam2);
        return inflate;
    }
}