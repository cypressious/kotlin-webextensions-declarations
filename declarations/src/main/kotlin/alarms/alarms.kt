package alarms

import kotlin.js.Promise
import webextensions.Event

/**
 * @param name Name of this alarm.
 * @param scheduledTime Time when the alarm is scheduled to fire, in milliseconds past the epoch.
 * @param periodInMinutes When present, signals that the alarm triggers periodically after so many
        minutes.
 */
class Alarm(
    var name: String,
    var scheduledTime: Float,
    var periodInMinutes: Float? = null
)

/**
 * Details about the alarm. The alarm first fires either at 'when' milliseconds past the epoch (if
        'when' is provided), after 'delayInMinutes' minutes from the current time (if
        'delayInMinutes' is provided instead), or after 'periodInMinutes' minutes from the current
        time (if only 'periodInMinutes' is provided). Users should never provide both 'when' and
        'delayInMinutes'. If 'periodInMinutes' is provided, then the alarm recurs repeatedly after
        that many minutes.
 * @param when Time when the alarm is scheduled to first fire, in milliseconds past the epoch.
 * @param delayInMinutes Number of minutes from the current time after which the alarm should first
        fire.
 * @param periodInMinutes Number of minutes after which the alarm should recur repeatedly.
 */
class AlarmInfo(
    var `when`: Float? = null,
    var delayInMinutes: Float? = null,
    var periodInMinutes: Float? = null
)

external class AlarmsNamespace {
    /**
     * Fired when an alarm has expired. Useful for transient background pages.
     *
     * @param name The alarm that has expired. */
    val onAlarm: Event<(name: Alarm) -> Unit>

    /**
     * Creates an alarm. After the delay is expired, the onAlarm event is fired. If there is another
            alarm with the same name (or no name if none is specified), it will be cancelled and
            replaced by this alarm.
     */
    fun create(name: String? = definedExternally, alarmInfo: AlarmInfo)

    /**
     * Retrieves details about the specified alarm.
     */
    fun get(name: String? = definedExternally): Promise<Alarm?>

    /**
     * Gets an array of all the alarms.
     */
    fun getAll(): Promise<Array<Alarm>>

    /**
     * Clears the alarm with the given name.
     */
    fun clear(name: String? = definedExternally): Promise<Boolean>

    /**
     * Clears all alarms.
     */
    fun clearAll(): Promise<Boolean>
}
