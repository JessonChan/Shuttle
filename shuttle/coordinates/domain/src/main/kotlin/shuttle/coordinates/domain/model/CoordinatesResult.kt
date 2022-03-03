package shuttle.coordinates.domain.model

import arrow.core.Either
import com.soywiz.klock.Time
import shuttle.coordinates.domain.error.LocationNotAvailable

data class CoordinatesResult(
    val location: Either<LocationNotAvailable, Location>,
    val time: Time
)

fun <C> CoordinatesResult.fold(ifLeft: (LocationNotAvailable) -> C, ifRight: (Coordinates) -> C): C =
    location.fold(ifLeft, ifRight = { ifRight(Coordinates(location = it, time = time)) })