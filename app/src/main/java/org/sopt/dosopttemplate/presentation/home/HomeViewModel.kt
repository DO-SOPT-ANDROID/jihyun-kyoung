package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Profile

class HomeViewModel : ViewModel(){
    val mockFriendList = listOf<Profile>(
        Profile(
            profileImage = R.drawable.img_monkey,
            name = "경지현",
            musicTitle = "행복했던 날들 이었다",
            musicArtist = "데이식스",
            type = "me"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "박강희",
            musicTitle = "Gorgeous",
            musicArtist = "골져스",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "이삭",
            musicTitle = "봄아",
            musicArtist = "홍이삭",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "조세연",
            musicTitle = "세상아 덤벼라",
            musicArtist = "귀요미",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "박동민",
            musicTitle = "일본 재밌었어...?",
            musicArtist = "JJ",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "김이름",
            musicTitle = "노래",
            musicArtist = "Zion.T",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "경대승",
            musicTitle = "가시리",
            musicArtist = "작자미상",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "경전철",
            musicTitle = "슈웅",
            musicArtist = "피웅",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "이태희",
            musicTitle = "Hello",
            musicArtist = "Adele",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "이 름",
            musicTitle = "Song",
            musicArtist = "Famous Artist",
            type = "friend"
        ),
        Profile(
            profileImage = R.drawable.img_wolf,
            name = "성 함",
            musicTitle = "아리랑",
            musicArtist = "작자미상",
            type = "friend"
        ),

        )
}