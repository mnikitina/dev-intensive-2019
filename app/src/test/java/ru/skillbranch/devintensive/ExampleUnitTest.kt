package ru.skillbranch.devintensive

import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        //val user2 = User("2", "John", "Cena")
        //val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        //user2.printMe()
        //user3.printMe()
        //println("$user $user2 $user3")
        //println("$user")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Wick")
        //print(user)
        //if (user == null) print("One of the fields have null value")
        val user2 = user.copy(id = "2", lastName = "Cena", lastVisit = Date())
        print("$user \n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
        println("$id $firstName $lastName")
        println("${user.component1()} ${user.component2()} ${user.component3()}")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date())
        val user3 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.SECOND))
        val user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        if (user == user2) {
            println("equals data and hash ${user.hashCode()} \n ${user2.hashCode()}")
        } else {
            println("not equals data and hash ${user.hashCode()} \n ${user2.hashCode()}")
        }
        //Сравнение ссылки ===
        //user2 = user //тогда ссылается на тот же адрес
        if (user === user2) {
            println("equals address ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        } else {
            println(
                "not equals address ${System.identityHashCode(user)} ${System.identityHashCode(
                    user2
                )}"
            )
        }

        //user2.lastName = "Doe"
        //println("$user \n$user2")

        println(
            """
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_dataq_maping() {
        val user = User.makeUser("Макеев Михаил")
        val newUser = user.copy(lastVisit = Date().add(-7, TimeUnits.DAY))
        println(newUser)
        val userView = newUser.toUserView()
        userView.printMe()

    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("John Test")
        val txtMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(txtMessage.formatMessage())
        println(imageMessage.formatMessage())
        when(imageMessage){
            is BaseMessage-> println("this is base message")
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")
        }
    }
}
