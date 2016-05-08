package org.typedef.jcopenha.alltheglyphs;

import android.util.Log;

import java.security.SecureRandom;
import java.util.ArrayList;

import fr.free.nrw.Antisquare;

/**
 * Created by jcopenha on 5/1/16.
 */
public class DisplayGlyph {
    private String font;
    private String glyph;
    private String glyphName;

    public String getFontName() {
        return font;
    }

    public String getGlyph() {
        return glyph;
    }

    public String getGlyphName() {
        return glyphName;
    }

    // all this work is an attempt to get random chars evenly distributed
    // amongst the unfiltered code points.

    private int getCharInRange() {
        SecureRandom sr = new SecureRandom();
        int u = 0;
        ArrayList<int[]> ranges = UnicodeBlocks.getBlockRanges();
        int total = 0;
        for(int x = 0; x < ranges.size(); x ++) {
            total += ranges.get(x)[1] - ranges.get(x)[0] + 1; // inclusive ranges
        }
        int c = sr.nextInt(total);
        total = 0;
        for(int x = 0; x < ranges.size(); x ++) {
            int lasttotal = total;
            total += ranges.get(x)[1] - ranges.get(x)[0] + 1; // inclusive ranges
            if(c < total) {
                u = ranges.get(x)[0] + (c-lasttotal);
                break;
            }
        }
        return u;
    }

    private int generatePossibleChar() {
        int u = getCharInRange();
        int n = 1;
        // crazy.. it's acutally hit 100 random chars that didn't
        // meet this criteria..
        while( n < 1000 && (u > 0x10FFFF ||
                Character.getType(u) == Character.PRIVATE_USE ||
                Character.getType(u) == Character.SPACE_SEPARATOR ||
                Character.getName(u) == null)) {
            u = getCharInRange();
            n++;
        }
        Log.d("UNICODE", "Did " + n + " char lookup");
        return u;
    }

    public static DisplayGlyph Generate() {
        int u;
        String[] fonts;
        DisplayGlyph g = new DisplayGlyph();

        int n = 0;
        do {
            u = g.generatePossibleChar();
            fonts = Antisquare.getSuitableFonts(u);
            n++;
        } while(fonts.length == 0 && n < 100); // limit of 10 just in case..
        String description = "U+" + String.format("%06X",u) + ": " + Character.getName(u);
        Log.d("UNICODE", description);
        Log.d("UNICODE", "Did " + n + " font lookup");

        String output = new String(Character.toChars(u));

        g.glyph = output;
        g.glyphName = description;
        g.font = fonts[0];

        return g;
    }
}
