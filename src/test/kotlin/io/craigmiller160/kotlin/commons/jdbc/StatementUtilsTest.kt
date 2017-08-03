package io.craigmiller160.kotlin.commons.jdbc

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
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
        conn.createStatement().use { stmt ->
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

            assertTrue("ResultSet did not get closed") { result?.isClosed ?: false }
        }

        assertEquals(2, recordCount, "Wrong number of records found")
    }

}