package com.example.test_loadmore.ui.component.main.top.adapter

import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.R
import com.example.test_loadmore.base.adapter.AdapterBase
import com.example.test_loadmore.base.adapter.BaseHolder
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.CardItemTopYouBinding
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.utils.LayoutId
import com.squareup.picasso.Picasso
import java.lang.Exception

@LayoutId(R.layout.card_item_top_you)
class YouAdapter(event: RecyclerItemListener) : AdapterBase<Image, CardItemTopYouBinding>(event) {
    override fun bindView(itemBinding: BaseHolder<CardItemTopYouBinding>, position: Int) {
        itemBinding.itemBinding.o = listData[position]

        Picasso.get().load(BASE_URL + "image/" + listData[position].id.toString() + ".jpg").fit()
            .into( itemBinding.itemBinding.imgTopYou, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                }

            })
    }
}