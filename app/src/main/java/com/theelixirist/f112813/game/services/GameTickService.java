package com.theelixirist.f112813.game.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.game.GameState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTickService extends Service {
    private final IBinder binder = new GameTickBinder();
    private ScheduledExecutorService scheduler;
    private Runnable onTick;

    public class GameTickBinder extends Binder {
        public GameTickService getService() {
            return GameTickService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTicking();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTicking();
    }

    public void setOnTick(Runnable onTick) {
        this.onTick = onTick;
    }

    private void startTicking() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(this::tick, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private void stopTicking() {
        if (scheduler != null) scheduler.shutdownNow();
    }

    private void tick() {
        GameState gs = ElixiristApp.get(this).getGameState();
        gs.addElixirs(gs.computeElixirsPerSecond());
        if (onTick != null) onTick.run();
    }
}
