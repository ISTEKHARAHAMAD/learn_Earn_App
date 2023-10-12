package com.example.earningapp.modle

 class HistoryModelClass{
     var timaAndDate:String=""
     var coin:String=""
     var isWithDrawal:Boolean=false
constructor()
     constructor(timaAndDate: String, coin: String, isWithDrawal: Boolean) {
         this.timaAndDate = timaAndDate
         this.coin = coin
         this.isWithDrawal = isWithDrawal
     }
 }
