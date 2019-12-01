package ins1der.cleanarch.presentation.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseFragment
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
        view.setOnClickListener { findNavController().navigate(R.id.action_peopleFragment_to_testFragment) }
    }
}