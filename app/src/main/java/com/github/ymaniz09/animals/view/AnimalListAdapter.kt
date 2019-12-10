package com.github.ymaniz09.animals.view

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.github.ymaniz09.animals.R
import com.github.ymaniz09.animals.databinding.ItemAnimalBinding
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.util.bind

class AnimalListAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>(), AnimalClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflatedView = parent.bind<ItemAnimalBinding>(R.layout.item_animal, false)
        return AnimalViewHolder(inflatedView)
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animal = animalList[position]
        holder.view.listener = this
    }

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    class AnimalViewHolder(var view: ItemAnimalBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        val animal = animalList.first { animal -> animal.uuid == v.tag }
        val action = ListAnimalsFragmentDirections.actionGoToDetails(animal)
        Navigation.findNavController(v).navigate(action)
    }
}