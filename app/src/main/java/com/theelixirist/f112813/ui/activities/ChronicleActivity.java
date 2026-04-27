package com.theelixirist.f112813.ui.activities;

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

public class ChronicleActivity extends AppCompatActivity {
    // Views
    ImageButton ibPotion;
    ImageButton ibMarket;
    ImageButton ibChronicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chronicle);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        ibPotion = findViewById(R.id.chronicle_ib_main);
        ibMarket = findViewById(R.id.chronicle_ib_market);
        ibChronicle = findViewById(R.id.chronicle_ib_chronicle);

        ibPotion.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibChronicle.setOnClickListener(v -> onChronicleClicked());
    }

    private void onMainClicked() {
        Intent intent = new Intent(ChronicleActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onMarketClicked() {
        Intent intent = new Intent(ChronicleActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onChronicleClicked() {
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }
}
