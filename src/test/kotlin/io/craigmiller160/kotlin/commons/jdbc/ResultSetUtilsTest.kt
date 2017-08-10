package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ResultSetUtilsTest : JdbcTestCommons() {

    //TODO need to test the get() methods on the ResultSetRecord class

    @Test
    fun testItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.itr().forEach { recordCount++ }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertFalse("ResultSet should not be closed") { rs.isClosed }
        }
    }

    @Test
    fun testUseItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.useItr { it.forEach { recordCount++ } }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertTrue("ResultSet should be closed") { rs.isClosed }
        }
    }

}