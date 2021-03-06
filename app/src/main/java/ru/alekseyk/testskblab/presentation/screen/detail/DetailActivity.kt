package ru.alekseyk.testskblab.presentation.screen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity
import ru.alekseyk.testskblab.presentation.ext.setNavigationOnClickListener
import ru.alekseyk.testskblab.presentation.models.RepositoryModel

class DetailActivity : StateActivity<DetailViewState>(
    layoutResource = R.layout.activity_detail
) {

    override val viewModel by viewModel<DetailViewModel>()
    private val repositoryModel: RepositoryModel by lazy {
        intent!!.getSerializableExtra(
            EXTRA_REPOSITORY_MODEL
        ) as RepositoryModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setStatus(repositoryModel)
    }

    override fun initListeners() {
        general_toolbar?.setNavigationOnClickListener(::finish)
        add_favorites_btn.setOnClickListener {
            viewModel.updateFavoriteStatus(repositoryModel)
        }
    }

    override fun render(state: DetailViewState) {
        general_progressbar.isVisible = state.isLoading
        add_favorites_btn.text =
            if (state.isFavorite) getString(R.string.detail_btn_delete_fav_btn)
            else getString(R.string.detail_btn_add_fav_btn)
    }

    override fun initViews() {
        general_toolbar.title = repositoryModel.name
        Glide.with(this).load(repositoryModel.ownerAvatarUrl).into(detail_owner_image_v)
        detail_owner_tv.text = repositoryModel.ownerLogin
        detail_description_tv.text = repositoryModel.description
        detail_fork_count_tv.text = repositoryModel.forksCount.toString()
        detail_star_count_tv.text = repositoryModel.stargazersCount.toString()
        detail_created_at_tv.text = repositoryModel.createdAt

    }

    companion object {
        private const val EXTRA_REPOSITORY_MODEL = "extra_repository_model"
        fun startActivity(context: Context, repositoryModel: RepositoryModel) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(
                    EXTRA_REPOSITORY_MODEL,
                    repositoryModel
                )
            })
        }
    }
}
