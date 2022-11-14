package kr.texnopos.mycity.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.texnopos.mycity.data.local.Dao
import kr.texnopos.mycity.data.local.ListDatabase
import kr.texnopos.mycity.data.local.MyDatabase
import kr.texnopos.mycity.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = ListAdapter()
    private val args: ListFragmentArgs by navArgs()
    private lateinit var dao: Dao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dao = ListDatabase.getInstance(requireContext()).listDao()
            when (args.id) {
                1 -> toolbar.title = "Университет"
                2 -> toolbar.title = "Клиника"
                3 -> toolbar.title = "Банк"
                4 -> toolbar.title = "Ресторан"
                5 -> toolbar.title = "Кафе"
            }
            setData()
            recyclerView.adapter = adapter
            val itemDecoration =
                DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(itemDecoration)
        }
    adapter.onItemClick = { id,url ->
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(id,url)
        findNavController().navigate(action)
    }
    }

    private fun setData() {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.model = withContext(Dispatchers.IO) {
                dao.getListById(args.id)
            }
        }
    }
}
