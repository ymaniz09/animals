package com.github.ymaniz09.animals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.ymaniz09.animals.R
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.util.getProgressDrawable
import com.github.ymaniz09.animals.util.loadImage
import kotlinx.android.synthetic.main.fragment_details_animal.*


class DetailsAnimalFragment : Fragment() {

    var animal: Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailsAnimalFragmentArgs.fromBundle(it).animal

        }

        context?.let {
            animalImage.loadImage(animal?.imageUrl, getProgressDrawable(it))
            animalName.text = animal?.name
            animalLocation.text = animal?.location
            animalDiet.text = animal?.diet
            animalLocation.text = animal?.location
            animalLifespan.text = animal?.lifeSpan
        }
    }
}
