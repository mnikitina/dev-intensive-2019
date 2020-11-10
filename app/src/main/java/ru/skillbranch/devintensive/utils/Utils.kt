package ru.skillbranch.devintensive.utils


object Utils {
    fun parseFullName(fullName:String?, divider : String = " ") : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(divider)

        var firstName = parts?.getOrNull(0)
        if (firstName == "")
            firstName = null

        var lastName = parts?.getOrNull(1)
        if (lastName == "")
            lastName = null

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return payload.replace(Regex("[абвгдежзийклмнопрстуфхцчшщъыьэюя]")) {
            when (it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й"->  "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh"
                "ъ" -> ""
                "ы"-> "i"
                "ь" -> ""
                "э"-> "e"
                "ю" -> "yu"
                "я" -> "ya"
                else -> ""
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstNameInitials: Char? = firstName?.get(0)
        val lastNameInitials: Char? = lastName?.get(0)
        return if ((firstNameInitials==null) || (lastNameInitials == null)) {
            ""
        } else " $firstNameInitials. $lastNameInitials."
    }
}
