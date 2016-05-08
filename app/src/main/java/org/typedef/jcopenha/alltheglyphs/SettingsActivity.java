package org.typedef.jcopenha.alltheglyphs;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.TwoStatePreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("refresh_value"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {

        private final static String PrefKeys[] = {
            "unicode_block_basic_latin",
            "unicode_block_latin_1_supplement",
            "unicode_block_latin_extended_a",
            "unicode_block_latin_extended_b",
            "unicode_block_ipa_extensions",
            "unicode_block_spacing_modifier_letters",
            "unicode_block_combining_diacritical_marks",
            "unicode_block_greek_and_coptic",
            "unicode_block_cyrillic",
            "unicode_block_cyrillic_supplement",
            "unicode_block_armenian",
            "unicode_block_hebrew",
            "unicode_block_arabic",
            "unicode_block_syriac",
            "unicode_block_arabic_supplement",
            "unicode_block_thaana",
            "unicode_block_nko",
            "unicode_block_samaritan",
            "unicode_block_mandaic",
            "unicode_block_arabic_extended_a",
            "unicode_block_devanagari",
            "unicode_block_bengali",
            "unicode_block_gurmukhi",
            "unicode_block_gujarati",
            "unicode_block_oriya",
            "unicode_block_tamil",
            "unicode_block_telugu",
            "unicode_block_kannada",
            "unicode_block_malayalam",
            "unicode_block_sinhala",
            "unicode_block_thai",
            "unicode_block_lao",
            "unicode_block_tibetan",
            "unicode_block_myanmar",
            "unicode_block_georgian",
            "unicode_block_hangul_jamo",
            "unicode_block_ethiopic",
            "unicode_block_ethiopic_supplement",
            "unicode_block_cherokee",
            "unicode_block_unified_canadian_aboriginal_syllabics",
            "unicode_block_ogham",
            "unicode_block_runic",
            "unicode_block_tagalog",
            "unicode_block_hanunoo",
            "unicode_block_buhid",
            "unicode_block_tagbanwa",
            "unicode_block_khmer",
            "unicode_block_mongolian",
            "unicode_block_unified_canadian_aboriginal_syllabics_extended",
            "unicode_block_limbu",
            "unicode_block_tai_le",
            "unicode_block_new_tai_lue",
            "unicode_block_khmer_symbols",
            "unicode_block_buginese",
            "unicode_block_tai_tham",
            "unicode_block_combining_diacritical_marks_extended",
            "unicode_block_balinese",
            "unicode_block_sundanese",
            "unicode_block_batak",
            "unicode_block_lepcha",
            "unicode_block_ol_chiki",
            "unicode_block_sundanese_supplement",
            "unicode_block_vedic_extensions",
            "unicode_block_phonetic_extensions",
            "unicode_block_phonetic_extensions_supplement",
            "unicode_block_combining_diacritical_marks_supplement",
            "unicode_block_latin_extended_additional",
            "unicode_block_greek_extended",
            "unicode_block_general_punctuation",
            "unicode_block_superscripts_and_subscripts",
            "unicode_block_currency_symbols",
            "unicode_block_combining_diacritical_marks_for_symbols",
            "unicode_block_letterlike_symbols",
            "unicode_block_number_forms",
            "unicode_block_arrows",
            "unicode_block_mathematical_operators",
            "unicode_block_miscellaneous_technical",
            "unicode_block_control_pictures",
            "unicode_block_optical_character_recognition",
            "unicode_block_enclosed_alphanumerics",
            "unicode_block_box_drawing",
            "unicode_block_block_elements",
            "unicode_block_geometric_shapes",
            "unicode_block_miscellaneous_symbols",
            "unicode_block_dingbats",
            "unicode_block_miscellaneous_mathematical_symbols_a",
            "unicode_block_supplemental_arrows_a",
            "unicode_block_braille_patterns",
            "unicode_block_supplemental_arrows_b",
            "unicode_block_miscellaneous_mathematical_symbols_b",
            "unicode_block_supplemental_mathematical_operators",
            "unicode_block_miscellaneous_symbols_and_arrows",
            "unicode_block_glagolitic",
            "unicode_block_latin_extended_c",
            "unicode_block_coptic",
            "unicode_block_georgian_supplement",
            "unicode_block_tifinagh",
            "unicode_block_ethiopic_extended",
            "unicode_block_cyrillic_extended_a",
            "unicode_block_supplemental_punctuation",
            "unicode_block_cjk_radicals_supplement",
            "unicode_block_kangxi_radicals",
            "unicode_block_ideographic_description_characters",
            "unicode_block_cjk_symbols_and_punctuation",
            "unicode_block_hiragana",
            "unicode_block_katakana",
            "unicode_block_bopomofo",
            "unicode_block_hangul_compatibility_jamo",
            "unicode_block_kanbun",
            "unicode_block_bopomofo_extended",
            "unicode_block_cjk_strokes",
            "unicode_block_katakana_phonetic_extensions",
            "unicode_block_enclosed_cjk_letters_and_months",
            "unicode_block_cjk_compatibility",
            "unicode_block_cjk_unified_ideographs_extension_a",
            "unicode_block_yijing_hexagram_symbols",
            "unicode_block_cjk_unified_ideographs",
            "unicode_block_yi_syllables",
            "unicode_block_yi_radicals",
            "unicode_block_lisu",
            "unicode_block_vai",
            "unicode_block_cyrillic_extended_b",
            "unicode_block_bamum",
            "unicode_block_modifier_tone_letters",
            "unicode_block_latin_extended_d",
            "unicode_block_syloti_nagri",
            "unicode_block_common_indic_number_forms",
            "unicode_block_phags_pa",
            "unicode_block_saurashtra",
            "unicode_block_devanagari_extended",
            "unicode_block_kayah_li",
            "unicode_block_rejang",
            "unicode_block_hangul_jamo_extended_a",
            "unicode_block_javanese",
            "unicode_block_myanmar_extended_b",
            "unicode_block_cham",
            "unicode_block_myanmar_extended_a",
            "unicode_block_tai_viet",
            "unicode_block_meetei_mayek_extensions",
            "unicode_block_ethiopic_extended_a",
            "unicode_block_latin_extended_e",
            "unicode_block_cherokee_supplement",
            "unicode_block_meetei_mayek",
            "unicode_block_hangul_syllables",
            "unicode_block_hangul_jamo_extended_b",
            "unicode_block_high_surrogates",
            "unicode_block_high_private_use_surrogates",
            "unicode_block_low_surrogates",
            "unicode_block_private_use_area",
            "unicode_block_cjk_compatibility_ideographs",
            "unicode_block_alphabetic_presentation_forms",
            "unicode_block_arabic_presentation_forms_a",
            "unicode_block_variation_selectors",
            "unicode_block_vertical_forms",
            "unicode_block_combining_half_marks",
            "unicode_block_cjk_compatibility_forms",
            "unicode_block_small_form_variants",
            "unicode_block_arabic_presentation_forms_b",
            "unicode_block_halfwidth_and_fullwidth_forms",
            "unicode_block_specials",
            "unicode_block_linear_b_syllabary",
            "unicode_block_linear_b_ideograms",
            "unicode_block_aegean_numbers",
            "unicode_block_ancient_greek_numbers",
            "unicode_block_ancient_symbols",
            "unicode_block_phaistos_disc",
            "unicode_block_lycian",
            "unicode_block_carian",
            "unicode_block_coptic_epact_numbers",
            "unicode_block_old_italic",
            "unicode_block_gothic",
            "unicode_block_old_permic",
            "unicode_block_ugaritic",
            "unicode_block_old_persian",
            "unicode_block_deseret",
            "unicode_block_shavian",
            "unicode_block_osmanya",
            "unicode_block_elbasan",
            "unicode_block_caucasian_albanian",
            "unicode_block_linear_a",
            "unicode_block_cypriot_syllabary",
            "unicode_block_imperial_aramaic",
            "unicode_block_palmyrene",
            "unicode_block_nabataean",
            "unicode_block_hatran",
            "unicode_block_phoenician",
            "unicode_block_lydian",
            "unicode_block_meroitic_hieroglyphs",
            "unicode_block_meroitic_cursive",
            "unicode_block_kharoshthi",
            "unicode_block_old_south_arabian",
            "unicode_block_old_north_arabian",
            "unicode_block_manichaean",
            "unicode_block_avestan",
            "unicode_block_inscriptional_parthian",
            "unicode_block_inscriptional_pahlavi",
            "unicode_block_psalter_pahlavi",
            "unicode_block_old_turkic",
            "unicode_block_old_hungarian",
            "unicode_block_rumi_numeral_symbols",
            "unicode_block_brahmi",
            "unicode_block_kaithi",
            "unicode_block_sora_sompeng",
            "unicode_block_chakma",
            "unicode_block_mahajani",
            "unicode_block_sharada",
            "unicode_block_sinhala_archaic_numbers",
            "unicode_block_khojki",
            "unicode_block_multani",
            "unicode_block_khudawadi",
            "unicode_block_grantha",
            "unicode_block_tirhuta",
            "unicode_block_siddham",
            "unicode_block_modi",
            "unicode_block_takri",
            "unicode_block_ahom",
            "unicode_block_warang_citi",
            "unicode_block_pau_cin_hau",
            "unicode_block_cuneiform",
            "unicode_block_cuneiform_numbers_and_punctuation",
            "unicode_block_early_dynastic_cuneiform",
            "unicode_block_egyptian_hieroglyphs",
            "unicode_block_anatolian_hieroglyphs",
            "unicode_block_bamum_supplement",
            "unicode_block_mro",
            "unicode_block_bassa_vah",
            "unicode_block_pahawh_hmong",
            "unicode_block_miao",
            "unicode_block_kana_supplement",
            "unicode_block_duployan",
            "unicode_block_shorthand_format_controls",
            "unicode_block_byzantine_musical_symbols",
            "unicode_block_musical_symbols",
            "unicode_block_ancient_greek_musical_notation",
            "unicode_block_tai_xuan_jing_symbols",
            "unicode_block_counting_rod_numerals",
            "unicode_block_mathematical_alphanumeric_symbols",
            "unicode_block_sutton_signwriting",
            "unicode_block_mende_kikakui",
            "unicode_block_arabic_mathematical_alphabetic_symbols",
            "unicode_block_mahjong_tiles",
            "unicode_block_domino_tiles",
            "unicode_block_playing_cards",
            "unicode_block_enclosed_alphanumeric_supplement",
            "unicode_block_enclosed_ideographic_supplement",
            "unicode_block_miscellaneous_symbols_and_pictographs",
            "unicode_block_emoticons",
            "unicode_block_ornamental_dingbats",
            "unicode_block_transport_and_map_symbols",
            "unicode_block_alchemical_symbols",
            "unicode_block_geometric_shapes_extended",
            "unicode_block_supplemental_arrows_c",
            "unicode_block_supplemental_symbols_and_pictographs",
            "unicode_block_cjk_unified_ideographs_extension_b",
            "unicode_block_cjk_unified_ideographs_extension_c",
            "unicode_block_cjk_unified_ideographs_extension_d",
            "unicode_block_cjk_unified_ideographs_extension_e",
            "unicode_block_cjk_compatibility_ideographs_supplement",
            "unicode_block_tags",
            "unicode_block_variation_selectors_supplement",
            "unicode_block_supplementary_private_use_area_a",
            "unicode_block_supplementary_private_use_area_b"
        };

        private static Preference.OnPreferenceChangeListener sUpdateAllListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object value) {
                Boolean newValue = (Boolean)value;
                PreferenceManager pm = preference.getPreferenceManager();
                for(String s: PrefKeys) {
                    TwoStatePreference p = (TwoStatePreference) pm.findPreference(s);
                    p.setChecked(newValue);
                }

                return true;
            }
        };
        private static void bindAllBlockPreference(Preference preference) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(sUpdateAllListener);

            // Trigger the listener immediately with the preference's
            // current value.
            sUpdateAllListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getBoolean(preference.getKey(), false));
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindAllBlockPreference(findPreference("unicode_block_all"));
            //bindPreferenceSummaryToValue(findPreference("unicode_block_basic_latin"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
