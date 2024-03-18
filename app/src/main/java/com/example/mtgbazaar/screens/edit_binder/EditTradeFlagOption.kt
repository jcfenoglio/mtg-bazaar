package com.example.mtgbazaar.screens.edit_binder

enum class EditTradeFlagOption {
    Trade,
    Keep;

    companion object {
        fun getByCheckedState(checkedState: Boolean?): EditTradeFlagOption {
            val hasFlag = checkedState ?: false
            return if (hasFlag) Trade else Keep
        }

        fun getBooleanValue(flagOption: String): Boolean {
            return flagOption == Trade.name
        }

        fun getOptions(): List<String> {
            val options = mutableListOf<String>()
            entries.forEach { flagOption -> options.add(flagOption.name) }
            return options
        }
    }
}