package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Topic
 * Created by 2022/11/30
 *
 * Description:
 */
data class Topic(
    @StringRes val title: Int,
    val likes: Int,
    @DrawableRes val image: Int
)
