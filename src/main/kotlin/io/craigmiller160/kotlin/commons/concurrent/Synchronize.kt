package io.craigmiller160.kotlin.commons.concurrent

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A property delegating class that ensures all
 * access to the property is synchronized on
 * the object that owns the property.
 */
class Synchronize<T>(defaultValue: T) : ReadWriteProperty<Any, T> {

    private var backingField = defaultValue

    override operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        synchronized(thisRef){
            return backingField
        }
    }

    override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        synchronized(thisRef){
            backingField = value
        }
    }
}