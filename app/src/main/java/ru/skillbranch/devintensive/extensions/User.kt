package ru.skillbranch.devintensive.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

@RequiresApi(Build.VERSION_CODES.O)
fun User.toUserView(): UserView {
    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "Online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        initials = initials,
        status = status
    )
}

