package com.tda.ginilibrary.ui

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import com.tda.ginilibrary.CatImageLib.appContainer
import com.tda.ginilibrary.CatImageListener
import com.tda.ginilibrary.R
import com.tda.ginilibrary.core.getColorFromAttr
import com.tda.ginilibrary.core.getViewModel
import com.tda.ginilibrary.core.observe
import com.tda.ginilibrary.data.mapper.CatImage
import com.tda.ginilibrary.data.model.Failure
import kotlinx.android.synthetic.main.fragment_all_cats.*

internal class AllCatsFragment : Fragment() {

    private lateinit var viewModel: AllCatsViewModel
    private val adapter = CatsListAdapter(::onImageClick)
    private var catImageListener: CatImageListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButtonSrc = arguments?.getInt(BACK_BUTTON_EXTRA)

        viewModel = getViewModel(appContainer.allCatsViewModelFactory) {
            observe(pagingList, ::onLoadSuccess)
            observe(getLoadingState(), ::isLoading)
        }

        val myActivity = activity as AppCompatActivity?
        myActivity!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        myActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        myActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (backButtonSrc != android.R.id.home)
            myActivity.supportActionBar?.setHomeAsUpIndicator(backButtonSrc!!)
        toolbar.title = arguments?.getString(APP_BAR_TITLE_EXTRA)
        catRecycler.adapter = adapter

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activity?.setTheme(arguments?.getInt(BACKGROUND_COLOR_EXTRA)!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity?.window?.statusBarColor =
                    requireActivity().getColorFromAttr(R.attr.colorPrimaryVariant)
        }
        return inflater.inflate(R.layout.fragment_all_cats, container, false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onLoadSuccess(imageList: PagedList<CatImage>?) {
        adapter.submitList(imageList)
    }

    private fun onImageClick(catImage: CatImage) {
        catImageListener?.setImageUrl(catImage.url)
        requireActivity().onBackPressed()
    }

    private fun isLoading(isLoading: LoadingState?) {
        when (isLoading) {
            LoadingState.LOADING -> {

            }
            LoadingState.DONE -> {
                mainProgressBar.visibility = GONE
            }
            is LoadingState.ERROR -> {
                onLoadFailure(isLoading.failure)
            }
        }
    }

    private fun onLoadFailure(failure: Failure?) {
        when (failure) {
            Failure.NetworkConnection -> {
            }
            is Failure.ServerError -> {
            }
            Failure.FeatureFailure -> {
            }
            null -> {
            }
        }
    }

    fun setListener(catImageListener: CatImageListener) {
        this.catImageListener = catImageListener
    }

    companion object {

        const val APP_BAR_TITLE_EXTRA: String = "APP_BAR_TITLE_EXTRA"
        const val BACKGROUND_COLOR_EXTRA: String = "BACKGROUND_COLOR_EXTRA"
        const val BACK_BUTTON_EXTRA: String = "BACK_BUTTON_EXTRA"

        var TAG = AllCatsFragment::javaClass.name

        fun newInstance(title: String, color: Int, backButtonSrc: Int): AllCatsFragment {
            return AllCatsFragment().apply {
                arguments = Bundle().apply {
                    putString(APP_BAR_TITLE_EXTRA, title)
                    putInt(BACKGROUND_COLOR_EXTRA, color)
                    putInt(BACK_BUTTON_EXTRA, backButtonSrc)
                }
            }
        }
    }
}