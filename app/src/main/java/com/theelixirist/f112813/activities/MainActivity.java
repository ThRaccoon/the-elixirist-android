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

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.views.PixelPerfectImageButton;

public class MainActivity extends AppCompatActivity {
    // Views
    TextView tvPotionCount;
    TextView tvPotionsPerSecond;
    PixelPerfectImageButton ppibPotionSprite;
    ImageButton ibMain;
    ImageButton ibMarket;
    ImageButton ibStats;

    // Game Vars
    BigDouble totalPotions = new BigDouble(0, 0);
    BigDouble potionsPerClick = new BigDouble(1, 0);
    BigDouble potionsPerSecond = new BigDouble(0, 0);

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
        ibMain = findViewById(R.id.main_ib_main);
        ibMarket = findViewById(R.id.main_ib_market);
        ibStats = findViewById(R.id.main_ib_stats);

        updateUI();

        ppibPotionSprite.setOnClickListener(v -> onBrew());
        ibMain.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibStats.setOnClickListener(v -> onStatsClicked());
    }

    private void onBrew() {
        incrementPotions(potionsPerClick);

        ElixiristApp.get(this).getAudioManager().play("brew", 1);

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

    private void onMainClicked() {
        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onMarketClicked() {
        Intent intent = new Intent(MainActivity.this, MarketActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void onStatsClicked() {
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void incrementPotions(BigDouble amount) {
        totalPotions.add(amount);
        updateUI();
    }

    private void updateUI() {
        String formattedTotalPotions = BigDoubleFormatter.format(totalPotions);
        String formattedPotionsPerSecond = BigDoubleFormatter.format(potionsPerSecond);

        tvPotionCount.setText(getString(R.string.potion_count_label, formattedTotalPotions));
        tvPotionsPerSecond.setText(getString(R.string.potions_per_second_label, formattedPotionsPerSecond));
    }
}
