package kr.texnopos.mycity.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.texnopos.mycity.R
import kr.texnopos.mycity.data.model.ListById
import kr.texnopos.mycity.databinding.ListItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var onItemClick: (id: Int, url: String) -> Unit = { _, _ -> }
    var model: List<ListById> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setPopulateModel(data: ListById) {
            binding.apply {
                Glide.with(root.context)
                    .load(data.url)
                    .into(imageView)
                textView.text = data.name
                constraint.setOnClickListener {
                    onItemClick.invoke(data.id, data.url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(ListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        return holder.setPopulateModel(model[position])
    }

    override fun getItemCount() = model.size
}
