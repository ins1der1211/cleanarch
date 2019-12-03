package ins1der.cleanarch.presentation.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseFragment
import ins1der.cleanarch.presentation.ui.extensions.toast
import kotlinx.android.synthetic.main.fragment_people.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import timber.log.Timber

class PeopleFragment: BaseFragment() {

    private val secondViewModel: SecondViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_people, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$secondViewModel")

        val adapter = PeopleAdapter()
        people_rv.layoutManager = LinearLayoutManager(view.context)
        people_rv.adapter = adapter
        secondViewModel.peopleList.observe(this, Observer {
            adapter.submitList(it)
        })
        secondViewModel.viewState.observe(this, Observer {
            when (it) {
                is Error -> activity?.toast(it.txt ?: "")
            }
        })
        secondViewModel.networkState.observe(this, Observer {  })
        secondViewModel.getPeople()
//        view.setOnClickListener { findNavController().navigate(R.id.action_peopleFragment_to_testFragment) }
    }
}