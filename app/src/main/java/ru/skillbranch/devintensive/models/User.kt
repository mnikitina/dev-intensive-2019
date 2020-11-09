package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
    //var introBit:String
) {
    var introBit: String = getIntro()

    //Вторичный конструктор
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(
        id = id,
        firstName = "John",
        lastName = "Doe"
    )

    init {
        introBit = getIntro()
        println(
            "It's Alive!!! \n" +
                    "${if (lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName"}\n"//+ "${getIntro()}"
        )
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    private fun getIntro() = """
        tu tu ru ruuuuu !!!
        tu tu ru ruuuuuuuuuu ...
        
        tu tu ru ruuuuu !!!
        tu tu ru ruuuuuuuuuu ...
        ${"\n\n\n"}
        $firstName $lastName         
    """.trimIndent()

    //Вывод в консоль содержимое данного класса
    //Unit аналог void в Java
    //т.е. можно было написать fun printMe() = Unit: { println ( etc...
    fun printMe() = println(
        """
                id: $id
                firstName: $firstName
                lastName: $lastName
                avatar: $avatar
                rating: $rating
                respect: $respect
                lastVisit: $lastVisit
                isOnline: $isOnline
        """.trimIndent()
    )
}


