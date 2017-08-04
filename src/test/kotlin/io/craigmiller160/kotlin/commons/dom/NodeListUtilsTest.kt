package io.craigmiller160.kotlin.commons.dom

import org.junit.Before
import org.junit.Test
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.test.assertEquals

class NodeListUtilsTest {

    lateinit var sampleDoc: Document

    @Before
    fun init(){
        val stream = javaClass.classLoader.getResourceAsStream("io/craigmiller160/kotlin/commons/dom/sample.xml")
        sampleDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream)
    }

    @Test
    fun testNodeListItr(){
        val root = sampleDoc.documentElement
        val children = root.childNodes
        var count = 0
        children.itr().forEach { count++ }
        assertEquals(7, count, "Iteration count incorrect")
    }

    @Test
    fun testNodeListItrType(){
        val root = sampleDoc.documentElement
        val children = root.childNodes
        var count = 0
        children.itr(Node.ELEMENT_NODE).forEach { count++ }
        assertEquals(3, count, "Iteration count incorrect")
    }

}