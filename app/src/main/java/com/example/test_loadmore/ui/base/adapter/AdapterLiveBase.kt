package com.example.test_loadmore.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.utils.LayoutId

class BaseDiffCallBack<K : OBase> : DiffUtil.ItemCallback<K>() {
    override fun areItemsTheSame(oldItem: K, newItem: K): Boolean {

        return true
    }

    override fun areContentsTheSame(oldItem: K, newItem: K): Boolean {
        return oldItem.equals(newItem)
    }
}

class BaseHolder1<T : ViewDataBinding>(var itemBinding: T) :
    RecyclerView.ViewHolder(itemBinding.root)

abstract class AdapterLiveBase<K : OBase, T : ViewDataBinding>(
    var event: RecyclerItemListener
) :
    ListAdapter<K, BaseHolder<T>>(BaseDiffCallBack<K>()) {
    companion object {
        const val CLICK_ITEM = "CLICK_ITEM"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {

        return BaseHolder(
            DataBindingUtil.inflate<T>
                (
                LayoutInflater.from(parent.context),
                javaClass.getAnnotation(LayoutId::class.java).value,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        var item = getItem(position)
        bindView(holder, position);
        holder.itemBinding.root.setOnClickListener {
            event.onItemSelected(position, item)
        }
        holder.itemBinding.root.setOnLongClickListener() {

            true
        }

    }

    abstract fun bindView(itemBinding: BaseHolder<T>, position: Int)

}
