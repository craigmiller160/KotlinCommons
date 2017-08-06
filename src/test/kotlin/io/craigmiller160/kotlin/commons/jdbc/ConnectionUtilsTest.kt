package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConnectionUtilsTest : JdbcTestCommons() {

    @Test
    fun testQuickTimeout(){
        assertEquals(0, conn.quickTimeout, "Initial quick timeout value is incorrect")
        conn.quickTimeout = 20
        assertEquals(20, conn.quickTimeout, "New quick timeout value is incorrect")
    }

    @Test
    fun testQuickQuery(){
        var recordCount = 0
        conn.quickQuery("SELECT * FROM people WHERE first_name = ?", "Clark"){ rs ->
            val firstName = rs.getString("first_name")
            recordCount++
            assertEquals("Clark", firstName, "Wrong first name value")
        }
        assertEquals(2, recordCount, "Wrong number of records iterated over")
    }

    @Test
    fun testQuickUpdate(){
        val result = conn.quickUpdate("INSERT INTO people (first_name, last_name) VALUES (?,?)", "Jimmy", "Carter")
        assertEquals(1, result, "Wrong number of rows updated")

        conn.prepareStatement("SELECT * FROM people WHERE first_name = ?").use { stmt ->
            stmt.setString(1, "Jimmy")
            stmt.executeQuery().use { rs ->
                assertTrue("ResultSet has no records") { rs.next() }
                assertEquals("Jimmy", rs.getString("first_name"), "Wrong first record first_name")
                assertEquals("Carter", rs.getString("last_name"), "Wrong first record last_name")
            }
        }
    }

    @Test
    fun testQuickBatch(){
        conn.quickBatch("INSERT INTO people (first_name, last_name) VALUES (?,?)"){
            addBatch("Jimmy", "Olson")
            addBatch("Perry", "White")
        }

        conn.prepareStatement("SELECT * FROM people WHERE person_id > 4 ORDER BY person_id ASC").use { it.executeQuery().use { rs ->
            assertTrue("ResultSet doesn't have first record") { rs.next() }
            assertEquals("Jimmy", rs.getString("first_name"), "Wrong first record first_name")
            assertEquals("Olson", rs.getString("last_name"), "Wrong first record last_name")

            assertTrue("ResultSet doesn't have second record") { rs.next() }
            assertEquals("Perry", rs.getString("first_name"), "Wrong second record first_name")
            assertEquals("White", rs.getString("last_name"), "Wrong second record last_name")

            assertFalse("ResultSet shouldn't have third record") { rs.next() }
        }}
    }

}