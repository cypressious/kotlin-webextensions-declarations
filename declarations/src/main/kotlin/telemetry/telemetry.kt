package telemetry

import kotlin.Suppress
import kotlin.js.Promise

/**
 * Type of scalar: 'count' for numeric values, 'string' for string values, 'boolean' for boolean values. Maps to <code>nsITelemetry.SCALAR_TYPE_*</code>. */
typealias ScalarType = String

/**
 * Represents registration data for a Telemetry scalar.
 * @param keyed True if this is a keyed scalar.
 * @param record_on_release True if this data should be recorded on release.
 * @param expired True if this scalar entry is expired. This allows recording it without error, but it will be discarded.
 */
class ScalarData(
    var kind: ScalarType,
    var keyed: Boolean? = null,
    var record_on_release: Boolean? = null,
    var expired: Boolean? = null
)

/**
 * Represents registration data for a Telemetry event.
 * @param methods List of methods for this event entry.
 * @param objects List of objects for this event entry.
 * @param extra_keys List of allowed extra keys for this event entry.
 * @param record_on_release True if this data should be recorded on release.
 * @param expired True if this event entry is expired. This allows recording it without error, but it will be discarded.
 */
class EventData(
    var methods: Array<String>,
    var objects: Array<String>,
    var extra_keys: Array<String>,
    var record_on_release: Boolean? = null,
    var expired: Boolean? = null
)

/**
 * The data payload for the ping.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Message() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * Set to override the environment data.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class OverrideEnvironment() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * Options object.
 * @param addClientId True if the ping should contain the client id.
 * @param addEnvironment True if the ping should contain the environment data.
 * @param overrideEnvironment Set to override the environment data.
 * @param usePingSender If true, send the ping using the PingSender.
 */
class Options(
    var addClientId: Boolean? = null,
    var addEnvironment: Boolean? = null,
    var overrideEnvironment: OverrideEnvironment? = null,
    var usePingSender: Boolean? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Value() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * The value to set the scalar to */
typealias Value2 = Any

/**
 * An optional object of the form (string -> string). It should only contain registered extra keys.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Extra() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

/**
 * An object that contains registration data for multiple scalars. Each property name is the scalar name, and the corresponding property value is an object of ScalarData type.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Data() {
    inline operator fun get(key: String): ScalarData = asDynamic()[key]
    inline operator fun set(key: String, value: ScalarData) {
        asDynamic()[key] = value
    }
}

/**
 * An object that contains registration data for 1+ events. Each property name is the category name, and the corresponding property value is an object of EventData type.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Data2() {
    inline operator fun get(key: String): EventData = asDynamic()[key]
    inline operator fun set(key: String, value: EventData) {
        asDynamic()[key] = value
    }
}

external class TelemetryNamespace {
    /**
     * Submits a custom ping to the Telemetry back-end. See <code>submitExternalPing</code> inside TelemetryController.jsm for more details.
     */
    fun submitPing(
        type: String,
        message: Message,
        options: Options
    ): Promise<Any>

    /**
     * Checks if Telemetry is enabled.
     */
    fun canUpload(): Promise<Any>

    /**
     * Adds the value to the given scalar.
     */
    fun scalarAdd(name: String, value: Int): Promise<Any>

    /**
     * Sets the named scalar to the given value. Throws if the value type doesn't match the scalar type.
     */
    fun scalarSet(name: String, value: String): Promise<Any>

    /**
     * Sets the named scalar to the given value. Throws if the value type doesn't match the scalar type.
     */
    fun scalarSet(name: String, value: Boolean): Promise<Any>

    /**
     * Sets the named scalar to the given value. Throws if the value type doesn't match the scalar type.
     */
    fun scalarSet(name: String, value: Int): Promise<Any>

    /**
     * Sets the named scalar to the given value. Throws if the value type doesn't match the scalar type.
     */
    fun scalarSet(name: String, value: Value): Promise<Any>

    /**
     * Sets the scalar to the maximum of the current and the passed value
     */
    fun scalarSetMaximum(name: String, value: Int): Promise<Any>

    /**
     * Record an event in Telemetry. Throws when trying to record an unknown event.
     */
    fun recordEvent(
        category: String,
        method: String,
        `object`: String,
        value: Int? = definedExternally,
        extra: Extra? = definedExternally
    ): Promise<Any>

    /**
     * Register new scalars to record them from addons. See nsITelemetry.idl for more details.
     */
    fun registerScalars(category: String, data: Data): Promise<Any>

    /**
     * Register new events to record them from addons. See nsITelemetry.idl for more details.
     */
    fun registerEvents(category: String, data: Data2): Promise<Any>

    /**
     * Enable recording of events in a category. Events default to recording disabled. This allows to toggle recording for all events in the specified category.
     */
    fun setEventRecordingEnabled(category: String, enabled: Boolean): Promise<Any>
}
