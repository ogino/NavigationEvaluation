package io.miyabi.navigate.ui.preference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import io.miyabi.navigate.R

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
    }
}