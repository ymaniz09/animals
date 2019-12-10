package com.github.ymaniz09.animals.view

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.github.ymaniz09.animals.R
import com.github.ymaniz09.animals.util.inflate
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.util.getProgressDrawable
import com.github.ymaniz09.animals.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflatedView = parent.inflate(R.layout.item_animal, false)
        return AnimalViewHolder(inflatedView)
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalName.text = animalList[position].name
        holder.view.animalImage.loadImage(animalList[position].imageUrl,
            getProgressDrawable(holder.view.context))
        holder.view.animalLayout.setOnClickListener {
            val action = ListAnimalsFragmentDirections.actionGoToDetails(animalList[position])
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    class AnimalViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}