package com.theelixirist.f112813.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.theelixirist.f112813.AppContainer;
import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.domain.RequirementFilter;
import com.theelixirist.f112813.ui.adapters.UpgradeMarketAdapter;

import java.util.List;

public class UpgradesFragment extends Fragment {
    private UpgradeMarketAdapter adapter;
    private AppContainer appContainer;

    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private final Runnable uiUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            refreshList();
            uiHandler.postDelayed(this, 3000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_upgrades, container, false);

        appContainer = ElixiristApp.get(requireContext()).getAppContainer();

        adapter = new UpgradeMarketAdapter(appContainer, def -> {
            appContainer.getPurchaseSystem().buyUpgrade(def);
            refreshList();
        });

        RecyclerView rv = view.findViewById(R.id.upgrades_rv);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(adapter);

        refreshList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHandler.removeCallbacks(uiUpdateRunnable);
        uiHandler.post(uiUpdateRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHandler.removeCallbacks(uiUpdateRunnable);
    }

    private void refreshList() {
        List<UpgradeDefinition> visible = RequirementFilter.getVisible(
                appContainer.getUpgradeDefinitionRegistry().getUpgradeDefinitions().values(),
                def -> def.requirements,
                appContainer
        );

        visible.removeIf(def ->
                appContainer.getUpgradeRegistry().getUpgrades().containsKey(def.id)
        );

        adapter.setItems(visible);
    }
}
