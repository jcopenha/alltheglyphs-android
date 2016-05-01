package org.typedef.jcopenha.alltheglyphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;

import fr.free.nrw.Antisquare;

/**
 * Created by jcopenha on 4/25/16.
 */
public class GlyphsService extends WallpaperService {
    @Override
    public WallpaperService.Engine onCreateEngine() {
        //  Movie movie = Movie.decodeStream(
        //          getResources().getAssets().open("girl.gif"));

        return new GlyphsWallpaperEngine();

    }

    private class GlyphsWallpaperEngine extends WallpaperService.Engine {
        private final int frameDuration = 5000;

        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private Handler handler;
        private long lastUpdate;

        public GlyphsWallpaperEngine() {
            //this.movie = movie;
            handler = new Handler();
        }

        private Runnable drawGIF = new Runnable() {
            public void run() {
                //draw();
                lastUpdate = System.currentTimeMillis();
                drawUnicodeGlyph();
            }
        };

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;
        }
        private void draw() {
            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPaint(paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(20);
                canvas.drawText("Some Text", 10, 25, paint);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));

                handler.removeCallbacks(drawGIF);
                handler.postDelayed(drawGIF, frameDuration);
            }
        }

        private HashMap<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

        private void drawUnicodeGlyph() {
            if(!visible)
                return;
            // if(System.currentTimeMillis() - lastUpdate < 5000)
            //   return;

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

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText(glyph.getGlyphName(), 10, 625, paint);

            paint.setTextSize(212);
            paint.setTypeface(font);
            canvas.drawText(glyph.getGlyph(), 10, 425, paint);

            /*
            TextView text = (TextView)findViewById(R.id.unicode);
            text.setTypeface(font);
            text.setText(output); // How to make Emojis bigger?
            // bigger emojis work just fine on actual phone
            // adjusted emulator to use 2GB RAM/ 256MB VM heap to see if that helps

            TextView name = (TextView)findViewById(R.id.unicode_name);
            name.setText(description);
            */
            canvas.restore();
            holder.unlockCanvasAndPost(canvas);

            handler.removeCallbacks(drawGIF);
            handler.postDelayed(drawGIF, frameDuration);
        }
        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
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
