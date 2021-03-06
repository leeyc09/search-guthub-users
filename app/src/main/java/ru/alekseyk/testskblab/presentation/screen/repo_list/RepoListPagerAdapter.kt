package ru.alekseyk.testskblab.presentation.screen.repo_list

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.alekseyk.testskblab.R
import ru.alekseyk.testskblab.presentation.screen.repo_list.favorites_repo.FavoritesRepoFragment
import ru.alekseyk.testskblab.presentation.screen.repo_list.search_repo.SearchRepoFragment


class RepoListPagerAdapter(fragmentManager: FragmentManager, val context: Context) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.repo_list_toolbar_title_search)
            else -> context.getString(R.string.repo_list_toolbar_title_favorite)
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchRepoFragment()
            1 -> FavoritesRepoFragment()
            else -> SearchRepoFragment()
        }
    }

    override fun getCount(): Int = 2

}