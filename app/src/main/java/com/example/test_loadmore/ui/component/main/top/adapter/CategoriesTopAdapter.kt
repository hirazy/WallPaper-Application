package com.example.test_loadmore.ui.component.main.top.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.test_loadmore.R
import com.example.test_loadmore.URL_IMAGE
import com.example.test_loadmore.data.dto.categories.top.CategoryTop
import com.example.test_loadmore.data.dto.image.Image
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.math.roundToInt

class CategoriesTopAdapter(
    private val list: List<CategoryTop>,
    var context: Context,
    var listener: (name: String) -> Unit
) : PagerAdapter() {

    override fun isViewFromObject(v: View, `object`: Any): Boolean {
        return v === `object` as View
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_top_image, container, false)


        var imageView = view.findViewById<ImageView>(R.id.imgTopCategory)

        Picasso.get().load(list[position].thumb).fit().into(imageView,  object: com.squareup.picasso.Callback{
            override fun onSuccess() {
                // scaleImage(imageView)
            }

            override fun onError(e: Exception?) {

            }

        })

        imageView.setOnClickListener {
            listener(list[position].name)
        }

        Log.e("instantiateItem",  list[position].thumb)

        container.addView(view)
        return view
    }


    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        parent.removeView(`object` as View)
    }

    @Throws(NoSuchElementException::class)
    private fun scaleImage(view: ImageView) {
        var bitmap: Bitmap? = null
        bitmap = try {
            val drawing: Drawable = view.drawable
            (drawing as BitmapDrawable).bitmap
        } catch (e: NullPointerException) {
            throw NoSuchElementException("No drawable on given view")
        }
        var width = 0
        width = try {
            bitmap!!.width
        } catch (e: NullPointerException) {
            throw NoSuchElementException("Can't find bitmap on given view/drawable")
        }
        var height = bitmap.height
        val bounding = dpToPx(250)
        val xScale = bounding.toFloat() / width
        val yScale = bounding.toFloat() / height
        val scale = if (xScale <= yScale) xScale else yScale
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        val scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        width = scaledBitmap.width // re-use
        height = scaledBitmap.height // re-use
        val result = BitmapDrawable(scaledBitmap)
        view.setImageDrawable(result)
        val params = view.layoutParams as RelativeLayout.LayoutParams
        params.width = width
        params.height = height
        view.layoutParams = params
    }

    private fun dpToPx(dp: Int): Int {
        val density: Float = context.applicationContext.resources
            .displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

}