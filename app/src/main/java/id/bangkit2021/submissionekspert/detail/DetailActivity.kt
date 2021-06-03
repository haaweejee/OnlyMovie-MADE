package id.bangkit2021.submissionekspert.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.bangkit2021.submissionekspert.R
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.utils.Constanta
import id.bangkit2021.submissionekspert.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "id"
    }

    private lateinit var binding : ActivityDetailBinding
    private val detailViewModel : DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_ID)
        loadDetailMovie(detailMovie)
    }

    private fun loadDetailMovie(movie: Movie?){
        if (movie != null){
            binding.itemDetailName.text = movie.original_title
            supportActionBar?.title = movie.original_title
            binding.itemDetailOverview.text = movie.overview
            binding.itemDetailDate.text = movie.release_date

            //Poster_path
            Glide.with(this)
                .load(Constanta.POSTER_PATH + movie.poster_path)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .transform(RoundedCorners(10))
                .into(binding.imgPoster)

            //Backdrop
            Glide.with(this)
                .load(Constanta.BACKDROP_PATH + movie.backdrop_path)
                .transform(RoundedCorners(10))
                .fitCenter()
                .placeholder(R.drawable.loading)
                .into(binding.imgBackdrop)

            var statusFavorite = movie.favorite
            setFavoriteState(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                setFavoriteState(statusFavorite)
            }
        }
    }

    override fun onSupportNavigateUp():Boolean{
        onBackPressed()
        return true
    }



    private fun setFavoriteState(state: Boolean){
        if (state){
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorited))
        }else{
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        }
    }
}