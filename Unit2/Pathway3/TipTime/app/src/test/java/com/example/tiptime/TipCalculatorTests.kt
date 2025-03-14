package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * TipCalculatorTests
 * Created by 2022/11/16
 *
 * Description:
 */
class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "₩2"
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)

        assertEquals(expectedTip, actualTip)
    }
}