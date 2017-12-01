package idle

import kotlin.js.Promise

typealias IdleState = String

external class IdleNamespace {
    /**
     * Returns "idle" if the user has not generated any input for a specified number of seconds, or "active" otherwise.
     */
    fun queryState(detectionIntervalInSeconds: Int): Promise<IdleState>

    /**
     * Sets the interval, in seconds, used to determine when the system is in an idle state for onStateChanged events. The default interval is 60 seconds.
     */
    fun setDetectionInterval(intervalInSeconds: Int)
}
