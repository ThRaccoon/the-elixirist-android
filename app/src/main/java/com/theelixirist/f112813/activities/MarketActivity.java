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

public class MarketActivity extends AppCompatActivity {
    // Views
    ImageButton ibPotion;
    ImageButton ibMarket;
    ImageButton ibStats;

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

        ibPotion = findViewById(R.id.market_ib_main);
        ibMarket = findViewById(R.id.market_ib_market);
        ibStats = findViewById(R.id.market_ib_chronicle);

        ibPotion.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibStats.setOnClickListener(v -> onStatsClicked());
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

    private void onStatsClicked() {
        Intent intent = new Intent(MarketActivity.this, ChronicleActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }
}
