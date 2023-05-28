package com.magdenbt.collectionsbenchmark.ui.viewflow

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.window.DialogWindowProvider
import androidx.fragment.app.DialogFragment
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme

fun DialogFragment.dialogFragmentComposeView(
    _consumeWindowInsets: Boolean,
    content: @Composable () -> Unit,
): View {
    return DialogFragmentComposeView(
        context = requireContext(),
        dialogProvider = { dialog ?: Dialog(requireContext()) },
    ).apply {
        consumeWindowInsets = _consumeWindowInsets
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )

        // Another interesting flaw. If you use other strategies in DialogFragment you may get crashes 🫠
        setViewCompositionStrategy(strategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow)

        setContent {
            AppTheme {
                content()
            }
        }
    }
}

internal class DialogFragmentComposeView(
    context: Context,
    private val dialogProvider: () -> Dialog,
) : AbstractComposeView(context, null, 0),
    DialogWindowProvider {

    // The fix is here
    override val window get() = dialogProvider().window!!

    private val content = mutableStateOf<(@Composable () -> Unit)?>(null)

    override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    @Composable
    override fun Content() {
        content.value?.invoke()
    }

    override fun getAccessibilityClassName(): CharSequence = ComposeView::class.java.name

    /**
     * Set the Jetpack Compose UI content for this view.
     * Initial composition will occur when the view becomes attached to a window or when
     * [createComposition] is called, whichever comes first.
     */
    fun setContent(content: @Composable () -> Unit) {
        shouldCreateCompositionOnAttachedToWindow = true
        this.content.value = content
        if (isAttachedToWindow) createComposition()
    }
}

internal var DialogFragmentComposeView.consumeWindowInsets: Boolean
    get() = getTag(androidx.compose.ui.R.id.consume_window_insets_tag) as? Boolean ?: true
    set(value) {
        setTag(androidx.compose.ui.R.id.consume_window_insets_tag, value)
    }
