package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class MatchList(val matches: List<MatchReference>,
                     val totalGames: Int,
                     val startIndex: Int,
                     val endIndex: Int)
