package com.coderkube.composeshowcaseview

object Data {
    var userList = listOf(
        Item(id = 1, profilePic = R.drawable.dp1, name = "Coderkube Technologies")
    )
}

data class Item(val id: Int, val profilePic: Int, val name: String)