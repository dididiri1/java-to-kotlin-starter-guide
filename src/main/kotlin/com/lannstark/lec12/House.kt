package com.lannstark.lec12

import com.lannstark.lec13.JavaHouse.LivingRoom

fun main() {

}

class JavaHouse(
    val  address: String,
    val livingRoom: LivingRoom,
) {
    inner class LivingRoom(
        private val area: Double,
    ) {
        val address: String
            get() = this@JavaHouse.address

    }
}