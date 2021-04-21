package com.jackpan.stockcomputer.model;

import com.google.gson.annotations.SerializedName;

public class StockEpsListData {
    @SerializedName("排名")
    public String rank =  null ; ;

    @SerializedName("代號")
    public String codename= null ; ;

    @SerializedName("名稱")
    public String name= null ; ;

    @SerializedName("成交")
    public String deal= null ; ;

    @SerializedName("財報年度")
    public String financialyear= null ; ;

    @SerializedName("營收(億)")
    public String revenue= null ; ;

    @SerializedName("營收成長(%)")
    public String revenuegrowth= null ; ;

    @SerializedName("毛利(億)")
    public String grossprofitbillion= null ; ;

    @SerializedName("毛利成長(%)")
    public String grossprofitgrowth= null ; ;

    @SerializedName("淨利(億)")
    public String netincomebillion= null ; ;

    @SerializedName("淨利成長(%)")
    public String netincomegrowth= null ; ;

    @SerializedName("毛利(%)")
    public String grossprofit= null ; ;

    @SerializedName("毛率增減")
    public String grossprofitiod= null ; ;

    @SerializedName("淨利(%)")
    public String netincome= null ; ;

    @SerializedName("淨率增減")
    public String netincomeiod= null ; ;

    @SerializedName("EPS(元)")
    public String eps= null ; ;

    @SerializedName("EPS增減(元)")
    public String epsiod= null ; ;

    @SerializedName("ROE(%)")
    public String roe= null ; ;

    @SerializedName("ROE增減")
    public String roeiod= null ; ;

    @SerializedName("ROA(%)")
    public String poa= null ; ;

    @SerializedName("ROA增減")
    public String poaiod= null ; ;

    @SerializedName("財報評分")
    public String score= null ; ;
}
