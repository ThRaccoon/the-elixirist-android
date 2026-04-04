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

public class MarketActivity extends AppCompatActivity {
    // Views
    ImageButton ibTrophies;
    ImageButton ibQuests;
    ImageButton ibMain;

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

        AudioManager.getInstance(this);

        ibTrophies = findViewById(R.id.market_ib_trophies);
        ibQuests = findViewById(R.id.market_ib_quests);
        ibMain = findViewById(R.id.market_ib_main);

        ibTrophies.setOnClickListener(v -> onTrophiesPressed());
        ibQuests.setOnClickListener(v -> onQuestsPressed());
        ibMain.setOnClickListener(v -> onMainPressed());
    }

    private void onTrophiesPressed() {
        Intent intent = new Intent(MarketActivity.this, TrophiesActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onQuestsPressed() {
        Intent intent = new Intent(MarketActivity.this, QuestsActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onMainPressed() {
        Intent intent = new Intent(MarketActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }
}
