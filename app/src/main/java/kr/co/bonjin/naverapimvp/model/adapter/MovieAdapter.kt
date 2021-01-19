package kr.co.bonjin.naverapimvp.model.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kr.co.bonjin.naverapimvp.R
import kr.co.bonjin.naverapimvp.databinding.ActivitySearchBinding
import kr.co.bonjin.naverapimvp.databinding.ItemMovieBinding
import kr.co.bonjin.naverapimvp.model.vo.Item


class MovieAdapter(context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val mContext: Context = context
    private val mMovieInfoArrayList = ArrayList<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bind(mMovieInfoArrayList[position])

//        movieViewHolder.mTvTitle.text = Html.fromHtml(item.title)
//        movieViewHolder.mRbUserRating.rating = item.userRating.toFloat() / 2
//        movieViewHolder.mTvPubData.text = item.pubDate
//        movieViewHolder.mTvDirector.text = Html.fromHtml(item.director)
//        movieViewHolder.mTvActor.text = Html.fromHtml(item.actor)

//        Glide.with(mContext)
//            .load(mMovieInfoArrayList[position].image)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(movieViewHolder)

    }

    override fun getItemCount(): Int {
        return mMovieInfoArrayList.size
    }

    fun clearItems() {
        mMovieInfoArrayList.clear()
        notifyDataSetChanged()
    }

    fun clearAndAddItems(items: ArrayList<Item>) {
        mMovieInfoArrayList.clear()
        mMovieInfoArrayList.addAll(items)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root){

        init {

        }

        fun bind(items: Item) {
            binding.movieItem = items
            binding.executePendingBindings()
        }

    }

}