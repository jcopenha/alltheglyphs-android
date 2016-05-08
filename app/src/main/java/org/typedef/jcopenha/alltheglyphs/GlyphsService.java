package org.typedef.jcopenha.alltheglyphs;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.support.design.widget.CoordinatorLayout;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import android.view.View;
import android.content.Context;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.graphics.Bitmap;

import fr.free.nrw.Antisquare;

/**
 * Created by jcopenha on 4/25/16.
 */
public class GlyphsService extends WallpaperService {
    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new GlyphsWallpaperEngine();

    }

    private class GlyphsWallpaperEngine extends WallpaperService.Engine {
        private int frameDuration = 5000;

        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private Handler handler;
        private long lastUpdate;

        public GlyphsWallpaperEngine() {
            handler = new Handler();
        }

        private Runnable drawGIF = new Runnable() {
            public void run() {
                lastUpdate = System.currentTimeMillis();
                drawUnicodeGlyph();
            }
        };

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;
        }

        private HashMap<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

        private void drawUnicodeGlyph() {
            if(!visible)
                return;


            lastUpdate = System.currentTimeMillis();

            Canvas canvas = holder.lockCanvas();
            canvas.save();

            DisplayGlyph glyph = DisplayGlyph.Generate();

            Typeface font = null;
            if(typefaceCache.containsKey(glyph.getFontName())) {
                font = typefaceCache.get(glyph.getFontName());
            } else {
                font = Typeface.createFromAsset(getAssets(), glyph.getFontName());
                typefaceCache.put(glyph.getFontName(), font);
            }

            // clear the screen.
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout v = new RelativeLayout(getApplicationContext());
            inflater.inflate(R.layout.content_main, v, true);

            v.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                  RelativeLayout.LayoutParams.MATCH_PARENT));
            Log.i("UNICODE","w:" + canvas.getWidth() + ", h:" + canvas.getHeight());


            TextView text = (TextView)v.findViewById(R.id.unicode);
            text.setTypeface(font);
            text.setTextColor(Color.BLACK);
            text.setText(glyph.getGlyph()); // How to make Emojis bigger?
            // bigger emojis work just fine on actual phone
            // adjusted emulator to use 2GB RAM/ 256MB VM heap to see if that helps

            TextView name = (TextView)v.findViewById(R.id.unicode_name);
            name.setTextColor(Color.BLACK);
            name.setText(glyph.getGlyphName());

            // reusing the other view so lets hide the button.
            Button bt = (Button)v.findViewById(R.id.button);
            bt.setVisibility(View.INVISIBLE);

            // maybe there is a better way to do this but a few hours of furious google-programming
            // left me with this solution for now.
            v.measure(MeasureSpec.makeMeasureSpec(canvas.getWidth(),MeasureSpec.EXACTLY),
                      MeasureSpec.makeMeasureSpec(canvas.getHeight(),MeasureSpec.EXACTLY));
            v.layout(0, 0, canvas.getWidth(), canvas.getHeight());

            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            canvas.drawBitmap(b,0,0,null);
            //Log.i("UNICODE","vw:" + v.getWidth() + ", vh:" + v.getHeight());

            canvas.restore();
            holder.unlockCanvasAndPost(canvas);

            handler.removeCallbacks(drawGIF);
            handler.postDelayed(drawGIF, frameDuration);
        }
        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String refresh_value = sharedPref.getString("refresh_value", "5");
                frameDuration = Integer.parseInt(refresh_value) * 1000;

                handler.post(drawGIF);
            } else {
                handler.removeCallbacks(drawGIF);
            }
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }
    }


}
