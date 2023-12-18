package com.vshkl.beerstore5.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import com.vshkl.beerstore5.DependencyContainer
import com.vshkl.beerstore5.LocalDependencyContainer

@Composable
inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    savedStateRegistryOwner: SavedStateRegistryOwner = LocalSavedStateRegistryOwner.current,
): VM {
    return androidx.lifecycle.viewmodel.compose.viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        factory = ViewModelFactory(
            owner = savedStateRegistryOwner,
            defaultArgs = (savedStateRegistryOwner as? NavBackStackEntry)?.arguments,
            dependencyContainer = LocalDependencyContainer.current,
        )
    )
}

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?,
    private val dependencyContainer: DependencyContainer,
) : AbstractSavedStateViewModelFactory(
    owner,
    defaultArgs
) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return dependencyContainer.createViewModel(modelClass, handle)
    }
}
