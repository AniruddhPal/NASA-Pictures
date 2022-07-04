package com.obiviousdemo.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem
import org.apache.commons.lang3.StringUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val LIST_FROM_BUNDLE = "list_from_bundle"
private const val POSITION_FROM_BUNDLE = "position_from_bundle"

class DataFragment : Fragment() {
    private var picList: ArrayList<NasaPicModelItem>? = null
    private var listItemPosition: Int = 0

    private lateinit var imageView: ImageView
    private lateinit var textTitle: TextView
    private lateinit var textCopyRight: TextView
    private lateinit var textDate: TextView
    private lateinit var textExplaination: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_data, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            picList = it.getSerializable(LIST_FROM_BUNDLE) as ArrayList<NasaPicModelItem>
            listItemPosition = it.getInt(POSITION_FROM_BUNDLE)
        }
        initRef(view)
    }

    companion object {
        @JvmStatic
        fun newInstance(nasaPicList: ArrayList<NasaPicModelItem>, position: Int) =
            DataFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LIST_FROM_BUNDLE, nasaPicList)
                    putInt(POSITION_FROM_BUNDLE, position)
                }
            }
    }

    private fun initRef(activity: View) {
        imageView = activity?.findViewById(R.id.image) as ImageView
        textTitle = activity?.findViewById(R.id.title) as TextView
        textCopyRight = activity?.findViewById(R.id.copyright) as TextView
        textDate = activity?.findViewById(R.id.date) as TextView
        textExplaination = activity?.findViewById(R.id.explaination) as TextView

        setValue(listItemPosition)
    }

    private fun setValue(position: Int) {
        val item: NasaPicModelItem? = picList?.get(position)
        Glide.with(this)
            .asBitmap()
            .load(item?.hdurl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })


        if (!StringUtils.isEmpty(item?.copyright)) {
            textCopyRight.text =
                context?.resources?.getString(R.string.copyright_symbol) + " " + item?.copyright
        }

        if (!StringUtils.isEmpty(item?.title)) {
            textTitle.text = item?.title
        }

        if (!StringUtils.isEmpty(item?.date)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                textDate.text = "Date : " + LocalDate.parse(item?.date)
                    .format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
            } else {
                textDate.text = "Date : " + item?.date
            }
        }

        if (!StringUtils.isEmpty(item?.explanation)) {
            textExplaination.text = "Explaination : " + item?.explanation
        }
    }

}