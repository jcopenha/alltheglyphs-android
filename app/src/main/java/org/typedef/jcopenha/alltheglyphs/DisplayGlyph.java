package org.typedef.jcopenha.alltheglyphs;

import android.util.Log;

import java.security.SecureRandom;

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

    public static DisplayGlyph Generate() {
        int u;
        String[] fonts;
        DisplayGlyph g = new DisplayGlyph();

        int n = 0;
        do {
            u = g.generatePossibleChar();
            fonts = Antisquare.getSuitableFonts(u);
            n++;
        } while(fonts.length == 0 && n < 10); // limit of 10 just in case..
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
