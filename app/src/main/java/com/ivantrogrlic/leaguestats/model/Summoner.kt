package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

data class Summoner(val profileIconId: Int,
                    val name: String,
                    val summonerLevel: Long,
                    val revisionDate: Long,
                    val id: Long,
                    val accountId: Long)
