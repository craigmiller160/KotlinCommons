package io.craigmiller160.kotlin.commons.concurrent

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Synchronize<T>(defaultValue: T) : ReadWriteProperty<Any, T> {

    private var backingField = defaultValue

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        synchronized(thisRef){
            return backingField
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        synchronized(thisRef){
            backingField = value
        }
    }
}