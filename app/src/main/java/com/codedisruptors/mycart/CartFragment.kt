package com.codedisruptors.mycart

import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cart.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CartFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    companion object {
        val TAG: String = CartFragment::class.java.simpleName
        fun newInstance() = CartFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater!!.inflate(R.layout.fragment_cart, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}// Required empty public constructor
