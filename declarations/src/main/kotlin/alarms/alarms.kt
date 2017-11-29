package alarms

import kotlin.js.Promise

external class Alarm {
  /**
   * Name of this alarm.
   */
  val name: String

  /**
   * Time when the alarm is scheduled to fire, in milliseconds past the epoch.
   */
  val scheduledTime: Any

  /**
   * When present, signals that the alarm triggers periodically after so many minutes.
   */
  val periodInMinutes: Any?
}

/**
 * Details about the alarm. The alarm first fires either at 'when' milliseconds past the epoch (if 'when' is provided), after 'delayInMinutes' minutes from the current time (if 'delayInMinutes' is provided instead), or after 'periodInMinutes' minutes from the current time (if only 'periodInMinutes' is provided). Users should never provide both 'when' and 'delayInMinutes'. If 'periodInMinutes' is provided, then the alarm recurs repeatedly after that many minutes.
 */
external class AlarmInfo {
  /**
   * Time when the alarm is scheduled to first fire, in milliseconds past the epoch.
   */
  val `when`: Any?

  /**
   * Number of minutes from the current time after which the alarm should first fire.
   */
  val delayInMinutes: Any?

  /**
   * Number of minutes after which the alarm should recur repeatedly.
   */
  val periodInMinutes: Any?
}

external class AlarmsNamespace {
  /**
   * Creates an alarm. After the delay is expired, the onAlarm event is fired. If there is another alarm with the same name (or no name if none is specified), it will be cancelled and replaced by this alarm.
   */
  fun create(name: String?, alarmInfo: AlarmInfo)

  /**
   * Retrieves details about the specified alarm.
   */
  fun get(name: String?): Promise<Alarm>

  /**
   * Gets an array of all the alarms.
   */
  fun getAll(): Promise<Array<Alarm>>

  /**
   * Clears the alarm with the given name.
   */
  fun clear(name: String?): Promise<Boolean>

  /**
   * Clears all alarms.
   */
  fun clearAll(): Promise<Boolean>
}
