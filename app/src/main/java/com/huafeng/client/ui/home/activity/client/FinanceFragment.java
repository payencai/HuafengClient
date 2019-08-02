package com.huafeng.client.ui.home.activity.client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huafeng.client.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanceFragment extends Fragment {
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    List<ClientFinance> clientFinances;
    ClientFinanceAdapter clientFinanceAdapter;
    int id;
    public FinanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_finance, container, false);
        id=getArguments().getInt("id");
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        clientFinances=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            clientFinances.add(new ClientFinance());
        }
        clientFinanceAdapter=new ClientFinanceAdapter(clientFinances);
        rv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_order.setAdapter(clientFinanceAdapter);
    }

    public static FinanceFragment newInstance(int id) {
        FinanceFragment financeFragment = new FinanceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        financeFragment.setArguments(bundle);
        return financeFragment;
    }
}
