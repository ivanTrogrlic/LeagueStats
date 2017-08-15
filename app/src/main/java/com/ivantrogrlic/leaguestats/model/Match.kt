package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class Match(val participants: List<Participant>,
                 val queue: Int,
                 val gameDuration: Long,
                 val gameCreation: Long) {

    fun getParticipant(): Participant {
        if (participants.size > 1) throw IllegalStateException("Participant needs to be filtered out")
        return participants.first()
    }

}
