package com.ivantrogrlic.leaguestats.model

import android.content.Context
import com.ivantrogrlic.leaguestats.R

/**
 * Created by ivan on 8/14/2017.
 */

enum class QueueType(val queueId: Int) {
    CUSTOM(0),
    NORMAL_3V3(8),
    NORMAL_5V5_BLIND_1(2),
    NORMAL_5v5_DRAFT(14),
    RANKED_5V5_SOLO(4),
    RANKED_5V5_PREMADE(6),
    RANKED_3V3_FLEX(9),
    RANKED_3V3_TEAM(41),
    RANKED_5V5_TEAM(42),
    DOMINATION_5V5_BLIND(16),
    DOMINATION_5V5_DRAFT(17),
    COOP_VS_AI_RIFT(7),
    COOP_VS_AI_DOMINION(25),
    COOP_VS_AI_RIFT_INTRO(31),
    COOP_VS_AI_RIFT_BEGINNER(32),
    COOP_VS_AI_RIFT_INTERMEDIATE(33),
    COOP_VS_AI_TWISTED_TREELINE(52),
    TEAM_BUILDER(61),
    ARAM(65),
    ONE_FOR_ALL(70),
    SNOWDOWN_SHOWDOWN_1V1(72),
    SNOWDOWN_SHOWDOWN_2V2(73),
    HEXAKILL_RIFT(75),
    URF(76),
    ONE_FOR_ALL_MIRROR(78),
    URF_AI(83),
    DOOM_BOTS_RANK_1(91),
    DOOM_BOTS_RANK_2(92),
    DOOM_BOTS_RANK_3(93),
    ASCENSION(96),
    HEXAKILL_TWISTED_TREELINE(98),
    BUTCHERS_BRIDGE(100),
    KING_PORO(300),
    NEMESIS(310),
    BLACK_MARKET_BRAWLERS(313),
    NEXUS_SIEGE(315),
    DEFINITELY_NOT_DOMINION(317),
    ALL_RANDOM_URF(318),
    ALL_RANDOM_RIFT(325),
    NORMAL_5V5_DRAFT(400),
    RANKED_5V5_DRAFT(410),
    RANKED_SOLO_RIFT(420),
    NORMAL_5V5_BLIND_2(430),
    RANKED_FLEX_RIFT(440),
    BLOOD_HUNT_ASSASSIN(600),
    DARK_STAR(310)
}

fun queueName(context: Context, queueId: Int): String {
    val queues = context.resources.getStringArray(R.array.queue_names)
    return when (queueId) {
        0 -> queues[0]
        8 -> queues[1]
        2 -> queues[2]
        14 -> queues[3]
        4 -> queues[4]
        6 -> queues[5]
        9 -> queues[6]
        41 -> queues[7]
        42 -> queues[8]
        16 -> queues[9]
        17 -> queues[10]
        7 -> queues[11]
        25 -> queues[12]
        31 -> queues[13]
        32 -> queues[14]
        33 -> queues[15]
        52 -> queues[16]
        61 -> queues[17]
        65 -> queues[18]
        70 -> queues[19]
        72 -> queues[20]
        73 -> queues[21]
        75 -> queues[22]
        76 -> queues[23]
        78 -> queues[24]
        83 -> queues[25]
        91 -> queues[26]
        92 -> queues[27]
        93 -> queues[28]
        96 -> queues[29]
        98 -> queues[30]
        100 -> queues[31]
        300 -> queues[32]
        310 -> queues[33]
        313 -> queues[34]
        315 -> queues[35]
        317 -> queues[36]
        318 -> queues[37]
        325 -> queues[38]
        400 -> queues[39]
        410 -> queues[40]
        420 -> queues[41]
        430 -> queues[42]
        440 -> queues[43]
        600 -> queues[44]
        610 -> queues[45]
        else -> queues[46]
    }
}
