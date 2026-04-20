package com.theelixirist.f112813.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;

public class StatsActivity extends AppCompatActivity {
    // Views
    ImageButton ibPotion;
    ImageButton ibMarket;
    ImageButton ibStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stats);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        ibPotion = findViewById(R.id.stats_ib_main);
        ibMarket = findViewById(R.id.stats_ib_market);
        ibStats = findViewById(R.id.stats_ib_stats);

        ibPotion.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibStats.setOnClickListener(v -> onStatsClicked());
    }

    private void onMainClicked() {
        Intent intent = new Intent(StatsActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onMarketClicked() {
        Intent intent = new Intent(StatsActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onStatsClicked() {
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }
}
