package com.vshkl.beerstore5.ui.modifier

import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay

/**
 * Configure component to receive clicks via input or accessibility "click" event debounced by
 * [delay] amount of milliseconds.
 *
 * Consider setting [delay] to be long enough to cover the length of the event it is intended to
 * prevent from happening more than once. For instance, in case of navigation – consider setting it
 * to at least as long as navigation transition takes to complete.
 *
 * @see [Modifier.clickable]
 */
fun Modifier.debouncedClickable(
    delay: Long = 300L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "debounceClickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    var clicked by remember { mutableStateOf(!enabled) }

    LaunchedEffect(clicked) {
        if (clicked) {
            delay(delay)
            clicked = !clicked
        }
    }

    Modifier.clickable(
        enabled = when (enabled) {
            true -> !clicked
            false -> false
        },
    ) {
        clicked = !clicked
        onClick()
    }
}
