
package jug.nsk.try_kotlin1

import kotlinx.coroutines.delay

// Kotlin
class Client {

    suspend fun call(): String {
        delay(100)
        return "done"
    }
}
