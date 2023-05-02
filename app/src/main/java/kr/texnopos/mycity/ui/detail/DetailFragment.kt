package kr.texnopos.mycity.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.texnopos.mycity.data.local.Dao
import kr.texnopos.mycity.data.local.ListDatabase
import kr.texnopos.mycity.data.model.Detail
import kr.texnopos.mycity.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var dao: Dao
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = ListDatabase.getInstance(requireContext()).listDao()
        setData(args.id)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setData(id: Int) {
        var data: List<Detail> = dao.getInfo(id)
        binding.apply {
            tvAddress.text = "📍 ${data[0].address}"
            tvPhone.text = "📞 ${data[0].phone}"
            tvTime.text = "🕐 ${data[0].time}"
            val id = dao.getInfo(id)
            val picture = "picture${id}"
            Glide.with(root.context)
                .load(root.context.resources.getIdentifier(picture,"drawable", root.context.packageName))
                .into(imageView)
        }
    }
}
