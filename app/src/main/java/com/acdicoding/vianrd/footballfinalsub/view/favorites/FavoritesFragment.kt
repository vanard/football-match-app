package com.acdicoding.vianrd.footballfinalsub.view.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.utils.SectionsPagerAdapter
import com.acdicoding.vianrd.footballfinalsub.view.favorites.fav_match.FavoriteMatchFragment
import com.acdicoding.vianrd.footballfinalsub.view.favorites.fav_team.FavoriteTeamFragment

class FavoritesFragment : Fragment() {

    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        mSectionsPagerAdapter.populateFragment(FavoriteMatchFragment(), getString(R.string.matches))
        mSectionsPagerAdapter.populateFragment(FavoriteTeamFragment(), getString(R.string.teams))
        vPager.adapter = mSectionsPagerAdapter
        tabs.setupWithViewPager(vPager)

    }

}