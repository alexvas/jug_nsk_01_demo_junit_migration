package jug.nsk.try_kotlin1

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class ShambalaExtension : ParameterResolver, BeforeAllCallback {

    private val shambala = Shambala.getInstance()!!

    override fun supportsParameter(parameterContext: ParameterContext, ignored: ExtensionContext) =
            parameterContext.parameter.type == Shambala::class.java

    override fun resolveParameter(ignored1: ParameterContext, ignored2: ExtensionContext) = shambala

    override fun beforeAll(context: ExtensionContext) {
        context.getStore(ExtensionContext.Namespace.GLOBAL)
                .getOrComputeIfAbsent("shambalaAdapter") {
                    ShambalaAdapter(shambala)
                }
    }
}

internal class ShambalaAdapter(private val shambala: Shambala) : ExtensionContext.Store.CloseableResource {

    init {
        shambala.access()
    }

    override fun close() {
        shambala.leave()
    }
}
