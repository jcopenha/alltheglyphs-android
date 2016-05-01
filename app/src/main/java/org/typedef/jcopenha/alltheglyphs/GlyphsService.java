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

        private int generatePossibleChar() {
            SecureRandom sr = new SecureRandom();
            int u = sr.nextInt() & 0x1FFFFF;
            int n = 1;
            // crazy.. it's acutally hit 100 random chars that didn't
            // meet this criteria..
            while( n < 1000 && (u > 0x10FFFF ||
                    Character.getType(u) == Character.PRIVATE_USE ||
                    Character.getType(u) == Character.SPACE_SEPARATOR ||
                    Character.getName(u) == null)) {
                u = sr.nextInt() & 0x1FFFFF;
                n++;
            }
            Log.d("UNICODE", "Did " + n + " char lookup");
            return u;
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
            int u = 0;
            String[] fonts = null;
            int n = 0;
            do {
                u = generatePossibleChar();
                fonts = Antisquare.getSuitableFonts(u);
                n++;
            } while(fonts.length == 0 && n < 10); // limit of 10 just in case..
            String description = "U+" + String.format("%06X",u) + ": " + Character.getName(u);
            Log.d("UNICODE", description);
            Log.d("UNICODE", "Did " + n + " font lookup");

            String output = new String(Character.toChars(u));

            Typeface font = null;
            if(typefaceCache.containsKey(fonts[0])) {
                font = typefaceCache.get(fonts[0]);
            } else {
                font = Typeface.createFromAsset(getAssets(), fonts[0]);
                typefaceCache.put(fonts[0], font);
            }

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText(description, 10, 625, paint);

            paint.setTextSize(212);
            paint.setTypeface(font);
            canvas.drawText(output, 10, 425, paint);

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
