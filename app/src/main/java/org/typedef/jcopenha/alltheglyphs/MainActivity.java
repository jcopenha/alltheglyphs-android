package org.typedef.jcopenha.alltheglyphs;
import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import java.lang.Character;
import java.security.SecureRandom;
import android.graphics.Typeface;
import fr.free.nrw.Antisquare;
import java.util.HashMap;
import org.typedef.jcopenha.alltheglyphs.DisplayGlyph;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        TextView text = (TextView)findViewById(R.id.unicode);
        // was using this when size was large to try and
        // get emoji to draw but it didn't work..
        //text.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        DisplayGlyph glyph = DisplayGlyph.Generate();

                        Typeface font = null;
                        if(typefaceCache.containsKey(glyph.getFontName())) {
                            font = typefaceCache.get(glyph.getFontName());
                        } else {
                            font = Typeface.createFromAsset(getAssets(), glyph.getFontName());
                            typefaceCache.put(glyph.getFontName(), font);
                        }

                        TextView text = (TextView)findViewById(R.id.unicode);
                        text.setTypeface(font);
                        text.setText(glyph.getGlyph()); // How to make Emojis bigger?
                        // bigger emojis work just fine on actual phone
                        // adjusted emulator to use 2GB RAM/ 256MB VM heap to see if that helps

                        TextView name = (TextView)findViewById(R.id.unicode_name);
                        name.setText(glyph.getGlyphName());
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
