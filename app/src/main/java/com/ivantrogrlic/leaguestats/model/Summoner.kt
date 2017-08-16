package com.ivantrogrlic.leaguestats.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

@Parcel(Parcel.Serialization.BEAN)
data class Summoner @ParcelConstructor constructor(val profileIconId: Int,
                                                   val name: String,
                                                   val summonerLevel: Long,
                                                   val revisionDate: Long,
                                                   val id: Long,
                                                   val accountId: Long)
