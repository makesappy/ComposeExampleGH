package com.nous.example.data.controller

import android.content.Context
import com.nous.example.domain.controller.StringResourceController

internal class AndroidStringResourceController(private val context: Context) :
    StringResourceController {

    override fun getString(id: Int, vararg formatArgs: Any) = context.getString(id, *formatArgs)
    override fun getPluralString(id: Int, quantity: Int, vararg formatArgs: Any) =
        context.resources.getQuantityString(id, quantity, *formatArgs)
}