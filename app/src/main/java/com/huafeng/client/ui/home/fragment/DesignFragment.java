package com.huafeng.client.ui.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.adapter.ModelDesignAdapter;
import com.huafeng.client.ui.home.model.ModelDesign;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesignFragment extends Fragment {
    @BindView(R.id.rv_design)
    RecyclerView  rv_design;
    ModelDesignAdapter mModelDesignAdapter;
    List<ModelDesign> mModelDesigns;
    int type;
    public DesignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_design, container, false);
        type=getArguments().getInt("type");
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    public static DesignFragment newInstance(int state) {
        DesignFragment orderListFragment = new DesignFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", state);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }
    private void initView() {
        mModelDesigns=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            mModelDesigns.add(new ModelDesign());
        }
        mModelDesignAdapter=new ModelDesignAdapter(R.layout.item_home_design,mModelDesigns);
        mModelDesignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(), DesignDetailActivity.class));
            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_design.setAdapter(mModelDesignAdapter);
    }

}
