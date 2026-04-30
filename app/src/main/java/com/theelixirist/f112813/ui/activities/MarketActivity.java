package com.theelixirist.f112813.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.ui.fragments.GeneratorsFragment;
import com.theelixirist.f112813.ui.fragments.UpgradesFragment;

import org.jetbrains.annotations.NotNull;

public class MarketActivity extends AppCompatActivity {
    ImageButton ibMain;
    ImageButton ibMarket;
    ImageButton ibChronicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_market);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        ibMain = findViewById(R.id.market_ib_main);
        ibMarket = findViewById(R.id.market_ib_market);
        ibChronicle = findViewById(R.id.market_ib_chronicle);

        ibMain.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibChronicle.setOnClickListener(v -> onChronicleClicked());

        ViewPager2 viewPager = findViewById(R.id.market_view_pager);
        TabLayout tabLayout = findViewById(R.id.market_tab_layout);

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NotNull
            @Override
            public Fragment createFragment(int position) {
                return position == 0 ? new GeneratorsFragment() : new UpgradesFragment();
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(position == 0 ? "Generators" : "Upgrades")
        ).attach();
    }

    private void onMainClicked() {
        Intent intent = new Intent(MarketActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onMarketClicked() {
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onChronicleClicked() {
        Intent intent = new Intent(MarketActivity.this, ChronicleActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }
}
