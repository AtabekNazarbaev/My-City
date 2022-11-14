package kr.texnopos.mycity.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.texnopos.mycity.data.local.Dao
import kr.texnopos.mycity.data.local.ListDatabase
import kr.texnopos.mycity.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var dao :Dao
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            dao =ListDatabase.getInstance(requireContext()).listDao()
            setData()
            adapter.onItemClick = {
                val action = MainFragmentDirections.actionMainFragmentToListFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    private fun setData() {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.model = withContext(Dispatchers.IO) {
                dao.getCategory()
            }
        }
    }
}
