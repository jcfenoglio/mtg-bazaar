package com.example.mtgbazaar.screens.collection

enum class BinderActionOption(val title: String) {
    EditBinder("Edit Binder"),
    DeleteBinder("Delete Binder");

    companion object {
        fun getByTitle(title: String): BinderActionOption {
            entries.forEach { action -> if (title == action.title) return action }

            return EditBinder
        }

        fun getOptions(hasEditOption: Boolean): List<String> {
            val options = mutableListOf<String>()
            entries.forEach { taskAction ->
                if (hasEditOption || taskAction != EditBinder) {
                    options.add(taskAction.title)
                }
            }
            return options
        }
    }
}
