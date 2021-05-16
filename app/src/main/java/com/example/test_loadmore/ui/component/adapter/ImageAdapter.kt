package com.example.test_loadmore.ui.component.adapter

import android.util.Log
import com.example.test_loadmore.*
import com.example.test_loadmore.base.adapter.AdapterBase
import com.example.test_loadmore.base.adapter.BaseHolder
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.CardItemWppBinding
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.utils.LayoutId
import com.squareup.picasso.Picasso
import java.lang.Exception

@LayoutId(R.layout.card_item_wpp)
class ImageAdapter(event: RecyclerItemListener) : AdapterBase<Image, CardItemWppBinding>(event) {
    override fun bindView(itemBinding: BaseHolder<CardItemWppBinding>, position: Int) {

        itemBinding.itemBinding.o = listData[position]

        //if(listData[position].type == TYPE_IMAGE){

        Picasso.get().load(BASE_URL + "image/" + listData[position].id.toString() + ".jpg").fit()
            .into(itemBinding.itemBinding.imgWpp, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                }
            })
        //}
        //else if(listData[position].type == TYPE_4D){

        //}
        //else if(listData[position].type == TYPE_4K){

        //}


    }
}