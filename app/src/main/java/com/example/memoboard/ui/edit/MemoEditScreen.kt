package com.example.memoboard.ui.edit

import com.example.memoboard.R
import com.example.memoboard.ui.navigation.NavigationDestination

object EditDestination : NavigationDestination {
    override val route = "edit"
    override val titleRes = R.string.edit_title
    const val sectionIdArg = "sectionId" // can be used as a handle for ViewModel
    val routeWithArgs = "$route/{$sectionIdArg}"
}

class MemoEditScreen {

}
