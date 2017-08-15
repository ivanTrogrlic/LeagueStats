package com.ivantrogrlic.leaguestats.model

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class ParticipantStats(val deaths: Int,
                            val win: Boolean,
                            val item0: Int,
                            val item1: Int,
                            val item2: Int,
                            val item3: Int,
                            val item4: Int,
                            val item5: Int,
                            val item6: Int,
                            val totalMinionsKilled: Int,
                            val champLevel: Int,
                            val kills: Int,
                            val assists: Int)
