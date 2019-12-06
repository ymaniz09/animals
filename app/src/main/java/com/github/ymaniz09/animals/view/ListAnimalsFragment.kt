package com.github.ymaniz09.animals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.ymaniz09.animals.R
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_animals.*

class ListAnimalsFragment : Fragment() {

    private lateinit var viewModel : ListViewModel
    private val listAdapter = AnimalListAdapter(arrayListOf())

    private val animalListObserver = Observer<List<Animal>> {
        it?.let {
            animalList.visibility = View.VISIBLE
            listAdapter.updateAnimalList(it)
        }
    }

    private val errorObserver = Observer<Boolean> {
        listError.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val loadingObserver = Observer<Boolean> {
        loadingView.visibility = if (it) View.VISIBLE else View.GONE

        if (it) {
            listError.visibility = View.GONE
            animalList.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_animals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(viewLifecycleOwner, animalListObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorObserver)

        viewModel.refresh()

        animalList.adapter = listAdapter
    }


}
