package com.jackpan.stockcomputer.model

import com.google.gson.annotations.SerializedName

public class StockPreListData {
    @SerializedName("K線") var kline : String = ""
    @SerializedName("PBR") var pbr : String = ""
    @SerializedName("PER") var per : String = ""
    @SerializedName("一個月走勢圖") var onemonthtrendchart : String = ""
    @SerializedName("一年走勢圖") var oneyearchart: String = ""
    @SerializedName("三年走勢圖") var threeyearchart : String = ""
    @SerializedName("代號") var codename : String = ""
    @SerializedName("名稱") var name : String = ""
    @SerializedName("市場") var market : String = ""
    @SerializedName("成交") var deal : String = ""
    @SerializedName("成交張數") var numberofdeals : String = ""
    @SerializedName("成交額(百萬)") var turnover : String = ""
    @SerializedName("振幅(%)") var amplitude : String = ""
    @SerializedName("排名") var rank : String = ""
    @SerializedName("昨收") var closedyesterday : String = ""
    @SerializedName("最低") var lowest : String = ""
    @SerializedName("最高") var highest : String = ""
    @SerializedName("漲跌價") var aa : String = ""
    @SerializedName("漲跌幅") var aa : String = ""
    @SerializedName("股價日期") var aa : String = ""
    @SerializedName("開盤") var aa : String = ""







}