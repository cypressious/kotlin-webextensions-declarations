package test

import kotlin.js.Promise
import webextensions.Event

typealias ExpectedError = Any

typealias Promise = Any

typealias Test = Any

external class TestNamespace {
    /**
     * Used to test sending messages to extensions.
     *
     * @param message null
     * @param argument null */
    val onMessage: Event<(message: String, argument: dynamic) -> Unit>

    /**
     * Notifies the browser process that test code running in the extension failed.  This is only
            used for internal unit testing.
     */
    fun notifyFail(message: String)

    /**
     * Notifies the browser process that test code running in the extension passed.  This is only
            used for internal unit testing.
     */
    fun notifyPass(message: String? = definedExternally)

    /**
     * Logs a message during internal unit testing.
     */
    fun log(message: String)

    /**
     * Sends a string message to the browser process, generating a Notification that C++ test code
            can wait for.
     */
    fun sendMessage(arg1: dynamic = definedExternally, arg2: dynamic = definedExternally)

    fun fail(message: dynamic = definedExternally)

    fun succeed(message: dynamic = definedExternally)

    fun assertTrue(test: dynamic = definedExternally, message: String? = definedExternally)

    fun assertFalse(test: dynamic = definedExternally, message: String? = definedExternally)

    fun assertEq(
        expected: dynamic = definedExternally,
        actual: dynamic = definedExternally,
        message: String? = definedExternally
    )

    fun assertRejects(
        promise: Promise<Any?>,
        expectedError: ExpectedError? = definedExternally,
        message: String? = definedExternally
    ): Promise<Any>

    fun assertThrows(
        func: () -> Unit,
        expectedError: ExpectedError? = definedExternally,
        message: String? = definedExternally
    )
}
