package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class Participant(val stats: ParticipantStats,
                       val spell1Id: Int,
                       val spell2Id: Int,
                       val championId: Int)
