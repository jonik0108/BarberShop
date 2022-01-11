package com.abbasov.barbershop.models

class User {
    var idToken=""
    var name=""
    var number=""
    var date=""
    var time=""

    constructor(idToken: String, name: String, number: String, date: String, time: String) {
        this.idToken = idToken
        this.name = name
        this.number = number
        this.date = date
        this.time = time
    }

    override fun toString(): String {
        return "User(idToken='$idToken', name='$name', number='$number', date='$date', time='$time')"
    }

}