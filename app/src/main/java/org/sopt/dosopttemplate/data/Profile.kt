package org.sopt.dosopttemplate.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.R

@Parcelize
data class Profile(
    @DrawableRes
    val profileImage: Int?,
    var name: String,
    var musicTitle: String?,
    var musicArtist: String?,
    val type: Int,
    var MBTI: String?,
    var intro: String?,
    val id: String?,
    val email: String?,
    val avatar: String?,
    val idInt: Int?
) : Parcelable {
    var music:Music? = null
    init {
        setMusic()
    }
    constructor(
        profileImage: Int,
        name: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        null,
        null,
        null,
        null,
        null,
        null
    )

    constructor(
        name: String,
        type: Int,
        MBTI: String,
        intro: String
    ) : this(
        R.drawable.img_monkey,
        name,
        null,
        null,
        type,
        MBTI,
        intro,
        null,
        null,
        null,
        null
    )

    constructor(
        profileImage: Int,
        name: String,
        musicTitle: String,
        musicArtist: String,
        type: Int,
        MBTI: String,
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        MBTI,
        null,
        null,
        null,
        null,
        null
    )

    constructor(
        profileImage: Int,
        name: String,
        musicTitle: String?,
        musicArtist: String?,
        type: Int,
        MBTI: String?,
        intro: String?,
        id: String?
    ) : this(
        profileImage,
        name,
        musicTitle,
        musicArtist,
        type,
        MBTI,
        intro,
        id,
        null,
        null,
        null
    )

    constructor(
        name: String,
        email: String,
        avatar: String,
        idInt: Int
    ): this(
        null,
        name,
        null,
        null,
        FRIEND,
        null,
        null,
        null,
        email,
        avatar,
        idInt
    )
    fun isContainMusic(): Boolean = (music != null)
    fun getMusic(): String = if (isContainMusic()) music!!.string else "no music"

    fun setMusic() {
        if (musicArtist != null && musicTitle != null)
            this.music = Music(musicTitle!!, musicArtist!!)
        else this.music = null

    }
    companion object {
        const val ME = 1
        const val FRIEND = 0
    }
}
