package com.jackpan.stockcomputer.model

import com.google.gson.annotations.SerializedName

data class StockEpsListData(
    @SerializedName("排名")
    var rank : String,
    @SerializedName("代號")
    var codename : String,
    @SerializedName("名稱")
    var name : String,
    @SerializedName("成交")
    var deal : String,
    @SerializedName("財報年度")
    var financialyear : String,
    @SerializedName("營收(億)")
    var revenue : String,
    @SerializedName("營收成長(%)")
    var revenuegrowth : String,
    @SerializedName("毛利(億)")
    var grossprofitbillion : String,
    @SerializedName("毛利成長(%)")
    var grossprofitgrowth : String,
    @SerializedName("淨利(億)")
    var netincomebillion : String,
    @SerializedName("淨利成長(%)")
    var netincomegrowth : String,
    @SerializedName("毛利(%)")
    var grossprofit : String,
    @SerializedName("毛率增減")
    var grossprofitiod : String,
    @SerializedName("淨利(%)")
    var netincome : String,
    @SerializedName("淨率增減")
    var netincomeiod : String,
    @SerializedName("EPS(元)")
    var eps : String,
    @SerializedName("EPS增減(元)")
    var epsiod : String,
    @SerializedName("ROE(%)")
    var roe : String,
    @SerializedName("ROE增減")
    var roeiod : String,
    @SerializedName("ROA(%)")
    var poa : String,
    @SerializedName("ROA增減")
    var poaiod : String,
    @SerializedName("財報評分")
    var score : String
    )