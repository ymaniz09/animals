package com.github.ymaniz09.animals.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.github.ymaniz09.animals.R
import kotlinx.android.synthetic.main.fragment_list_animals.*

class ListAnimalsFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsFloatingActionButton.setOnClickListener {
            val action = ListAnimalsFragmentDirections.actionGoToDetails()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_animals, container, false)
    }


}
