package ru.alekseyk.testskblab.presentation.screen.repo_list

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_repolist.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.base.StateActivity
import ru.alekseyk.testskblab.presentation.ext.buildAlertDialog
import ru.alekseyk.testskblab.presentation.ext.switchTab
import ru.alekseyk.testskblab.presentation.screen.auth.AuthActivity
import ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo.FavoritesRepoFragment
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.SearchRepoFragment

internal class RepoListActivity : StateActivity<RepoListViewState>(
    layoutResource = R.layout.activity_repolist
) {

    override val viewModel by viewModel<RepoListViewModel>()

    override fun render(state: RepoListViewState) {
        general_progressbar.isVisible = state.isLoading
        if (state.isFinish) {
            AuthActivity.startActivity(this)
            finish()
        }
    }

    override fun initViews() {
        general_toolbar.title = "Github repositories"
        setSupportActionBar(general_toolbar)

        view_pager.adapter = RepoListPagerAdapter(supportFragmentManager, this@RepoListActivity)
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.switchTab {
            view_pager.currentItem = it.position
            if (it.position == 1) {
                (supportFragmentManager.fragments[1] as FavoritesRepoFragment).updateFavoritesList()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_update -> {
                (supportFragmentManager.fragments[0] as SearchRepoFragment).updateSearch()
                (supportFragmentManager.fragments[1] as FavoritesRepoFragment).updateFavoritesList()
                true
            }
            R.id.action_logout -> {
                this.buildAlertDialog(
                    title = "Выйти из аккаунта",
                    onPositiveBtnClick = { viewModel.deleteUserData() }).show()
                true
            }
            else -> true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RepoListActivity::class.java))
        }
    }

}
