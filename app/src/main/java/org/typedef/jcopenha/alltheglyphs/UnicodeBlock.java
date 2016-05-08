package org.typedef.jcopenha.alltheglyphs;

/**
 * Created by jcopenha on 5/8/16.
 */
public class UnicodeBlock {
    private String Name;
    private int LowerBound;
    private int UpperBound;
    private String PreferencesKey;

    public UnicodeBlock(String name, int lower, int upper) {
        Name = name;
        LowerBound = lower;
        UpperBound = upper;
        PreferencesKey = "unicode_block_" + name.toLowerCase().replace(' ','_').replace('-','_');
    }

    public String getName() {
        return Name;
    }

    public int getLowerBound() {
        return LowerBound;
    }

    public int getUpperBound() {
        return UpperBound;
    }

    public String getPreferencesKey() {
        return PreferencesKey;
    }
}
