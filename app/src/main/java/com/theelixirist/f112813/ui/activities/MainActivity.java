package com.theelixirist.f112813.ui.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.game.Chronicle;
import com.theelixirist.f112813.game.GameState;
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

    private  boolean isBound = false;
    private GameTickService tickService;
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GameTickService.GameTickBinder binder = (GameTickService.GameTickBinder) service;
            tickService = binder.getService();
            tickService.setOnTick(() -> runOnUiThread(() -> updateUI()));
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

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

        tvElixirsCount = findViewById(R.id.main_tv_elixirs_count);
        tvElixirsPerSecond = findViewById(R.id.main_tv_elixirs_per_second);
        ppibElixirSprite = findViewById(R.id.main_ppib_elixir_sprite);
        ibMain = findViewById(R.id.main_ib_main);
        ibMarket = findViewById(R.id.main_ib_market);
        ibChronicle = findViewById(R.id.main_ib_chronicle);

        updateUI();

        ppibElixirSprite.setOnClickListener(v -> onBrew());
        ibMain.setOnClickListener(v -> onMainClicked());
        ibMarket.setOnClickListener(v -> onMarketClicked());
        ibChronicle.setOnClickListener(v -> onChronicleClicked());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, GameTickService.class);
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    private void onBrew() {
        GameState gameState = ElixiristApp.get(this).getGameState();
        Chronicle chronicle = ElixiristApp.get(this).getChronicle();

        gameState.addElixirs(gameState.computeElixirsPerClick());
        chronicle.addBrewedElixirs(gameState.computeElixirsPerClick());

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
        GameState gameState = ElixiristApp.get(this).getGameState();

        tvElixirsCount.setText(getString(R.string.elixirs_count_label,
                BigDoubleFormatter.format(gameState.getCurrentElixirs())));
        tvElixirsPerSecond.setText(getString(R.string.elixirs_per_second_label,
                BigDoubleFormatter.format(gameState.computeElixirsPerSecond())));
    }
}
