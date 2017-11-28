package browserAction

import kotlin.Any
import kotlin.js.Promise

class SetTitleDetails(/**
 * The string the browser action should display when moused over.
 */
val title: String, /**
 * Limits the change to when a particular tab is selected. Automatically resets when the tab is closed.
 */
val tabId: Int)

class GetTitleDetails(/**
 * Specify the tab to get the title from. If no tab is specified, the non-tab-specific title is returned.
 */
val tabId: Int)

class SetIconDetails(
    /**
     * Either an ImageData object or a dictionary {size -> ImageData} representing icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.imageData = foo' is equivalent to 'details.imageData = {'19': foo}'
     */
    val imageData: Any,
    /**
     * Either a relative image path or a dictionary {size -> relative image path} pointing to icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.path = foo' is equivalent to 'details.imageData = {'19': foo}'
     */
    val path: Any,
    /**
     * Limits the change to when a particular tab is selected. Automatically resets when the tab is closed.
     */
    val tabId: Int
)

class SetPopupDetails(/**
 * Limits the change to when a particular tab is selected. Automatically resets when the tab is closed.
 */
val tabId: Int, /**
 * The html file to show in a popup.  If set to the empty string (''), no popup is shown.
 */
val popup: String)

class GetPopupDetails(/**
 * Specify the tab to get the popup from. If no tab is specified, the non-tab-specific popup is returned.
 */
val tabId: Int)

class SetBadgeTextDetails(/**
 * Any number of characters can be passed, but only about four can fit in the space.
 */
val text: String, /**
 * Limits the change to when a particular tab is selected. Automatically resets when the tab is closed.
 */
val tabId: Int)

class GetBadgeTextDetails(/**
 * Specify the tab to get the badge text from. If no tab is specified, the non-tab-specific badge text is returned.
 */
val tabId: Int)

class SetBadgeBackgroundColorDetails(/**
 * An array of four integers in the range [0,255] that make up the RGBA color of the badge. For example, opaque red is <code>[255, 0, 0, 255]</code>. Can also be a string with a CSS value, with opaque red being <code>#FF0000</code> or <code>#F00</code>.
 */
val color: Any, /**
 * Limits the change to when a particular tab is selected. Automatically resets when the tab is closed.
 */
val tabId: Int)

class GetBadgeBackgroundColorDetails(/**
 * Specify the tab to get the badge background color from. If no tab is specified, the non-tab-specific badge background color is returned.
 */
val tabId: Int)

typealias ColorArray = Any

typealias ImageDataType = Any

external class BrowserAction {
  /**
   * Sets the title of the browser action. This shows up in the tooltip.
   */
  fun setTitle(details: SetTitleDetails): Promise<Any>

  /**
   * Gets the title of the browser action.
   */
  fun getTitle(details: GetTitleDetails): Promise<String>

  /**
   * Sets the icon for the browser action. The icon can be specified either as the path to an image file or as the pixel data from a canvas element, or as dictionary of either one of those. Either the <b>path</b> or the <b>imageData</b> property must be specified.
   */
  fun setIcon(details: SetIconDetails): Promise<Any>

  /**
   * Sets the html document to be opened as a popup when the user clicks on the browser action's icon.
   */
  fun setPopup(details: SetPopupDetails): Promise<Any>

  /**
   * Gets the html document set as the popup for this browser action.
   */
  fun getPopup(details: GetPopupDetails): Promise<String>

  /**
   * Sets the badge text for the browser action. The badge is displayed on top of the icon.
   */
  fun setBadgeText(details: SetBadgeTextDetails): Promise<Any>

  /**
   * Gets the badge text of the browser action. If no tab is specified, the non-tab-specific badge text is returned.
   */
  fun getBadgeText(details: GetBadgeTextDetails): Promise<String>

  /**
   * Sets the background color for the badge.
   */
  fun setBadgeBackgroundColor(details: SetBadgeBackgroundColorDetails): Promise<Any>

  /**
   * Gets the background color of the browser action.
   */
  fun getBadgeBackgroundColor(details: GetBadgeBackgroundColorDetails): Promise<ColorArray>

  /**
   * Enables the browser action for a tab. By default, browser actions are enabled.
   */
  fun enable(tabId: Int): Promise<Any>

  /**
   * Disables the browser action for a tab.
   */
  fun disable(tabId: Int): Promise<Any>

  /**
   * Opens the extension popup window in the active window.
   */
  fun openPopup(): Promise<Any>
}
