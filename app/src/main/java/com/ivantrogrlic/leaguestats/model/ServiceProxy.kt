package com.ivantrogrlic.leaguestats.model

import android.content.Context
import com.ivantrogrlic.leaguestats.R

/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

enum class ServiceProxy {
  BR {
    override fun serverName(context: Context): String = context.getString(R.string.server_br)
    override fun serverHost() = "https://br1.api.riotgames.com/"
  },
  EUNE {
    override fun serverName(context: Context): String = context.getString(R.string.server_eune)
    override fun serverHost() = "https://eun1.api.riotgames.com/"
  },
  EUW {
    override fun serverName(context: Context): String = context.getString(R.string.server_euw)
    override fun serverHost() = "https://euw1.api.riotgames.com/"
  },
  JP {
    override fun serverName(context: Context): String = context.getString(R.string.server_jp)
    override fun serverHost() = "https://jp1.api.riotgames.com/"
  },
  KR {
    override fun serverName(context: Context): String = context.getString(R.string.server_kr)
    override fun serverHost() = "https://kr.api.riotgames.com/"
  },
  LAN {
    override fun serverName(context: Context): String = context.getString(R.string.server_lan)
    override fun serverHost() = "https://la1.api.riotgames.com/"
  },
  LAS {
    override fun serverName(context: Context): String = context.getString(R.string.server_las)
    override fun serverHost() = "https://la2.api.riotgames.com/"
  },
  NA {
    override fun serverName(context: Context): String = context.getString(R.string.server_na)
    override fun serverHost() = "https://na1.api.riotgames.com/"
  },
  OCE {
    override fun serverName(context: Context): String = context.getString(R.string.server_oce)
    override fun serverHost() = "https://oc1.api.riotgames.com/"
  },
  TR {
    override fun serverName(context: Context): String = context.getString(R.string.server_tr)
    override fun serverHost() = "https://tr1.api.riotgames.com/"
  },
  RU {
    override fun serverName(context: Context): String = context.getString(R.string.server_ru)
    override fun serverHost() = "https://ru.api.riotgames.com/"
  };
  
  abstract fun serverName(context: Context): String
  abstract fun serverHost(): String
}
