package com.example.myapplication1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapterExample(private val context: Context) : SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {
    private var mCount = 0
    fun setCount(count: Int) {
        mCount = count
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }
    abstract class ViewHolder(internal val itemView: View)

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {

        viewHolder!!.itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT).show() })
        when (position) {
            0 -> {

                viewHolder.textViewDescription.textSize = 16f
                viewHolder.textViewDescription.setTextColor(Color.WHITE)
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.win_point)
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            1 -> {

                viewHolder.textViewDescription.textSize = 16f
                viewHolder.textViewDescription.setTextColor(Color.WHITE)
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.year_end_sale)
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            else -> {

                viewHolder.textViewDescription.textSize = 16f
                viewHolder.textViewDescription.setTextColor(Color.WHITE)
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(viewHolder.itemView)
                    .load(R.drawable.christmas_special)
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
        }
    }

    override fun getCount(): Int { //slider view count could be dynamic size
        return mCount
    }

    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var itemView: View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            this.itemView = itemView
        }
    }


}