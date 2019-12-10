package com.github.ymaniz09.animals.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

            setupBackgroundColor(animal?.imageUrl)
        }
    }

    private fun setupBackgroundColor(url: String?) {
        if (url != null) {
            Glide
                .with(this)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Palette.from(resource)
                            .generate {
                                val intColor = it?.lightMutedSwatch?.rgb ?: 0
                                animalScroll.setBackgroundColor(intColor)
                            }
                    }

                })
        }
    }
}
