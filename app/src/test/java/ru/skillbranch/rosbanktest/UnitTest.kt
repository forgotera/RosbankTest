package ru.skillbranch.rosbanktest


import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.rosbanktest.extensions.round
import ru.skillbranch.rosbanktest.model.POJO.JsonAnswer



/**
 * JsonAnswer local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testJsonAnswerNotNull(){
        assertNotNull(JsonAnswer())
    }

    @Test
    fun testExtensionsRound(){
        assertEquals(3.14, 3.1415.round(2),0.0)
    }
}
