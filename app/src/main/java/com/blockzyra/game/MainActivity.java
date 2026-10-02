package com.blockzyra.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    private static final int BG_COLOR = Color.rgb(4, 6, 31);

    /**
     * Professional fullscreen mode.
     * Android 11+ uses WindowInsetsController.
     * Older Android versions use legacy immersive flags.
     */
    private void enterFullscreen() {

        Window window = getWindow();

        window.setStatusBarColor(BG_COLOR);
        window.setNavigationBarColor(BG_COLOR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            window.setDecorFitsSystemWindows(false);

            WindowInsetsController controller =
                    window.getInsetsController();

            if (controller != null) {

                controller.hide(
                        WindowInsets.Type.statusBars()
                                | WindowInsets.Type.navigationBars()
                );

                controller.setSystemBarsBehavior(
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                );
            }

        } else {

            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Dark background immediately.
        // Prevents white flash before WebView appears.
        getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        getWindow().setStatusBarColor(BG_COLOR);
        getWindow().setNavigationBarColor(BG_COLOR);

        enterFullscreen();

        // Create WebView.
        webView = new WebView(this);

        webView.setBackgroundColor(BG_COLOR);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // WebView settings.
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);

        settings.setMediaPlaybackRequiresUserGesture(false);

        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);

        // Keep normal browser-like rendering.
        settings.setLoadWithOverviewMode(false);
        settings.setUseWideViewPort(false);

        // Chrome client for HTML/JS features.
        webView.setWebChromeClient(
                new WebChromeClient()
        );

        // Handle links safely.
        webView.setWebViewClient(
                new WebViewClient() {

                    @Override
                    public boolean shouldOverrideUrlLoading(
                            WebView view,
                            WebResourceRequest request
                    ) {

                        Uri uri = request.getUrl();

                        String scheme = uri.getScheme();

                        if (scheme != null &&
                                (scheme.equalsIgnoreCase("http")
                                        || scheme.equalsIgnoreCase("https"))) {

                            // Keep normal web resources inside WebView.
                            return false;
                        }

                        // Open special links externally.
                        try {

                            Intent intent =
                                    new Intent(
                                            Intent.ACTION_VIEW,
                                            uri
                                    );

                            startActivity(intent);

                        } catch (Exception ignored) {
                        }

                        return true;
                    }
                }
        );

        // Put WebView on screen.
        setContentView(webView);

        // Load the actual game.
        webView.loadUrl(
                "file:///android_asset/index.html"
        );

        // Make sure fullscreen remains active.
        enterFullscreen();
    }

    @Override
    protected void onResume() {
        super.onResume();

        enterFullscreen();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            enterFullscreen();
        }
    }

    @Override
    public void onBackPressed() {

        if (webView != null && webView.canGoBack()) {

            webView.goBack();

        } else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {

        if (webView != null) {

            webView.stopLoading();
            webView.destroy();
            webView = null;
        }

        super.onDestroy();
    }
}
