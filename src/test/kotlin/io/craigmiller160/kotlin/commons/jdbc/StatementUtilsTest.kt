package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import java.sql.ResultSet
import java.sql.Statement
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StatementUtilsTest : JdbcTestCommons() {

    @Test
    fun testUseQuickQuery(){
        var recordCount = 0
        var stmt: Statement? = null
        try{
            stmt = conn.createStatement()
            var result: ResultSet? = null
            stmt.useQuickQuery("SELECT * FROM people ORDER BY person_id ASC"){ rs ->
                result = rs
                assertTrue("ResultSet doesn't have first record") { rs.next() }
                recordCount++
                assertEquals("Bob", rs.getString("first_name"), "Wrong value for first first_name")

                assertTrue("ResultSet doesn't have second value") { rs.next() }
                recordCount++
                assertEquals("John", rs.getString("first_name"), "Wrong value for second first_name")
            }

            assertEquals(2, recordCount, "Wrong number of records found")
            assertTrue("ResultSet did not get closed") { result?.isClosed ?: false }
            assertFalse("Statement should not be closed") { stmt?.isClosed ?: false }
        }
        finally{
            stmt?.close()
        }
    }

    @Test
    fun testUseQuickQueryItr(){
        var recordCount = 0
        var stmt: Statement? = null
        try{
            stmt = conn.createStatement()
            stmt.useQuickQueryItr("SELECT * FROM people ORDER BY person_id ASC") { rs ->
                rs.forEachIndexed { i, rs ->
                    recordCount++
                    when(i){
                        0 -> assertEquals("Bob", rs.getString("first_name"), "Wrong value for first first_name")
                        1 -> assertEquals("John", rs.getString("first_name"), "Wrong value for second first_name")
                        else -> throw Exception("Invalid index: " + i)
                    }
                }
            }

            assertEquals(2, recordCount, "Wrong number of records found")
            assertFalse("Statement should not be closed") { stmt?.isClosed ?: false }
        }
        finally{
            stmt?.close()
        }
    }

}