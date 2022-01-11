package uz.umarxon.barbershopui.models

import java.io.Serializable

class Barber :Serializable{
    var name :String? = null
    var about:String? = null
    var image :Int? = null

    constructor(name: String?, about: String?, image: Int?) {
        this.name = name
        this.about = about
        this.image = image
    }

    constructor()
}