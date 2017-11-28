package test

import kotlin.js.Promise

external class ExpectedError

external class Promise

external class Test {
  /**
   * Notifies the browser process that test code running in the extension failed.  This is only used for internal unit testing.
   */
  fun notifyFail(message: String)

  /**
   * Notifies the browser process that test code running in the extension passed.  This is only used for internal unit testing.
   */
  fun notifyPass(message: String)

  /**
   * Logs a message during internal unit testing.
   */
  fun log(message: String)

  /**
   * Sends a string message to the browser process, generating a Notification that C++ test code can wait for.
   */
  fun sendMessage(arg1: Any, arg2: Any)

  fun fail(message: Any)

  fun succeed(message: Any)

  fun assertTrue(test: Any, message: String)

  fun assertFalse(test: Any, message: String)

  fun assertBool(
      test: String,
      expected: Boolean,
      message: String
  )

  fun assertBool(
      test: Boolean,
      expected: Boolean,
      message: String
  )

  fun checkDeepEq(expected: Any, actual: Any)

  fun assertEq(
      expected: Any,
      actual: Any,
      message: String
  )

  fun assertNoLastError()

  fun assertLastError(expectedError: String)

  fun assertRejects(
      promise: Promise,
      expectedError: ExpectedError,
      message: String
  ): Promise<Any>

  fun assertThrows(
      func: Any,
      expectedError: ExpectedError,
      message: String
  )
}
