package theme

import kotlin.js.Promise
import manifest.ThemeType

/**
 * Info provided in the onUpdated listener.
 */
external class ThemeUpdateInfo {
  /**
   * The new theme after update
   */
  val theme: Theme

  /**
   * The id of the window the theme has been applied to
   */
  val windowId: Int
}

external class Theme {
  /**
   * Returns the current theme for the specified window or the last focused window.
   */
  fun getCurrent(windowId: Int): Promise<Any>

  /**
   * Make complete updates to the theme. Resolves when the update has completed.
   */
  fun update(windowId: Int, details: ThemeType): Promise<Any>

  /**
   * Removes the updates made to the theme.
   */
  fun reset(windowId: Int): Promise<Any>
}
