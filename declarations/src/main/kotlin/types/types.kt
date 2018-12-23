package types

import kotlin.js.Promise

/**
 * Which setting to consider.
 * @param incognito Whether to return the value that applies to the incognito session (default
        false).
 */
class Details(
    var incognito: Boolean? = null
)

/**
 * Details of the currently effective value.
 * @param value The value of the setting.
 * @param levelOfControl The level of control of the setting.
 * @param incognitoSpecific Whether the effective value is specific to the incognito
        session.<br/>This property will <em>only</em> be present if the <var>incognito</var>
        property in the <var>details</var> parameter of <code>get()</code> was true.
 */
class Details2(
    var value: dynamic,
    var levelOfControl: LevelOfControl,
    var incognitoSpecific: Boolean? = null
)

/**
 * Which setting to change.
 * @param value The value of the setting. <br/>Note that every setting has a specific value type,
        which is described together with the setting. An extension should <em>not</em> set a value
        of a different type.
 * @param scope Where to set the setting (default: regular).
 */
class Details3(
    var value: dynamic,
    var scope: SettingScope? = null
)

/**
 * Which setting to clear.
 * @param scope Where to clear the setting (default: regular).
 */
class Details4(
    var scope: SettingScope? = null
)

/**
 * The scope of the Setting. One of<ul><li><var>regular</var>: setting for the regular profile
        (which is inherited by the incognito profile if not overridden
        elsewhere),</li><li><var>regular_only</var>: setting for the regular profile only (not
        inherited by the incognito profile),</li><li><var>incognito_persistent</var>: setting for
        the incognito profile that survives browser restarts (overrides regular
        preferences),</li><li><var>incognito_session_only</var>: setting for the incognito profile
        that can only be set during an incognito session and is deleted when the incognito session
        ends (overrides regular and incognito_persistent preferences).</li></ul> Only
        <var>regular</var> is supported by Firefox at this time. */
typealias SettingScope = String

/**
 * One of<ul><li><var>not_controllable</var>: cannot be controlled by any
        extension</li><li><var>controlled_by_other_extensions</var>: controlled by extensions with
        higher precedence</li><li><var>controllable_by_this_extension</var>: can be controlled by
        this extension</li><li><var>controlled_by_this_extension</var>: controlled by this
        extension</li></ul> */
typealias LevelOfControl = String

external class Setting {
    /**
     * Gets the value of a setting.
     */
    fun get(details: Details): Promise<Details2>

    /**
     * Sets the value of a setting.
     */
    fun set(details: Details3): Promise<Any>

    /**
     * Clears the setting, restoring any default value.
     */
    fun clear(details: Details4): Promise<Any>
}

external class TypesNamespace
