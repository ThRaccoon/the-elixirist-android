package com.theelixirist.f112813.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.ui.adapters.UpgradesAdapter;

public class UpgradesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_market_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new UpgradesAdapter(
                ElixiristApp.get(requireContext()).getDefinitionRegistry(),
                ElixiristApp.get(requireContext()).getGameState(),
                ElixiristApp.get(requireContext()).getChronicle()
        ));

        return view;
    }
}
