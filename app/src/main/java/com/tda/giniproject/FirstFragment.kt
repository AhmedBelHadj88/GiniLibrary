package com.tda.giniproject

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.tda.ginilibrary.CatImageLib
import com.tda.ginilibrary.CatImageLibSettings
import com.tda.ginilibrary.CatImageListener
import com.tda.ginilibrary.core.getColorFromAttr
import com.tda.ginilibrary.core.loadUrl

class FirstFragment : Fragment(), CatImageListener {

    var settings : CatImageLibSettings? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settings = CatImageLibSettings()

        view.findViewById<Button>(R.id.button_openCatList).setOnClickListener {
            CatImageLib.openCatList(requireActivity(), this, settings!!)
        }

        view.findViewById<Button>(R.id.button_setToolBarTitle).setOnClickListener {
           setToolBarTitle(requireContext())
        }

        view.findViewById<Button>(R.id.button_changeAppBarColor).setOnClickListener {
           changeAppBarColor(requireActivity())
        }

        view.findViewById<Button>(R.id.button_changeBackButtonBackground).setOnClickListener {
            changeBackButtonBackground(requireActivity())
        }
    }

    private fun setToolBarTitle(
            context: Context
    ) {
        val mView: View =
                LayoutInflater.from(context).inflate(com.tda.ginilibrary.R.layout.title_input_dialog_box, null)
        val alertDialogBuilderUserInput: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilderUserInput.setView(mView)

        val userInputDialogEditText = mView.findViewById<View>(com.tda.ginilibrary.R.id.titleInputDialog) as EditText
            userInputDialogEditText.setText(settings?.appBarTitle)
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok") { _, _ ->
                    settings?.appBarTitle = userInputDialogEditText.text.toString()
                }
                .setNegativeButton(
                        "Cancel"
                ) { dialogBox, _ -> dialogBox.cancel() }

        val alertDialogAndroid: AlertDialog = alertDialogBuilderUserInput.create()
        alertDialogAndroid.show()
    }

    private fun changeAppBarColor(
            activity: Activity
    ) {
        AlertDialog.Builder(activity).also {
            it.setTitle("Select Color")
                    .setItems(com.tda.ginilibrary.R.array.colorsAlert) { _, pos ->
                        settings?.appBarColor = when (pos) {
                            0 -> {
                                R.style.Theme_Red
                            }
                            1 -> {
                                R.style.Theme_Green
                            }
                            2 -> {
                                R.style.Theme_Brown
                            }
                            else -> {
                                R.style.Theme_Default
                            }
                        }


                    }
            it.show()
        }

    }

    private fun changeBackButtonBackground(
            activity: Activity
    ) {
        AlertDialog.Builder(activity).also {
            it.setTitle("Select Image")
                    .setItems(com.tda.ginilibrary.R.array.backButtonAlert) { _, pos ->
                        settings?.backButtonSRC = when (pos) {
                            0 -> {
                                android.R.id.home
                            }
                            1 -> {
                                android.R.drawable.arrow_up_float
                            }
                            else -> {
                                android.R.id.home
                            }
                        }
                    }
            it.show()
        }

    }

    override fun setImageUrl(url: String) {
        requireView().findViewById<ProgressBar>(R.id.progressBarMain).visibility = VISIBLE
        requireView().findViewById<ImageView>(R.id.selectedCatThumb).loadUrl(url)
    }

    override fun getImageBitmap(bitmap: Bitmap) {
    }

}