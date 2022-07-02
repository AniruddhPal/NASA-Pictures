package com.obiviousdemo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem
import org.apache.commons.lang3.StringUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: ArrayList<NasaPicModelItem>? = null
    private var param2: Int = 0

    private lateinit var imageView: ImageView
    private lateinit var textTitle: TextView
    private lateinit var textCopyRight: TextView
    private lateinit var textDate: TextView
    private lateinit var textExplaination: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as ArrayList<NasaPicModelItem>
            param2 = it.getInt(ARG_PARAM2)
        }

        Log.v("DataFragment", "ArrayList ::: " + param1.toString())
        Log.v("DataFragment", "ArrayList size::: " + param1?.size.toString())
        Log.v("DataFragment", "ArrayList position::: " + param2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_data, container, false)
        initRef(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<NasaPicModelItem>, param2: Int) =
            DataFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRef(activity: View) {
        imageView = activity?.findViewById(R.id.image) as ImageView
        textTitle = activity?.findViewById(R.id.title) as TextView
        textCopyRight = activity?.findViewById(R.id.copyright) as TextView
        textDate = activity?.findViewById(R.id.date) as TextView
        textExplaination = activity?.findViewById(R.id.explaination) as TextView

        setValue(param2)
    }

    private fun setValue(param2: Int) {
        val item: NasaPicModelItem = param1!!.get(param2)
        Log.v("DataFragment", "Object Aat ::: " + param2 + " is ::: " + item.title)
        context?.let {
            Glide.with(it)
                .load(item?.hdurl)
                .into(imageView)
                .onLoadStarted(context?.getDrawable(R.drawable.ic_placeholder_24))
        }


        if (!StringUtils.isEmpty(item.copyright)) {
            textCopyRight.text = item.copyright
        }

        if (!StringUtils.isEmpty(item.title)) {
            textTitle.text = item.title
        }

        if (!StringUtils.isEmpty(item.date)) {
            textDate.text = "Date : " + item.date
        }

        if (!StringUtils.isEmpty(item.explanation)) {
            textExplaination.text = "Explaination : " + item.explanation
        }
    }
}