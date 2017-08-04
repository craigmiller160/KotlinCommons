package io.craigmiller160.kotlin.commons.dom

import org.w3c.dom.Node
import org.w3c.dom.NodeList

class NodeListItr(val nodeList: NodeList, val nodeType: Short = 0) : Iterable<Node>{
    override fun iterator(): Iterator<Node> {
        return object : Iterator<Node>{
            var pos = -1
            override fun hasNext(): Boolean {
                if(nodeType == 0.toShort()){
                    return pos < nodeList.length - 1
                }

                var tempPos = pos + 1

                for(i in (tempPos..(nodeList.length - 1))){
                    val node = nodeList.item(i)
                    if(nodeType == node.nodeType){
                        pos = i - 1
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

fun NodeList.itr(nodeType: Short = 0): NodeListItr{
    return NodeListItr(this, nodeType)
}