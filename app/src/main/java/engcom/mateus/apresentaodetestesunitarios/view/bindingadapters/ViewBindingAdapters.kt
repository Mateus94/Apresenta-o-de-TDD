@file:JvmName("ViewBindingAdapters")

package engcom.mateus.apresentaodetestesunitarios.view.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun View.goneUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
