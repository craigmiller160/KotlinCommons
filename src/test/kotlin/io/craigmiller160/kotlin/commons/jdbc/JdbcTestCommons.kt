package io.craigmiller160.kotlin.commons.jdbc

import org.junit.After
import org.junit.Before
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

abstract class JdbcTestCommons {

    protected val url_start = "jdbc:h2:file:"
    protected val dbFile = File("./testdb")

    protected lateinit var conn: Connection

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

}