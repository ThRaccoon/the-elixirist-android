package com.theelixirist.f112813.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.theelixirist.f112813.R;
import com.theelixirist.f112813.managers.AudioManager;

public class TrophiesActivity extends AppCompatActivity {
    // Views
    ImageButton ibMain;
    ImageButton ibQuests;
    ImageButton ibMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trophies);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        AudioManager.getInstance(this);

        ibMain = findViewById(R.id.trophies_ib_main);
        ibQuests = findViewById(R.id.trophies_ib_quests);
        ibMarket = findViewById(R.id.trophies_ib_market);

        ibMain.setOnClickListener(v -> onMainPressed());
        ibQuests.setOnClickListener(v -> onQuestsPressed());
        ibMarket.setOnClickListener(v -> onMarketPressed());
    }

    private void onMainPressed() {
        Intent intent = new Intent(TrophiesActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onQuestsPressed() {
        Intent intent = new Intent(TrophiesActivity.this, QuestsActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onMarketPressed() {
        Intent intent = new Intent(TrophiesActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }
}
