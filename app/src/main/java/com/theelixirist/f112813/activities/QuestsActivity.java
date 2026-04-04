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

public class QuestsActivity extends AppCompatActivity {
    // Views
    ImageButton ibTrophies;
    ImageButton ibMain;
    ImageButton ibMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quests);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        AudioManager.getInstance(this);

        ibTrophies = findViewById(R.id.quests_ib_trophies);
        ibMain = findViewById(R.id.quests_ib_main);
        ibMarket = findViewById(R.id.quests_ib_market);

        ibTrophies.setOnClickListener(v -> onTrophiesPressed());
        ibMain.setOnClickListener(v -> onMainPressed());
        ibMarket.setOnClickListener(v -> onMarketPressed());
    }

    private void onTrophiesPressed() {
        Intent intent = new Intent(QuestsActivity.this, TrophiesActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onMainPressed() {
        Intent intent = new Intent(QuestsActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onMarketPressed() {
        Intent intent = new Intent(QuestsActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }
}
