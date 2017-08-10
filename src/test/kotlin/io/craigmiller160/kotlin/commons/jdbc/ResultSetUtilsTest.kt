package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ResultSetUtilsTest : JdbcTestCommons() {

    private val firstNames = listOf("Bob", "John", "Clark", "Clark")

    @Test
    fun testItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.itr().forEachIndexed { index, record ->
                recordCount++
                val name = firstNames[index]
                assertEquals(name, record.get(2), "Wrong value for first_name with index getter")
                assertEquals(name, record.get("first_name"), "Wrong value for first_name with named getter")
            }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertFalse("ResultSet should not be closed") { rs.isClosed }
        }
    }

    @Test
    fun testUseItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.useItr { it.forEachIndexed { index, record ->
                recordCount++
                val name = firstNames[index]
                assertEquals(name, record.get(2), "Wrong value for first_name with index getter")
                assertEquals(name, record.get("first_name"), "Wrong value for first_name with named getter")
            } }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertTrue("ResultSet should be closed") { rs.isClosed }
        }
    }

}