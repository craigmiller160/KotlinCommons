package io.craigmiller160.kotlin.commons.dom

import org.w3c.dom.Node
import org.w3c.dom.NodeList

class NodeListItr(val nodeList: NodeList, val nodeType: Short = 0) : Iterable<Node>{
    override fun iterator(): Iterator<Node> {
        return object : Iterator<Node>{
            var pos = -1
            override fun hasNext(): Boolean {
                if(nodeType == 0.toShort() && pos >= nodeList.length){
                    return false
                }

                for(i in (pos..(nodeList.length - pos - 1))){
                    val node = nodeList.item(pos + i)
                    if(nodeType == node.nodeType){
                        pos = pos + i - 1
                        return true
                    }
                }

                return false
            }

            override fun next(): Node {
                pos++
                return nodeList.item(pos)
            }
        }
    }
}

fun NodeList.itr(): NodeListItr{
    return NodeListItr(this)
}

fun NodeList.typeItr(nodeType: Short): NodeListItr{
    return NodeListItr(this, nodeType)
}