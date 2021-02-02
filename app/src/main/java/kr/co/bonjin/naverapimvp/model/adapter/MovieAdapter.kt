package kr.co.bonjin.naverapimvp.model.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kr.co.bonjin.naverapimvp.R
import kr.co.bonjin.naverapimvp.databinding.ItemMovieBinding
import kr.co.bonjin.naverapimvp.model.vo.Item


class MovieAdapter(context: Context, movieList: ArrayList<Item>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val mContext: Context = context
    private val mMovieInfoArrayList = movieList

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
        holder.bind()
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
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.tvTitle.text = Html.fromHtml(mMovieInfoArrayList[position].title)
            binding.rbUserRating.rating = mMovieInfoArrayList[position].userRating.toFloat() / 2
            binding.tvPubData.text = mMovieInfoArrayList[position].pubDate
            binding.tvDirector.text = Html.fromHtml(mMovieInfoArrayList[position].director)
            binding.tvActor.text = Html.fromHtml(mMovieInfoArrayList[position].actor)

            Glide.with(mContext)
                .load(mMovieInfoArrayList[position].image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivPoster)
        }

    }

}