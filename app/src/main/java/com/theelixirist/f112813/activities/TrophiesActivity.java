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

public class TrophiesActivity extends AppCompatActivity {
    // Views
    ImageButton ibPotion;
    ImageButton ibMarket;
    ImageButton ibQuests;
    ImageButton ibTrophies;

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

        ibPotion = findViewById(R.id.trophies_ib_potion);
        ibMarket = findViewById(R.id.trophies_ib_market);
        ibQuests = findViewById(R.id.trophies_ib_quests);
        ibTrophies = findViewById(R.id.trophies_ib_trophies);

        ibPotion.setOnClickListener(v -> onPotionClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibQuests.setOnClickListener(v -> onQuestsClicked());
        ibTrophies.setOnClickListener(v -> onTrophiesClicked());
    }

    private void onPotionClicked() {
        Intent intent = new Intent(TrophiesActivity.this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onMarketClicked() {
        Intent intent = new Intent(TrophiesActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onQuestsClicked() {
        Intent intent = new Intent(TrophiesActivity.this, QuestsActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onTrophiesClicked() {
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }
}
