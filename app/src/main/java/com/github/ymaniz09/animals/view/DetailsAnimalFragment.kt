package com.github.ymaniz09.animals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.ymaniz09.animals.R
import com.github.ymaniz09.animals.databinding.FragmentDetailsAnimalBinding
import com.github.ymaniz09.animals.model.Animal


class DetailsAnimalFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding: FragmentDetailsAnimalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil
                .inflate(inflater, R.layout.fragment_details_animal, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailsAnimalFragmentArgs.fromBundle(it).animal
        }

        if (animal != null) {
            dataBinding.animal = animal
        }
    }
}
