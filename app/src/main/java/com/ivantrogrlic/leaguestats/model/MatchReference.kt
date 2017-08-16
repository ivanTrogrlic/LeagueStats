package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class MatchReference(val lane: String,
                          val gameId: Long,
                          val champion: Int,
                          val role: String,
                          val timestamp: Long)
