package id.bangkit2021.submissionekspert.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.ui.ListMovieAdapter
import id.bangkit2021.submissionekspert.core.ui.OnItemClickMovieCallback
import id.bangkit2021.submissionekspert.detail.DetailActivity
import id.bangkit2021.submissionekspert.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: ListMovieAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(FavoriteModule().favoriteModule)

        favoriteAdapter = ListMovieAdapter()
        favoriteAdapter.notifyDataSetChanged()
        favoriteAdapter.setOnItemClick(object : OnItemClickMovieCallback {
            override fun onItemClickedMovie(movie: Movie) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_ID, movie)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvCard.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvCard.adapter = favoriteAdapter
            rvCard.setHasFixedSize(true)
        }

        favoriteViewModel.movieFavorite.observe(this, {
            favoriteAdapter.setMovies(it)

        })
    }
}