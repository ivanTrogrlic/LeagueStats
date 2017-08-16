package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class Match(val participantIdentities: List<ParticipantIdentity>,
                 val participants: List<Participant>,
                 val queue: Int,
                 val gameDuration: Long,
                 val gameCreation: Long,
                 val participant: Participant?)
