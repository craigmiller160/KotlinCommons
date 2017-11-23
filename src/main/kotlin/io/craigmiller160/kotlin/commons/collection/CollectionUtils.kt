package io.craigmiller160.kotlin.commons.collection

/**
 * Transform the contents of a Sequence by applying
 * the provided function to each element.
 *
 * @param transform the function to transform each
 *                  element of the Sequence.
 * @return a new Sequence with transformed contents.
 */
fun <T> Sequence<T>.transform(transform: (T) -> T): Sequence<T>{
    val list = toList()
    return object : Sequence<T>{
        override fun iterator(): Iterator<T> = list.transform(transform).iterator()
    }
}

/**
 * Transform the contents of an Iterable by applying
 * the provided function to each element.
 *
 * @param transform the function to transform each
 *                  element of the Iterable.
 * @return a new List with the transformed contents.
 */
fun <T> Iterable<T>.transform(transform: (T) -> T): List<T>{
    val itr = this
    return ArrayList<T>().apply { itr.forEach { item -> this += transform(item) } }
}
