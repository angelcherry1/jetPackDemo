package com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2.widget.MyViewPager2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPager2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPager2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;

    public ViewPager2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPager2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPager2Fragment newInstance(String param1, int param2) {
        ViewPager2Fragment fragment = new ViewPager2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_view_pager2, container, false);
        // Inflate the layout for this fragment
        MyViewPager2 myViewPager2 = inflate.findViewById(R.id.item_view_pager2);
//        ViewPager2 viewPager2 = inflate.findViewById(R.id.view_pager2);

        ViewPager2 viewPager2 = myViewPager2.getmViewPager2();
        viewPager2.setAdapter(new FragmentStateAdapter(requireActivity()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return ItemViewPager2Fragment.newInstance(String.valueOf(mParam2), String.valueOf(position));
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
        return inflate;
    }
}