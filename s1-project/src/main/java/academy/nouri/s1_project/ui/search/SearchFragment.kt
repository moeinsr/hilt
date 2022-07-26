package academy.nouri.s1_project.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.s1_project.R
import academy.nouri.s1_project.databinding.FragmentSearchBinding
import academy.nouri.s1_project.ui.home.HomeFragmentDirections
import academy.nouri.s1_project.ui.home.adapters.LastMoviesAdapter
import academy.nouri.s1_project.utils.initRecycler
import academy.nouri.s1_project.utils.showInvisible
import academy.nouri.s1_project.viewmodel.SearchViewModel
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchAdapter: LastMoviesAdapter

    //Other
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Search
            searchEdt.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    viewModel.loadSearchMovies(search)
                }
            }
            //Get movies list
            viewModel.moviesList.observe(viewLifecycleOwner) {
                searchAdapter.setData(it.data)
                moviesRecycler.initRecycler(LinearLayoutManager(requireContext()), searchAdapter)
            }
            //Click
            searchAdapter.setOnItemClickListener {
                val direction = SearchFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    searchLoading.showInvisible(true)
                } else {
                    searchLoading.showInvisible(false)
                }
            }
            //Empty items
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemsLay.showInvisible(true)
                    moviesRecycler.showInvisible(false)
                } else {
                    emptyItemsLay.showInvisible(false)
                    moviesRecycler.showInvisible(true)
                }
            }
        }
    }
}