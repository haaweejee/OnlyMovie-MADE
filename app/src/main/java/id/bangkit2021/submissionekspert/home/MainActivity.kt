package id.bangkit2021.submissionekspert.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit2021.submissionekspert.core.data.Resource
import id.bangkit2021.submissionekspert.core.domain.model.Movie
import id.bangkit2021.submissionekspert.core.ui.ListMovieAdapter
import id.bangkit2021.submissionekspert.core.ui.OnItemClickMovieCallback
import id.bangkit2021.submissionekspert.databinding.ActivityMainBinding
import id.bangkit2021.submissionekspert.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: ListMovieAdapter
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Adapter
        movieAdapter = ListMovieAdapter()
        movieAdapter.notifyDataSetChanged()
        movieAdapter.setOnItemClick(object : OnItemClickMovieCallback {
            override fun onItemClickedMovie(movie: Movie) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_ID, movie)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvCard.layoutManager = LinearLayoutManager(this@MainActivity)
            rvCard.adapter = movieAdapter
            rvCard.setHasFixedSize(true)

            btnFavorite.setOnClickListener {
                val uri = Uri.parse("submissionekspert://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }



        movieViewModel.movie.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        it.data?.let { list -> movieAdapter.setMovies(list) }
                        movieAdapter.notifyDataSetChanged()
                        binding.rvCard.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}