package com.muhammadfarhaan.latihanshared.Model

class UserModel {

    private var username: String? = null
    private var password: String? = null
    private var phone: String? = null

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }
}