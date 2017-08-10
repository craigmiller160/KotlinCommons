package io.craigmiller160.kotlin.commons.extension

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ExtensionProperty<R,T>(defaultValue: T) : ReadWriteProperty<R, T> {

    private var backingField = defaultValue

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return backingField
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.backingField = value
    }
}