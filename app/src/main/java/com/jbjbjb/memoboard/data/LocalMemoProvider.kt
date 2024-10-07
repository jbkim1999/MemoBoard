package com.jbjbjb.memoboard.data

import java.time.LocalDateTime

object LocalMemoProvider {

    private val memoList = mutableListOf<Memo>(
        Memo(
            0, "BFS",
            """
                    - BFS is not that easy
                       -  You might think it's easy but it's not
                          -  This is a nested bullet
                             -  Another one
                """.trimIndent(), LocalDateTime.of(2024, 6, 10, 18, 27)
        ),
        Memo(
            1, "DFS",
            """
                    # DFS is tricky!
                    ## I know because I have tried it
                    ### Keep in mind they are headings
                """.trimIndent(), LocalDateTime.of(2024, 6, 10, 18, 30)
        ),
        Memo(
            2, "Backtracking",
            """
                    ```
                    Backtracking is tricky because I think it is
                    ```
                    ![Exponential Image](https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Exponential.svg/1200px-Exponential.svg.png)
                """.trimIndent(), LocalDateTime.of(2023, 11, 28, 9, 13)
        ),
        Memo(
            3, "Binary Search",
            """
                    If you want to know about it... <br/>
                    See this [link](https://en.wikipedia.org/wiki/Binary_search)
                """.trimIndent(), LocalDateTime.of(2023, 12, 28, 9, 13)
        ),
    )

    fun getAllMemos(): MutableList<Memo> {
        return memoList
    }
}
