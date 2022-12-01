package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Heroes
 * Created by 2022/12/01
 *
 * Description:
 */
data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)