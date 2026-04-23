package com.theelixirist.f112813.game.data.loaders;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class BaseLoader {
    public static <T> List<T> load(Context context, String fileName, Type type) {
        try {
            InputStream is = context.getAssets().open("data/" + fileName);
            InputStreamReader reader = new InputStreamReader(is);
            return new Gson().fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }
}
