@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ShambalaExtension::class)
class `где-то тамии` {

    @Nested
    inner class Шамбала {
        @Test
        fun `настоящая Шамбала!`(shambala: Shambala) = assertTrue(shambala.isAuthentic, "Shambala is a real one")

        @Test
        fun `точно Шамбала!`(shambala: Shambala) = assertTrue(shambala.isAuthentic, "Shambala is still a real one")

    }

    @Nested
    inner class Авалон {
        @Test
        fun `это Авалон!`(shambala: Shambala) = assertTrue(shambala.isAvalon, "Shambala is an Avalon")

        @Test
        fun `может всё-таки Авалон?`(shambala: Shambala) = assertTrue(shambala.isAvalon, "maybe Shambala is an Avalon?")
    }

    @Nested
    inner class Вальгалла {
        @Test
        fun `это Вальгалла!`(shambala: Shambala) = assertTrue(shambala.isValhalla, "Shambala is Valhalla")

        @Test
        fun `может всё-же Вальгалла?`(shambala: Shambala) = assertTrue(shambala.isValhalla, "maybe Shambala is Valhalla?")

        @Test
        fun `наверное Вальгалла?`(shambala: Shambala) = assertTrue(shambala.isValhalla, "probably Shambala is Valhalla?")
    }

    @Nested
    inner class Китеж {
        @Test
        fun `это Китеж!`(shambala: Shambala) = assertTrue(shambala.`это Китеж`, "Shambala -- это Китеж")
    }

    @Nested
    inner class Элизий {
        @Test
        fun `это Элизий!`(shambala: Shambala) = assertTrue(shambala.ElysiumEst, "Shambala Elysium est")
    }

    @Nested
    inner class Винета {
        @Test
        fun `это Винета!`(shambala: Shambala) = assertTrue(shambala.isVineta, "Shambala is Vineta, как говаривал Адам фон Бремен")
    }

    @Nested
    inner class Ис {
        @Test
        fun `это город Ис!`(shambala: Shambala) = assertTrue(shambala.isKerIs, "Shambala is Ker Is")
    }

    @Nested
    inner class Кокань {
        @Test
        fun `это страна Кокань!`(shambala: Shambala) = assertTrue(shambala.estPaysDeCocagne, "Shambala est Pays de Cocagne")
    }

    @Nested
    inner class Шлараффенланд {
        @Test
        fun `это Шлараффенланд!`(shambala: Shambala) = assertTrue(shambala.dasSchlaraffenland, "Shambala das Schlaraffenland")
    }



}
