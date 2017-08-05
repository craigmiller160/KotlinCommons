package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StatementUtilsTest : JdbcTestCommons() {

    @Test
    fun testStatementQuickQuery(){
        var recordCount = 0
        var stmt: Statement? = null
        try{
            stmt = conn.createStatement()
            var result: ResultSet? = null
            stmt.quickQuery("SELECT * FROM people ORDER BY person_id ASC"){ rs ->
                result = rs
                while(rs.next()){
                    recordCount++
                }
            }

            assertEquals(4, recordCount, "Wrong number of records found")
            assertTrue("ResultSet did not get closed") { result?.isClosed ?: false }
            assertFalse("Statement should not be closed") { stmt?.isClosed ?: false }
        }
        finally{
            stmt?.close()
        }
    }

    @Test
    fun testStatementQuickQueryItr(){
        var recordCount = 0
        var stmt: Statement? = null
        try{
            stmt = conn.createStatement()
            stmt.quickQueryItr("SELECT * FROM people ORDER BY person_id ASC") { rs ->
                rs.forEach { recordCount++ }
            }

            assertEquals(4, recordCount, "Wrong number of records found")
            assertFalse("Statement should not be closed") { stmt?.isClosed ?: false }
        }
        finally{
            stmt?.close()
        }
    }

    @Test
    fun testPreparedStatementQuickUpdate(){
        var recordCount = 0
        var stmt: PreparedStatement? = null
        try{
            stmt = conn.prepareStatement("INSERT INTO people (first_name, last_name) VALUES (?,?)")
            val result = stmt.quickUpdate("Jimmy", "Olson")
            assertEquals(1, result, "Wrong number of rows updated")
            assertTrue("PreparedStatement should be closed") { stmt?.isClosed ?: false}

            stmt = conn.prepareStatement("SELECT COUNT(*) FROM people WHERE first_name = 'Jimmy'")
            val rs = stmt.executeQuery()
            assertTrue("ResultSet has no records") { rs.next() }
            val count = rs.getInt(1)
            assertEquals(1, count, "Record was not inserted")
        }
        finally{
            stmt?.close()
        }
    }

    @Test
    fun testPreparedStatementQuickQuery(){
        var recordCount = 0
        var stmt: PreparedStatement? = null
        try{
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM people WHERE first_name = ?")
            stmt.quickQuery("Clark"){

            }
        }
        finally{
            stmt?.close()
        }
    }

}