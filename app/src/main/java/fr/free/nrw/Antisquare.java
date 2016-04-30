package fr.free.nrw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Returns a list of suitable fonts for a given character or string.
 *
 * Copyright (c) 2012 Nicolas Raoul
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
public class Antisquare {



    /**
     * Separator between fonts in fonts lists.
     */
    public final static String FONTS_SEPARATOR = ";";

    /**
     * List of suitable fonts for a given character.
     * Data has to be generated beforehand, see DatabaseGenerator.
     */
    public static String[] getSuitableFonts(int character) {
        // Binary search in the Antisquare data.
        int offset = 0;
        int zone = 0;


        // HACK: the AntiquareData.zones exceeded the 65KB size for
        // static data so I split half of it into an inner class.
        // I was going to generalize it but then I realized the fontSets
        // class can get just as big (it just isn't in my case because
        // I'm only using one font) so.. ohh well.
        if(character < 135909)
            zone = Arrays.binarySearch(AntisquareData.zones, character);
        else {
            zone = Arrays.binarySearch(AntisquareData.Zones2.zones2, character);
            offset = AntisquareData.zones.length;
        }

        if (zone >= 0) {
            return AntisquareData.fontsSets[AntisquareData.mappings[zone+offset]];
        }
        else {
            // character is not the first of its zone, so look into the previous zone
            // binarySearch helpfully tells us where the character would be inserted.
            // See http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#binarySearch(char[], char)
            zone = -zone -1 -1; // Because binarySearch returns (-(insertion point) - 1)
            return AntisquareData.fontsSets[AntisquareData.mappings[zone+offset]];
        }
    }
    
    /**
     * List of suitable fonts for a given string.
     * Data has to be generated beforehand, see DatabaseGenerator.
     * If no font is totally suitable, the "most" suitable is returned.
     */
    public static String getSuitableFonts(String string) {
        // For each font, count how many characters of the string are correctly displayed.
        Map<String, Integer> votes = new HashMap<String, Integer>();
        for (char character : string.toCharArray()) {
            for (String font : getSuitableFonts(character)) {
                if (votes.containsKey(font)) {
                    int incrementedVoteCount = votes.get(font).intValue() + 1;
                    votes.put(font, new Integer(incrementedVoteCount));
                }
                else {
                    votes.put(font, new Integer(1));
                }
            }
        }
        // Find the font that correctly displays the highest number of characters.
        String mostSuitableFont = null;
        int mostSuitableFontVotes = 0;
        for(Map.Entry<String,Integer> entry : votes.entrySet()){
            if(entry.getValue().intValue() > mostSuitableFontVotes){
                mostSuitableFont = entry.getKey();
                mostSuitableFontVotes = entry.getValue();
            }
        }
        return mostSuitableFont;
    }
}
