package com.example.test_loadmore.ui.component.main.categories.adapter

import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.R
import com.example.test_loadmore.base.adapter.AdapterBase
import com.example.test_loadmore.base.adapter.BaseHolder
import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.databinding.CardItemCategoryBinding
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.utils.LayoutId
import com.squareup.picasso.Picasso
import java.lang.Exception

@LayoutId(R.layout.card_item_category)
class CategoriesAdapter(event: RecyclerItemListener) :
    AdapterBase<Category, CardItemCategoryBinding>(event) {
    override fun bindView(itemBinding: BaseHolder<CardItemCategoryBinding>, position: Int) {

        itemBinding.itemBinding.o = listData[position]

        Picasso.get().load(BASE_URL + "category/" + listData[position].image1 + ".png")
            .fit()
            .into(itemBinding.itemBinding.imgCategory1)


        Picasso.get().load(BASE_URL + "category/" + listData[position].image2 + ".png")
            .fit()
            .into(itemBinding.itemBinding.imgCategory2)

        Picasso.get().load(BASE_URL + "category/" + listData[position].image3 + ".png")
            .fit()
            .into(itemBinding.itemBinding.imgCategory3)

        Picasso.get().load(BASE_URL + "category/" + listData[position].image4 + ".png")
            .fit()
            .into(itemBinding.itemBinding.imgCategory4)


    }
}