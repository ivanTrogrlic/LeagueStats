package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class Match(val participants: List<Participant>,
                 val gameDuration: Long,
                 val gameCreation: Long)
