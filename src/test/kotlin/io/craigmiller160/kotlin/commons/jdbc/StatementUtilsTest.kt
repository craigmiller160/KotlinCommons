package io.craigmiller160.kotlin.commons.jdbc

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StatementUtilsTest {

    private val url_start = "jdbc:h2:file:"
    private val dbFile = File("./testdb")

    private lateinit var conn: Connection

    @Before
    fun init(){
        val url = "$url_start${dbFile.absolutePath}"
        conn = DriverManager.getConnection(url)

        val scriptStream = javaClass.classLoader.getResourceAsStream("io/craigmiller160/kotlin/commons/jdbc/test-script.sql")
        val script = scriptStream.bufferedReader().readText()
        conn.createStatement().use { it.executeUpdate(script) }
    }

    @After
    fun clean(){
        conn.close()
        val actualFile = File(dbFile.absolutePath + ".mv.db")
        actualFile.delete()
    }

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