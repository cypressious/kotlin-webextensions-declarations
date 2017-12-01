package test

import browser.Event
import kotlin.js.Promise

typealias ExpectedError = Any

typealias Promise = Any

typealias Test = Any

external class TestNamespace {
    val onMessage: Event<(message: String, argument: Any) -> Unit>

    /**
     * Notifies the browser process that test code running in the extension failed.  This is only used for internal unit testing.
     */
    fun notifyFail(message: String)

    /**
     * Notifies the browser process that test code running in the extension passed.  This is only used for internal unit testing.
     */
    fun notifyPass(message: String? = definedExternally)

    /**
     * Logs a message during internal unit testing.
     */
    fun log(message: String)

    /**
     * Sends a string message to the browser process, generating a Notification that C++ test code can wait for.
     */
    fun sendMessage(arg1: Any? = definedExternally, arg2: Any? = definedExternally)

    fun fail(message: Any? = definedExternally)

    fun succeed(message: Any? = definedExternally)

    fun assertTrue(test: Any? = definedExternally, message: String? = definedExternally)

    fun assertFalse(test: Any? = definedExternally, message: String? = definedExternally)

    fun assertEq(
            expected: Any? = definedExternally,
            actual: Any? = definedExternally,
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
