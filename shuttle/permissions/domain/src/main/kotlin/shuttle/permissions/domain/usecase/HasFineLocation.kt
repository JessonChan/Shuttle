package shuttle.permissions.domain.usecase

import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import org.koin.core.annotation.Factory
import shuttle.permissions.domain.model.FineLocation

@Factory
class HasFineLocation {

    operator fun invoke(state: MultiplePermissionsState) =
        state.permissions.any { it.permission == FineLocation && it.status == PermissionStatus.Granted }
}
