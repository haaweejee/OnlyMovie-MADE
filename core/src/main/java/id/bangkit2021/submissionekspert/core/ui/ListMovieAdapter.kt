package id.bangkit2021.submissionekspert.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.bangkit2021.submissionekspert.core.R
import id.bangkit2021.submissionekspert.core.databinding.ItemCardListBinding
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.utils.Constanta

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    private val listMovies = ArrayList<Movie>()
    private var onItemClickMovieCallback: OnItemClickMovieCallback? = null

    fun setOnItemClick(onItemClickMovieCallback: OnItemClickMovieCallback) {
        this.onItemClickMovieCallback = onItemClickMovieCallback
    }

    fun setMovies(movieEntities: List<Movie>) {
        this.listMovies.clear()
        this.listMovies.addAll(movieEntities)
        notifyDataSetChanged()
        Log.d("", listMovies.toString())
    }

    inner class ViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(movieEntity: Movie) {
            with(binding) {
                tvCardName.text = movieEntity.original_title
                Glide.with(itemView.context)
                    .load(Constanta.POSTER_PATH + movieEntity.poster_path)
                    .transform(RoundedCorners(10))
                    .placeholder(R.drawable.loading)
                    .into(imageView)
            }
            binding.root.setOnClickListener {
                onItemClickMovieCallback?.onItemClickedMovie(movieEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListBinding =
            ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bindMovie(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}

interface OnItemClickMovieCallback {
    fun onItemClickedMovie(movie: Movie)
}
