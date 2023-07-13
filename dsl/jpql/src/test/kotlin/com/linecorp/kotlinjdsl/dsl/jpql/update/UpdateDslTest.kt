package com.linecorp.kotlinjdsl.dsl.jpql.update

import com.linecorp.kotlinjdsl.dsl.jpql.AbstractJpqlDslTest
import com.linecorp.kotlinjdsl.dsl.jpql.update.impl.UpdateQuerySetStepDsl
import com.linecorp.kotlinjdsl.querymodel.jpql.JpqlQueryable
import com.linecorp.kotlinjdsl.querymodel.jpql.impl.AliasedPath
import com.linecorp.kotlinjdsl.querymodel.jpql.impl.Entity
import org.junit.jupiter.api.Test

class UpdateDslTest : AbstractJpqlDslTest() {
    private val alias1 = "alias1"

    @Test
    fun `update entity`() {
        // when
        val update = testJpql {
            update(entity(TestTable::class))
        }

        val actual: UpdateQuerySetStep<TestTable> = update // for type check

        // then
        val expected = UpdateQuerySetStepDsl(
            AliasedPath(Entity(TestTable::class), TestTable::class.simpleName!!),
        )

        assertThat(actual)
            .isNotInstanceOf(JpqlQueryable::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun `update entity as string`() {
        // when
        val update = testJpql {
            update(entity(TestTable::class).`as`(alias1))
        }

        val actual: UpdateQuerySetStep<TestTable> = update // for type check

        // then
        val expected = UpdateQuerySetStepDsl(
            AliasedPath(Entity(TestTable::class), alias1),
        )

        assertThat(actual)
            .isNotInstanceOf(JpqlQueryable::class.java)
            .isEqualTo(expected)
    }

    private class TestTable
}
