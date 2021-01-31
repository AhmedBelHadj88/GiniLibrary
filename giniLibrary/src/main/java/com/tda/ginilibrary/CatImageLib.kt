package com.tda.ginilibrary

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.tda.ginilibrary.core.AppContainer
import com.tda.ginilibrary.core.getColorFromAttr
import com.tda.ginilibrary.ui.AllCatsFragment

object CatImageLib {

    internal val appContainer = AppContainer()

    fun openCatList(
        activity: Activity,
        catImageListener: CatImageListener,
        settings: CatImageLibSettings
    ) {
        val listActivity = activity as AppCompatActivity
        listActivity.supportFragmentManager.beginTransaction()
            .add(
                android.R.id.content,
                AllCatsFragment.newInstance(settings.appBarTitle, settings.appBarColor, settings.backButtonSRC)
                    .apply { setListener(catImageListener) })
            .addToBackStack(AllCatsFragment.TAG)
            .commit()
    }
}