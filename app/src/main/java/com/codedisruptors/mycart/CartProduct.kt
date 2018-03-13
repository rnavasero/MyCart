package com.codedisruptors.mycart

/**
 * Created by codemagnus on 3/13/18.
 */
class CartProduct{
    var id:Int  = 0
    var pName: String = ""
    var pDesc:String = ""
    var pPrice: Double = 0.0
    var pimageURL:String  = ""
    var pQty: Int = 0

    constructor()

    constructor(id: Int, pName: String, pDesc: String, pPrice: Double, pimageURL: String, pQty: Int) {
        this.id = id
        this.pName = pName
        this.pDesc = pDesc
        this.pPrice = pPrice
        this.pimageURL = pimageURL
        this.pQty = pQty
    }


}
