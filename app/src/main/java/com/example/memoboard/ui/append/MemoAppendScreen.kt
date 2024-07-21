package com.example.memoboard.ui.append

import com.example.memoboard.R
import com.example.memoboard.ui.navigation.NavigationDestination

object AppendDestination : NavigationDestination {
    override val route = "append"
    override val titleRes = R.string.append_title
    const val sectionIdArg = "sectionId" // can be used as a handle for ViewModel
    val routeWithArgs = "$route/{$sectionIdArg}"
}

class MemoAppendScreen {

}
