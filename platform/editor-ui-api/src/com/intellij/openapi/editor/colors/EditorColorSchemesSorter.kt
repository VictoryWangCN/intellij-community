// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.editor.colors

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import org.jetbrains.annotations.ApiStatus.Internal
import org.jetbrains.annotations.Nls

@Internal
interface EditorColorSchemesSorter {
  companion object {
    @JvmStatic
    fun getInstance(): EditorColorSchemesSorter = ApplicationManager.getApplication().service<EditorColorSchemesSorter>()
  }

  fun getOrderedSchemes(schemesMap: Map<String, EditorColorsScheme>): Groups<EditorColorsScheme>
}

@Internal
class Groups<T>(val infos: List<GroupInfo<T>>) {
  class GroupInfo<A>(val items: List<A>, @Nls val title: String? = null)

  companion object {
    fun <T>create(items: List<List<T>>): Groups<T> = Groups(items.map { GroupInfo(it) })
  }
}
