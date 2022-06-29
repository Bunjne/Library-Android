package com.example.practiceafterwat.presentation.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practiceafterwat.presentation.home.LibraryBookBorrowFragment

private const val NUM_TABS = 2

class LibraryTabAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifeCycle)  {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LibraryBookBorrowFragment()
            else -> LibraryBookBorrowFragment()
        }
    }
}