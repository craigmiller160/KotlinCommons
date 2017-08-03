package io.craigmiller160.kotlin.commons.jdbc

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class StatementUtilsTest {

    private val url_start = "jdbc:h2:file:"
    private val dbFile = File("./testdb")

    private lateinit var conn: Connection

    @Before
    fun init(){
        val workingDir = File(".")
        val url = "$url_start${dbFile.absolutePath}"
        conn = DriverManager.getConnection(url)

        val script = javaClass.classLoader.getResourceAsStream("io/craigmiller160/kotlin/commons/jdbc/test-script.sql")

    }

    @After
    fun clean(){
        conn.close()
        val actualFile = File(dbFile.absolutePath + ".mv.db")
        actualFile.delete()
    }

    @Test
    fun foo(){

    }

}