package kr.texnopos.mycity.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.texnopos.mycity.R
import kr.texnopos.mycity.data.model.Main
import kr.texnopos.mycity.databinding.MainItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var onItemClick: (id: Int) -> Unit = {}
    var model: List<Main> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun setPopulateModel(data: Main) {
            binding.apply {
                Glide.with(root.context)
                    .load(data.url)
                    .into(imageView)
                textView.text = data.name
                imageView.setOnClickListener {
                    onItemClick.invoke(data.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return ViewHolder(MainItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.setPopulateModel(model[position])
    }

    override fun getItemCount() = model.size
}
