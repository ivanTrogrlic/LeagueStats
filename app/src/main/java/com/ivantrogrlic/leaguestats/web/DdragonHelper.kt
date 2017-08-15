package com.ivantrogrlic.leaguestats.web

/**
 * Created by ivanTrogrlic on 19/07/2017.
 */

fun getProfileIconUrl(profileIconId: Int) =
        "https://ddragon.leagueoflegends.com/cdn/7.14.1/img/profileicon/$profileIconId.png"

fun getItemIconUrl(itemIconId: Int) =
        "https://ddragon.leagueoflegends.com/cdn/7.14.1/img/item/$itemIconId.png"

fun getChampionIconUrl(championName: String) =
        "https://ddragon.leagueoflegends.com/cdn/7.14.1/img/champion/$championName.png"

fun getSummonerIconUrl(summonerSpellName: String) =
        "https://ddragon.leagueoflegends.com/cdn/7.14.1/img/spell/$summonerSpellName.png"

