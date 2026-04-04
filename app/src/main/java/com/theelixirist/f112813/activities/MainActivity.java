package com.theelixirist.f112813.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.theelixirist.f112813.R;
import com.theelixirist.f112813.game.NumberFormatter;
import com.theelixirist.f112813.managers.AudioManager;
import com.theelixirist.f112813.views.PixelPerfectImageButton;

public class MainActivity extends AppCompatActivity {
    // Views
    TextView tvPotionCount;
    TextView tvPotionsPerSecond;
    PixelPerfectImageButton ppibPotionSprite;
    ImageButton ibTrophies;
    ImageButton ibQuests;
    ImageButton ibMarket;

    // Game Vars
    double totalPotions = 0;
    double potionsPerClick = 1;
    double potionsPerSecond = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        tvPotionCount = findViewById(R.id.main_tv_potion_count);
        tvPotionsPerSecond = findViewById(R.id.main_tv_potions_per_second);
        ppibPotionSprite = findViewById(R.id.main_ppib_potion_sprite);
        ibTrophies = findViewById(R.id.main_ib_trophies);
        ibQuests = findViewById(R.id.main_ib_quests);
        ibMarket = findViewById(R.id.main_ib_market);

        updateUI();
        AudioManager.getInstance(this);

        ppibPotionSprite.setOnClickListener(v -> onPotionPressed());
        ibTrophies.setOnClickListener(v -> onTrophiesPressed());
        ibQuests.setOnClickListener(v -> onQuestsPressed());
        ibMarket.setOnClickListener(v -> onMarketPressed());
    }

    private void onPotionPressed() {
        incrementPotions(potionsPerClick);

        AudioManager.getInstance(this).play("brew", 1);

        ppibPotionSprite.animate().cancel();
        ppibPotionSprite.setScaleX(1.0f);
        ppibPotionSprite.setScaleY(1.0f);
        ppibPotionSprite.animate()
                .scaleX(0.90f)
                .scaleY(0.90f)
                .setDuration(25)
                .withEndAction(() -> ppibPotionSprite.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(50)
                        .start()).start();
    }

    private void onTrophiesPressed() {
        Intent intent = new Intent(MainActivity.this, TrophiesActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onQuestsPressed() {
        Intent intent = new Intent(MainActivity.this, QuestsActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void onMarketPressed() {
        Intent intent = new Intent(MainActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        AudioManager.getInstance(this).play("tab_switch", 1);
    }

    private void incrementPotions(double amount) {
        totalPotions += amount;
        updateUI();
    }

    private void updateUI() {
        String formattedTotalPotions = NumberFormatter.format(totalPotions);
        String formattedPotionsPerSecond = NumberFormatter.format(potionsPerSecond);

        tvPotionCount.setText(getString(R.string.potion_count_label, formattedTotalPotions));
        tvPotionsPerSecond.setText(getString(R.string.potions_per_second_label, formattedPotionsPerSecond));
    }
}
