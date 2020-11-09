package ru.skillbranch.devintensive.models

import android.os.Build
import androidx.annotation.RequiresApi
import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} ${if (isIncoming) "получил" else "отправил"} изображение \" $image\" ${date.humanizeDiff()}"
}