package com.thoriq.kasirku.cls

class cekclass() {
    fun mengandungSimbol(kata: String): Boolean {
        val regex = Regex("[^a-zA-Z0-9 ]") // Regex untuk mencocokkan karakter selain huruf dan angka

        return regex.containsMatchIn(kata)
    }
    fun hurufPertamaHuruf(kata: String): Boolean {
        if (kata.isNotEmpty()) {
            val hurufPertama = kata[0]
            return hurufPertama.isLetter()
        }
        return false
    }
    fun mengandungSimbolRegister(kata: String): Boolean {
        val regex = Regex("[^a-zA-Z0-9@.]") // Regex untuk mencocokkan karakter selain huruf dan angka

        return regex.containsMatchIn(kata)
    }
}