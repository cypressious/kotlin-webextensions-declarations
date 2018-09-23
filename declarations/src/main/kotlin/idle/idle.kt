package idle

import kotlin.js.Promise
import webextensions.Event

typealias IdleState = String

external class IdleNamespace {
    /**
     * Fired when the system changes to an active or idle state. The event fires with "idle" if the the user has not generated any input for a specified number of seconds, and "active" when the user generates input on an idle system.
     *
     * @param newState null */
    val onStateChanged: Event<(newState: IdleState) -> Unit>

    /**
     * Returns "idle" if the user has not generated any input for a specified number of seconds, or "active" otherwise.
     */
    fun queryState(detectionIntervalInSeconds: Int): Promise<IdleState>

    /**
     * Sets the interval, in seconds, used to determine when the system is in an idle state for onStateChanged events. The default interval is 60 seconds.
     */
    fun setDetectionInterval(intervalInSeconds: Int)
}
