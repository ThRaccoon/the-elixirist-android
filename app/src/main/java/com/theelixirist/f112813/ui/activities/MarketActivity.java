package com.theelixirist.f112813.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.ui.fragments.GeneratorsFragment;
import com.theelixirist.f112813.ui.fragments.UpgradesFragment;

public class MarketActivity extends AppCompatActivity {
    // Views
    ImageButton ibMain;
    ImageButton ibMarket;
    ImageButton ibChronicle;

    // Buy section
    ViewPager2 viewPager;
    LinearLayout tabGenerators, tabUpgrades;
    View dotGenerators, dotUpgrades;
    TextView tvGenerators, tvUpgrades;

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

        // Buy section
        viewPager = findViewById(R.id.market_vp2);
        tabGenerators = findViewById(R.id.market_tab_generators);
        tabUpgrades = findViewById(R.id.market_tab_upgrades);
        dotGenerators = findViewById(R.id.market_dot_generators);
        dotUpgrades = findViewById(R.id.market_dot_upgrades);
        tvGenerators = findViewById(R.id.market_tv_tab_generators);
        tvUpgrades = findViewById(R.id.market_tv_tab_upgrades);

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 2;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return position == 0 ? new GeneratorsFragment() : new UpgradesFragment();
            }
        });

        tabGenerators.setOnClickListener(v -> {
            viewPager.setCurrentItem(0);
            ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
        });

        tabUpgrades.setOnClickListener(v -> {
            viewPager.setCurrentItem(1);
            ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                dotGenerators.setVisibility(position == 0 ? View.VISIBLE : View.INVISIBLE);
                dotUpgrades.setVisibility(position == 1 ? View.VISIBLE : View.INVISIBLE);
                tvGenerators.setTextColor(position == 0 ? 0xFFFFFFFF : 0x80FFFFFF);
                tvUpgrades.setTextColor(position == 1 ? 0xFFFFFFFF : 0x80FFFFFF);
            }
        });
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
