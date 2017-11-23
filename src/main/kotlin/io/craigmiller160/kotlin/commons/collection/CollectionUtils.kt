package io.craigmiller160.kotlin.commons.collection

fun <T> Sequence<T>.transform(transform: (T) -> T): Sequence<T>{
    val list = toList()
    return object : Sequence<T>{
        override fun iterator(): Iterator<T> = list.transform(transform).iterator()
    }
}

fun <T> Iterable<T>.transform(transform: (T) -> T): List<T>{
    val itr = this
    return ArrayList<T>().apply { itr.forEach { item -> this += transform(item) } }
}
