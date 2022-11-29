package com.example.affirmationscodelab.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Affirmation
 * Created by 2022/11/29
 *
 * Description:
 */
data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
