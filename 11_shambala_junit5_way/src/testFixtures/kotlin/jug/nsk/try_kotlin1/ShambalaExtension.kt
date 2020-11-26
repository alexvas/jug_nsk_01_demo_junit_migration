package jug.nsk.try_kotlin1

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

// Kotlin
class ShambalaExtension : ParameterResolver, BeforeAllCallback, AfterAllCallback {

    private val shambala = Shambala.getInstance()!!

    override fun supportsParameter(parameterContext: ParameterContext, ignored: ExtensionContext) =
            parameterContext.parameter.type == Shambala::class.java

    override fun resolveParameter(ignored1: ParameterContext, ignored2: ExtensionContext) = shambala

    override fun beforeAll(context: ExtensionContext) = shambala.access()

    override fun afterAll(context: ExtensionContext) = shambala.leave()
}
