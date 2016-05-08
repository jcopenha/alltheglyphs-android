package org.typedef.jcopenha.alltheglyphs;


/**
 * Created by jcopenha on 5/8/16.
 */
public class UnicodeBlocks {

    private static UnicodeBlock blocks[] ={
        new UnicodeBlock("Basic Latin",0x0000,0x007F),
        new UnicodeBlock("Latin-1 Supplement",0x0080,0x00FF),
        new UnicodeBlock("Latin Extended-A",0x0100,0x017F),
        new UnicodeBlock("Latin Extended-B",0x0180,0x024F),
        new UnicodeBlock("IPA Extensions",0x0250,0x02AF),
        new UnicodeBlock("Spacing Modifier Letters",0x02B0,0x02FF),
        new UnicodeBlock("Combining Diacritical Marks",0x0300,0x036F),
        new UnicodeBlock("Greek and Coptic",0x0370,0x03FF),
        new UnicodeBlock("Cyrillic",0x0400,0x04FF),
        new UnicodeBlock("Cyrillic Supplement",0x0500,0x052F),
        new UnicodeBlock("Armenian",0x0530,0x058F),
        new UnicodeBlock("Hebrew",0x0590,0x05FF),
        new UnicodeBlock("Arabic",0x0600,0x06FF),
        new UnicodeBlock("Syriac",0x0700,0x074F),
        new UnicodeBlock("Arabic Supplement",0x0750,0x077F),
        new UnicodeBlock("Thaana",0x0780,0x07BF),
        new UnicodeBlock("NKo",0x07C0,0x07FF),
        new UnicodeBlock("Samaritan",0x0800,0x083F),
        new UnicodeBlock("Mandaic",0x0840,0x085F),
        new UnicodeBlock("Arabic Extended-A",0x08A0,0x08FF),
        new UnicodeBlock("Devanagari",0x0900,0x097F),
        new UnicodeBlock("Bengali",0x0980,0x09FF),
        new UnicodeBlock("Gurmukhi",0x0A00,0x0A7F),
        new UnicodeBlock("Gujarati",0x0A80,0x0AFF),
        new UnicodeBlock("Oriya",0x0B00,0x0B7F),
        new UnicodeBlock("Tamil",0x0B80,0x0BFF),
        new UnicodeBlock("Telugu",0x0C00,0x0C7F),
        new UnicodeBlock("Kannada",0x0C80,0x0CFF),
        new UnicodeBlock("Malayalam",0x0D00,0x0D7F),
        new UnicodeBlock("Sinhala",0x0D80,0x0DFF),
        new UnicodeBlock("Thai",0x0E00,0x0E7F),
        new UnicodeBlock("Lao",0x0E80,0x0EFF),
        new UnicodeBlock("Tibetan",0x0F00,0x0FFF),
        new UnicodeBlock("Myanmar",0x1000,0x109F),
        new UnicodeBlock("Georgian",0x10A0,0x10FF),
        new UnicodeBlock("Hangul Jamo",0x1100,0x11FF),
        new UnicodeBlock("Ethiopic",0x1200,0x137F),
        new UnicodeBlock("Ethiopic Supplement",0x1380,0x139F),
        new UnicodeBlock("Cherokee",0x13A0,0x13FF),
        new UnicodeBlock("Unified Canadian Aboriginal Syllabics",0x1400,0x167F),
        new UnicodeBlock("Ogham",0x1680,0x169F),
        new UnicodeBlock("Runic",0x16A0,0x16FF),
        new UnicodeBlock("Tagalog",0x1700,0x171F),
        new UnicodeBlock("Hanunoo",0x1720,0x173F),
        new UnicodeBlock("Buhid",0x1740,0x175F),
        new UnicodeBlock("Tagbanwa",0x1760,0x177F),
        new UnicodeBlock("Khmer",0x1780,0x17FF),
        new UnicodeBlock("Mongolian",0x1800,0x18AF),
        new UnicodeBlock("Unified Canadian Aboriginal Syllabics Extended",0x18B0,0x18FF),
        new UnicodeBlock("Limbu",0x1900,0x194F),
        new UnicodeBlock("Tai Le",0x1950,0x197F),
        new UnicodeBlock("New Tai Lue",0x1980,0x19DF),
        new UnicodeBlock("Khmer Symbols",0x19E0,0x19FF),
        new UnicodeBlock("Buginese",0x1A00,0x1A1F),
        new UnicodeBlock("Tai Tham",0x1A20,0x1AAF),
        new UnicodeBlock("Combining Diacritical Marks Extended",0x1AB0,0x1AFF),
        new UnicodeBlock("Balinese",0x1B00,0x1B7F),
        new UnicodeBlock("Sundanese",0x1B80,0x1BBF),
        new UnicodeBlock("Batak",0x1BC0,0x1BFF),
        new UnicodeBlock("Lepcha",0x1C00,0x1C4F),
        new UnicodeBlock("Ol Chiki",0x1C50,0x1C7F),
        new UnicodeBlock("Sundanese Supplement",0x1CC0,0x1CCF),
        new UnicodeBlock("Vedic Extensions",0x1CD0,0x1CFF),
        new UnicodeBlock("Phonetic Extensions",0x1D00,0x1D7F),
        new UnicodeBlock("Phonetic Extensions Supplement",0x1D80,0x1DBF),
        new UnicodeBlock("Combining Diacritical Marks Supplement",0x1DC0,0x1DFF),
        new UnicodeBlock("Latin Extended Additional",0x1E00,0x1EFF),
        new UnicodeBlock("Greek Extended",0x1F00,0x1FFF),
        new UnicodeBlock("General Punctuation",0x2000,0x206F),
        new UnicodeBlock("Superscripts and Subscripts",0x2070,0x209F),
        new UnicodeBlock("Currency Symbols",0x20A0,0x20CF),
        new UnicodeBlock("Combining Diacritical Marks for Symbols",0x20D0,0x20FF),
        new UnicodeBlock("Letterlike Symbols",0x2100,0x214F),
        new UnicodeBlock("Number Forms",0x2150,0x218F),
        new UnicodeBlock("Arrows",0x2190,0x21FF),
        new UnicodeBlock("Mathematical Operators",0x2200,0x22FF),
        new UnicodeBlock("Miscellaneous Technical",0x2300,0x23FF),
        new UnicodeBlock("Control Pictures",0x2400,0x243F),
        new UnicodeBlock("Optical Character Recognition",0x2440,0x245F),
        new UnicodeBlock("Enclosed Alphanumerics",0x2460,0x24FF),
        new UnicodeBlock("Box Drawing",0x2500,0x257F),
        new UnicodeBlock("Block Elements",0x2580,0x259F),
        new UnicodeBlock("Geometric Shapes",0x25A0,0x25FF),
        new UnicodeBlock("Miscellaneous Symbols",0x2600,0x26FF),
        new UnicodeBlock("Dingbats",0x2700,0x27BF),
        new UnicodeBlock("Miscellaneous Mathematical Symbols-A",0x27C0,0x27EF),
        new UnicodeBlock("Supplemental Arrows-A",0x27F0,0x27FF),
        new UnicodeBlock("Braille Patterns",0x2800,0x28FF),
        new UnicodeBlock("Supplemental Arrows-B",0x2900,0x297F),
        new UnicodeBlock("Miscellaneous Mathematical Symbols-B",0x2980,0x29FF),
        new UnicodeBlock("Supplemental Mathematical Operators",0x2A00,0x2AFF),
        new UnicodeBlock("Miscellaneous Symbols and Arrows",0x2B00,0x2BFF),
        new UnicodeBlock("Glagolitic",0x2C00,0x2C5F),
        new UnicodeBlock("Latin Extended-C",0x2C60,0x2C7F),
        new UnicodeBlock("Coptic",0x2C80,0x2CFF),
        new UnicodeBlock("Georgian Supplement",0x2D00,0x2D2F),
        new UnicodeBlock("Tifinagh",0x2D30,0x2D7F),
        new UnicodeBlock("Ethiopic Extended",0x2D80,0x2DDF),
        new UnicodeBlock("Cyrillic Extended-A",0x2DE0,0x2DFF),
        new UnicodeBlock("Supplemental Punctuation",0x2E00,0x2E7F),
        new UnicodeBlock("CJK Radicals Supplement",0x2E80,0x2EFF),
        new UnicodeBlock("Kangxi Radicals",0x2F00,0x2FDF),
        new UnicodeBlock("Ideographic Description Characters",0x2FF0,0x2FFF),
        new UnicodeBlock("CJK Symbols and Punctuation",0x3000,0x303F),
        new UnicodeBlock("Hiragana",0x3040,0x309F),
        new UnicodeBlock("Katakana",0x30A0,0x30FF),
        new UnicodeBlock("Bopomofo",0x3100,0x312F),
        new UnicodeBlock("Hangul Compatibility Jamo",0x3130,0x318F),
        new UnicodeBlock("Kanbun",0x3190,0x319F),
        new UnicodeBlock("Bopomofo Extended",0x31A0,0x31BF),
        new UnicodeBlock("CJK Strokes",0x31C0,0x31EF),
        new UnicodeBlock("Katakana Phonetic Extensions",0x31F0,0x31FF),
        new UnicodeBlock("Enclosed CJK Letters and Months",0x3200,0x32FF),
        new UnicodeBlock("CJK Compatibility",0x3300,0x33FF),
        new UnicodeBlock("CJK Unified Ideographs Extension A",0x3400,0x4DBF),
        new UnicodeBlock("Yijing Hexagram Symbols",0x4DC0,0x4DFF),
        new UnicodeBlock("CJK Unified Ideographs",0x4E00,0x9FFF),
        new UnicodeBlock("Yi Syllables",0xA000,0xA48F),
        new UnicodeBlock("Yi Radicals",0xA490,0xA4CF),
        new UnicodeBlock("Lisu",0xA4D0,0xA4FF),
        new UnicodeBlock("Vai",0xA500,0xA63F),
        new UnicodeBlock("Cyrillic Extended-B",0xA640,0xA69F),
        new UnicodeBlock("Bamum",0xA6A0,0xA6FF),
        new UnicodeBlock("Modifier Tone Letters",0xA700,0xA71F),
        new UnicodeBlock("Latin Extended-D",0xA720,0xA7FF),
        new UnicodeBlock("Syloti Nagri",0xA800,0xA82F),
        new UnicodeBlock("Common Indic Number Forms",0xA830,0xA83F),
        new UnicodeBlock("Phags-pa",0xA840,0xA87F),
        new UnicodeBlock("Saurashtra",0xA880,0xA8DF),
        new UnicodeBlock("Devanagari Extended",0xA8E0,0xA8FF),
        new UnicodeBlock("Kayah Li",0xA900,0xA92F),
        new UnicodeBlock("Rejang",0xA930,0xA95F),
        new UnicodeBlock("Hangul Jamo Extended-A",0xA960,0xA97F),
        new UnicodeBlock("Javanese",0xA980,0xA9DF),
        new UnicodeBlock("Myanmar Extended-B",0xA9E0,0xA9FF),
        new UnicodeBlock("Cham",0xAA00,0xAA5F),
        new UnicodeBlock("Myanmar Extended-A",0xAA60,0xAA7F),
        new UnicodeBlock("Tai Viet",0xAA80,0xAADF),
        new UnicodeBlock("Meetei Mayek Extensions",0xAAE0,0xAAFF),
        new UnicodeBlock("Ethiopic Extended-A",0xAB00,0xAB2F),
        new UnicodeBlock("Latin Extended-E",0xAB30,0xAB6F),
        new UnicodeBlock("Cherokee Supplement",0xAB70,0xABBF),
        new UnicodeBlock("Meetei Mayek",0xABC0,0xABFF),
        new UnicodeBlock("Hangul Syllables",0xAC00,0xD7AF),
        new UnicodeBlock("Hangul Jamo Extended-B",0xD7B0,0xD7FF),
        new UnicodeBlock("High Surrogates",0xD800,0xDB7F),
        new UnicodeBlock("High Private Use Surrogates",0xDB80,0xDBFF),
        new UnicodeBlock("Low Surrogates",0xDC00,0xDFFF),
        new UnicodeBlock("Private Use Area",0xE000,0xF8FF),
        new UnicodeBlock("CJK Compatibility Ideographs",0xF900,0xFAFF),
        new UnicodeBlock("Alphabetic Presentation Forms",0xFB00,0xFB4F),
        new UnicodeBlock("Arabic Presentation Forms-A",0xFB50,0xFDFF),
        new UnicodeBlock("Variation Selectors",0xFE00,0xFE0F),
        new UnicodeBlock("Vertical Forms",0xFE10,0xFE1F),
        new UnicodeBlock("Combining Half Marks",0xFE20,0xFE2F),
        new UnicodeBlock("CJK Compatibility Forms",0xFE30,0xFE4F),
        new UnicodeBlock("Small Form Variants",0xFE50,0xFE6F),
        new UnicodeBlock("Arabic Presentation Forms-B",0xFE70,0xFEFF),
        new UnicodeBlock("Halfwidth and Fullwidth Forms",0xFF00,0xFFEF),
        new UnicodeBlock("Specials",0xFFF0,0xFFFF),
        new UnicodeBlock("Linear B Syllabary",0x10000,0x1007F),
        new UnicodeBlock("Linear B Ideograms",0x10080,0x100FF),
        new UnicodeBlock("Aegean Numbers",0x10100,0x1013F),
        new UnicodeBlock("Ancient Greek Numbers",0x10140,0x1018F),
        new UnicodeBlock("Ancient Symbols",0x10190,0x101CF),
        new UnicodeBlock("Phaistos Disc",0x101D0,0x101FF),
        new UnicodeBlock("Lycian",0x10280,0x1029F),
        new UnicodeBlock("Carian",0x102A0,0x102DF),
        new UnicodeBlock("Coptic Epact Numbers",0x102E0,0x102FF),
        new UnicodeBlock("Old Italic",0x10300,0x1032F),
        new UnicodeBlock("Gothic",0x10330,0x1034F),
        new UnicodeBlock("Old Permic",0x10350,0x1037F),
        new UnicodeBlock("Ugaritic",0x10380,0x1039F),
        new UnicodeBlock("Old Persian",0x103A0,0x103DF),
        new UnicodeBlock("Deseret",0x10400,0x1044F),
        new UnicodeBlock("Shavian",0x10450,0x1047F),
        new UnicodeBlock("Osmanya",0x10480,0x104AF),
        new UnicodeBlock("Elbasan",0x10500,0x1052F),
        new UnicodeBlock("Caucasian Albanian",0x10530,0x1056F),
        new UnicodeBlock("Linear A",0x10600,0x1077F),
        new UnicodeBlock("Cypriot Syllabary",0x10800,0x1083F),
        new UnicodeBlock("Imperial Aramaic",0x10840,0x1085F),
        new UnicodeBlock("Palmyrene",0x10860,0x1087F),
        new UnicodeBlock("Nabataean",0x10880,0x108AF),
        new UnicodeBlock("Hatran",0x108E0,0x108FF),
        new UnicodeBlock("Phoenician",0x10900,0x1091F),
        new UnicodeBlock("Lydian",0x10920,0x1093F),
        new UnicodeBlock("Meroitic Hieroglyphs",0x10980,0x1099F),
        new UnicodeBlock("Meroitic Cursive",0x109A0,0x109FF),
        new UnicodeBlock("Kharoshthi",0x10A00,0x10A5F),
        new UnicodeBlock("Old South Arabian",0x10A60,0x10A7F),
        new UnicodeBlock("Old North Arabian",0x10A80,0x10A9F),
        new UnicodeBlock("Manichaean",0x10AC0,0x10AFF),
        new UnicodeBlock("Avestan",0x10B00,0x10B3F),
        new UnicodeBlock("Inscriptional Parthian",0x10B40,0x10B5F),
        new UnicodeBlock("Inscriptional Pahlavi",0x10B60,0x10B7F),
        new UnicodeBlock("Psalter Pahlavi",0x10B80,0x10BAF),
        new UnicodeBlock("Old Turkic",0x10C00,0x10C4F),
        new UnicodeBlock("Old Hungarian",0x10C80,0x10CFF),
        new UnicodeBlock("Rumi Numeral Symbols",0x10E60,0x10E7F),
        new UnicodeBlock("Brahmi",0x11000,0x1107F),
        new UnicodeBlock("Kaithi",0x11080,0x110CF),
        new UnicodeBlock("Sora Sompeng",0x110D0,0x110FF),
        new UnicodeBlock("Chakma",0x11100,0x1114F),
        new UnicodeBlock("Mahajani",0x11150,0x1117F),
        new UnicodeBlock("Sharada",0x11180,0x111DF),
        new UnicodeBlock("Sinhala Archaic Numbers",0x111E0,0x111FF),
        new UnicodeBlock("Khojki",0x11200,0x1124F),
        new UnicodeBlock("Multani",0x11280,0x112AF),
        new UnicodeBlock("Khudawadi",0x112B0,0x112FF),
        new UnicodeBlock("Grantha",0x11300,0x1137F),
        new UnicodeBlock("Tirhuta",0x11480,0x114DF),
        new UnicodeBlock("Siddham",0x11580,0x115FF),
        new UnicodeBlock("Modi",0x11600,0x1165F),
        new UnicodeBlock("Takri",0x11680,0x116CF),
        new UnicodeBlock("Ahom",0x11700,0x1173F),
        new UnicodeBlock("Warang Citi",0x118A0,0x118FF),
        new UnicodeBlock("Pau Cin Hau",0x11AC0,0x11AFF),
        new UnicodeBlock("Cuneiform",0x12000,0x123FF),
        new UnicodeBlock("Cuneiform Numbers and Punctuation",0x12400,0x1247F),
        new UnicodeBlock("Early Dynastic Cuneiform",0x12480,0x1254F),
        new UnicodeBlock("Egyptian Hieroglyphs",0x13000,0x1342F),
        new UnicodeBlock("Anatolian Hieroglyphs",0x14400,0x1467F),
        new UnicodeBlock("Bamum Supplement",0x16800,0x16A3F),
        new UnicodeBlock("Mro",0x16A40,0x16A6F),
        new UnicodeBlock("Bassa Vah",0x16AD0,0x16AFF),
        new UnicodeBlock("Pahawh Hmong",0x16B00,0x16B8F),
        new UnicodeBlock("Miao",0x16F00,0x16F9F),
        new UnicodeBlock("Kana Supplement",0x1B000,0x1B0FF),
        new UnicodeBlock("Duployan",0x1BC00,0x1BC9F),
        new UnicodeBlock("Shorthand Format Controls",0x1BCA0,0x1BCAF),
        new UnicodeBlock("Byzantine Musical Symbols",0x1D000,0x1D0FF),
        new UnicodeBlock("Musical Symbols",0x1D100,0x1D1FF),
        new UnicodeBlock("Ancient Greek Musical Notation",0x1D200,0x1D24F),
        new UnicodeBlock("Tai Xuan Jing Symbols",0x1D300,0x1D35F),
        new UnicodeBlock("Counting Rod Numerals",0x1D360,0x1D37F),
        new UnicodeBlock("Mathematical Alphanumeric Symbols",0x1D400,0x1D7FF),
        new UnicodeBlock("Sutton SignWriting",0x1D800,0x1DAAF),
        new UnicodeBlock("Mende Kikakui",0x1E800,0x1E8DF),
        new UnicodeBlock("Arabic Mathematical Alphabetic Symbols",0x1EE00,0x1EEFF),
        new UnicodeBlock("Mahjong Tiles",0x1F000,0x1F02F),
        new UnicodeBlock("Domino Tiles",0x1F030,0x1F09F),
        new UnicodeBlock("Playing Cards",0x1F0A0,0x1F0FF),
        new UnicodeBlock("Enclosed Alphanumeric Supplement",0x1F100,0x1F1FF),
        new UnicodeBlock("Enclosed Ideographic Supplement",0x1F200,0x1F2FF),
        new UnicodeBlock("Miscellaneous Symbols and Pictographs",0x1F300,0x1F5FF),
        new UnicodeBlock("Emoticons",0x1F600,0x1F64F),
        new UnicodeBlock("Ornamental Dingbats",0x1F650,0x1F67F),
        new UnicodeBlock("Transport and Map Symbols",0x1F680,0x1F6FF),
        new UnicodeBlock("Alchemical Symbols",0x1F700,0x1F77F),
        new UnicodeBlock("Geometric Shapes Extended",0x1F780,0x1F7FF),
        new UnicodeBlock("Supplemental Arrows-C",0x1F800,0x1F8FF),
        new UnicodeBlock("Supplemental Symbols and Pictographs",0x1F900,0x1F9FF),
        new UnicodeBlock("CJK Unified Ideographs Extension B",0x20000,0x2A6DF),
        new UnicodeBlock("CJK Unified Ideographs Extension C",0x2A700,0x2B73F),
        new UnicodeBlock("CJK Unified Ideographs Extension D",0x2B740,0x2B81F),
        new UnicodeBlock("CJK Unified Ideographs Extension E",0x2B820,0x2CEAF),
        new UnicodeBlock("CJK Compatibility Ideographs Supplement",0x2F800,0x2FA1F),
        new UnicodeBlock("Tags",0xE0000,0xE007F),
        new UnicodeBlock("Variation Selectors Supplement",0xE0100,0xE01EF),
        new UnicodeBlock("Supplementary Private Use Area-A",0xF0000,0xFFFFF),
        new UnicodeBlock("Supplementary Private Use Area-B",0x100000,0x10FFFF)
    };

    public static int getCountOfBlocks() {
        return blocks.length;
    }

    public static UnicodeBlock getUnicodeBlock(int n) {
        return blocks[n];
    }

}