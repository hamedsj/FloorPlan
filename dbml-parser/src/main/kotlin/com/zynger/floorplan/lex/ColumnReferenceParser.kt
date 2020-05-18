package com.zynger.floorplan.lex

import com.zynger.floorplan.Reference
import com.zynger.floorplan.ReferenceOrder

object ColumnReferenceParser {
    private val COLUMN_REFERENCE_REGEX = Regex("""(([Rr]ef)\s*:\s*([<>-])\s*("\w+"|\w+)\.("\w+"|\w+))""")

    fun parse(tableName: String, fromColumn: String, columnProperties: String): Reference? {
        return if (COLUMN_REFERENCE_REGEX.containsMatchIn(columnProperties)) {
            val referenceProperties = COLUMN_REFERENCE_REGEX.find(columnProperties)!!
            Reference(
                rawValue = referenceProperties.groups[0]!!.value,
                fromTable = tableName.removeSurroundQuotes(),
                fromColumn = fromColumn.removeSurroundQuotes(),
                referenceOrder = ReferenceOrder.fromString(referenceProperties.groups[3]!!.value),
                toTable = referenceProperties.groups[4]!!.value.removeSurroundQuotes(),
                toColumn = referenceProperties.groups[5]!!.value.removeSurroundQuotes()
            )
        } else null
    }

    private fun String.removeSurroundQuotes(): String {
        return this.removeSurrounding("\"")
    }
}