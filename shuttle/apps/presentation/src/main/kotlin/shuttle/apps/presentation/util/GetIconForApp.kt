package shuttle.apps.presentation.util

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import shuttle.apps.domain.model.AppId

class GetIconForApp(
    private val packageManager: PackageManager
) {

    private val cache = mutableMapOf<AppId, Drawable>()

    operator fun invoke(id: AppId): Drawable =
        cache.getOrPut(id) {
            packageManager.getApplicationIcon(id.value)
        }
}
