package com.example.test_loadmore.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.utils.LayoutId


class BaseHolder<T : ViewDataBinding>(var itemBinding: T) :
    RecyclerView.ViewHolder(itemBinding.root)


abstract class AdapterBase<K : OBase, T : ViewDataBinding>(var event: RecyclerItemListener) :
    RecyclerView.Adapter<BaseHolder<T>>() {
    protected val listData = arrayListOf<K>()


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

    fun setData(it: List<K>) {
        listData.clear()
        listData.addAll(it)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  listData.size
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
            bindView(holder, position);
            holder.itemBinding.root.setOnClickListener {
                event?.onItemSelected(position, listData[position])
            }
            holder.itemBinding.root.setOnLongClickListener() {
                event?.onItemSelected(position, listData[position])
                true
            }
    }

    abstract fun bindView(itemBinding: BaseHolder<T>, position: Int)


}