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
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.game.services.GameTickService;
import com.theelixirist.f112813.ui.views.PixelPerfectImageButton;

public class MainActivity extends AppCompatActivity {
    // Views
    TextView tvElixirsCount;
    TextView tvElixirsPerSecond;
    PixelPerfectImageButton ppibElixirSprite;
    ImageButton ibMain;
    ImageButton ibMarket;
    ImageButton ibChronicle;

    Chronicle chronicle;

    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private final Runnable uiUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI();
            uiHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, GameTickService.class);
        startService(serviceIntent);

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        tvElixirsCount = findViewById(R.id.main_tv_elixirs_count);
        tvElixirsPerSecond = findViewById(R.id.main_tv_elixirs_per_second);
        ppibElixirSprite = findViewById(R.id.main_ppib_elixir_sprite);
        ibMain = findViewById(R.id.main_ib_main);
        ibMarket = findViewById(R.id.main_ib_market);
        ibChronicle = findViewById(R.id.main_ib_chronicle);

        chronicle = ElixiristApp.get(this).getAppContainer().getChronicle();

        updateUI();

        ppibElixirSprite.setOnClickListener(v -> onBrew());
        ibMain.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibChronicle.setOnClickListener(v -> onChronicleClicked());
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

    @Override
    protected void onStop() {
        super.onStop();
        ElixiristApp.get(this).getAppContainer().getSaveManager().save();
    }

    private void onBrew() {
        chronicle.getCurrentElixirs().add(1, 0);
        chronicle.getTotalElixirsBrewed().add(1, 0);

        updateUI();

        ElixiristApp.get(this).getAudioManager().play("brew", 1);

        ppibElixirSprite.animate().cancel();
        ppibElixirSprite.setScaleX(1.0f);
        ppibElixirSprite.setScaleY(1.0f);
        ppibElixirSprite.animate()
                .scaleX(0.90f)
                .scaleY(0.90f)
                .setDuration(25)
                .withEndAction(() -> ppibElixirSprite.animate()
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

    private void onChronicleClicked() {
        Intent intent = new Intent(MainActivity.this, ChronicleActivity.class);
        startActivity(intent);

        overridePendingTransition(0, 0);

        ElixiristApp.get(this).getAudioManager().play("tab_switch", 1);
    }

    private void updateUI() {
        String formattedTotalElixirs = BigDoubleFormatter.format(chronicle.getCurrentElixirs());
        String formattedElixirsPerSecond = BigDoubleFormatter.format(chronicle.getYieldPerSecond());

        tvElixirsCount.setText(getString(R.string.elixirs_count_label, formattedTotalElixirs));
        tvElixirsPerSecond.setText(getString(R.string.elixirs_per_second_label, formattedElixirsPerSecond));
    }
}
