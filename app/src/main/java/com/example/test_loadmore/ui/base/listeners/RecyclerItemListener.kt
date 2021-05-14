package com.example.test_loadmore.ui.base.listeners

import com.example.test_loadmore.base.OBase


/**
 * Created by AhmedEltaher
 */

interface RecyclerItemListener {
    fun onItemSelected(index: Int, data: OBase)

    fun onOption(index: Int, data: OBase)
}
