package com.theelixirist.f112813.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;

public class ChronicleActivity extends AppCompatActivity {
    // Views
    ImageButton ibMain;
    ImageButton ibMarket;
    ImageButton ibChronicle;

    // Stats
    TextView tvCurrentElixirs, tvTotalElixirs, tvYield,
            tvCatalysts, tvEffects, tvFirstPlayed, tvLastPlayed;

    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private final Runnable uiUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI();
            uiHandler.postDelayed(this, 3000);
        }
    };

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

        ibMain = findViewById(R.id.chronicle_ib_main);
        ibMarket = findViewById(R.id.chronicle_ib_market);
        ibChronicle = findViewById(R.id.chronicle_ib_chronicle);

        ibMain.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibChronicle.setOnClickListener(v -> onChronicleClicked());

        // Stats
        tvCurrentElixirs = findViewById(R.id.chronicle_tv_current_elixirs);
        tvTotalElixirs = findViewById(R.id.chronicle_tv_total_elixirs);
        tvYield = findViewById(R.id.chronicle_tv_yield);
        tvCatalysts = findViewById(R.id.chronicle_tv_catalysts);
        tvEffects = findViewById(R.id.chronicle_tv_effects);
        tvFirstPlayed = findViewById(R.id.chronicle_tv_first_played);
        tvLastPlayed = findViewById(R.id.chronicle_tv_last_played);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHandler.removeCallbacks(uiUpdateRunnable);
        uiHandler.post(uiUpdateRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        uiHandler.removeCallbacks(uiUpdateRunnable);
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

    private void updateUI() {
        Chronicle chronicle = ElixiristApp.get(this).getAppContainer().getChronicle();

        tvCurrentElixirs.setText(getString(R.string.chronicle_current_elixirs,
                BigDoubleFormatter.format(chronicle.getCurrentElixirs())));
        tvTotalElixirs.setText(getString(R.string.chronicle_total_brewed,
                BigDoubleFormatter.format(chronicle.getTotalElixirsBrewed())));
        tvYield.setText(getString(R.string.chronicle_yield,
                BigDoubleFormatter.format(chronicle.getYieldPerSecond())));
        tvCatalysts.setText(getString(R.string.chronicle_catalysts,
                String.valueOf(chronicle.getTotalCatalystsCollected())));
        tvEffects.setText(getString(R.string.chronicle_effects,
                String.valueOf(chronicle.getTotalEffectsTriggered())));

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy, HH:mm", java.util.Locale.getDefault());
        String firstPlayed = sdf.format(new java.util.Date(chronicle.getFirstPlayedAt()));

        tvFirstPlayed.setText(getString(R.string.chronicle_first_played, firstPlayed));
    }
}
