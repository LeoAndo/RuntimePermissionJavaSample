package com.leoleo.runtimepermissionjavasample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final ActivityResultLauncher<String> requestPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                Log.d(TAG, "granted: " + granted);
            });

    private final ActivityResultLauncher<String[]> requestMultiplePermissions =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), granted -> {
                Log.d(TAG, "granted: " + granted.toString());
                final boolean isAllGranted = granted.values().stream().anyMatch(b -> b);
                Log.d(TAG, "isAllGranted: " + isAllGranted);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.single).setOnClickListener(v -> requestPermission());
        findViewById(R.id.multiple).setOnClickListener(v -> requestMultiplePermissions());
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            final boolean isDenied =
                    ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_DENIED;
            Log.d(TAG, "isDenied: " + isDenied);
            if (isDenied) {
                requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    private void requestMultiplePermissions() {
        final boolean isDenied =
                ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_CALENDAR
                ) == PackageManager.PERMISSION_DENIED;
        final boolean isDenied2 =
                ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_NUMBERS
                ) == PackageManager.PERMISSION_DENIED;
        Log.d(TAG, "isDenied: " + isDenied + " isDenied2: " + isDenied2);
        if (isDenied || isDenied2) {
            requestMultiplePermissions.launch(
                    new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.READ_PHONE_NUMBERS}
            );
        }
    }
}