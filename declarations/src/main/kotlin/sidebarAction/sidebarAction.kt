package sidebarAction

import kotlin.Any
import kotlin.js.Promise

typealias ImageDataType = Any

class Details(/**
 * The string the sidebar action should display when moused over.
 */
val title: String, /**
 * Sets the sidebar title for the tab specified by tabId. Automatically resets when the tab is closed.
 */
val tabId: Int?)

class Details2(/**
 * Specify the tab to get the title from. If no tab is specified, the non-tab-specific title is returned.
 */
val tabId: Int?)

class Details3(
    /**
     * Either an ImageData object or a dictionary {size -> ImageData} representing icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.imageData = foo' is equivalent to 'details.imageData = {'19': foo}'
     */
    val imageData: Any?,
    /**
     * Either a relative image path or a dictionary {size -> relative image path} pointing to icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.path = foo' is equivalent to 'details.imageData = {'19': foo}'
     */
    val path: Any?,
    /**
     * Sets the sidebar icon for the tab specified by tabId. Automatically resets when the tab is closed.
     */
    val tabId: Int?
)

class Details4(/**
 * Sets the sidebar url for the tab specified by tabId. Automatically resets when the tab is closed.
 */
val tabId: Int?, /**
 * The url to the html file to show in a sidebar.  If set to the empty string (''), no sidebar is shown.
 */
val panel: String)

class Details5(/**
 * Specify the tab to get the sidebar from. If no tab is specified, the non-tab-specific sidebar is returned.
 */
val tabId: Int?)

external class SidebarActionNamespace {
  /**
   * Sets the title of the sidebar action. This shows up in the tooltip.
   */
  fun setTitle(details: Details): Promise<Any>

  /**
   * Gets the title of the sidebar action.
   */
  fun getTitle(details: Details2): Promise<Any>

  /**
   * Sets the icon for the sidebar action. The icon can be specified either as the path to an image file or as the pixel data from a canvas element, or as dictionary of either one of those. Either the <strong>path</strong> or the <strong>imageData</strong> property must be specified.
   */
  fun setIcon(details: Details3): Promise<Any>

  /**
   * Sets the url to the html document to be opened in the sidebar when the user clicks on the sidebar action's icon.
   */
  fun setPanel(details: Details4): Promise<Any>

  /**
   * Gets the url to the html document set as the panel for this sidebar action.
   */
  fun getPanel(details: Details5): Promise<Any>

  /**
   * Opens the extension sidebar in the active window.
   */
  fun open(): Promise<Any>

  /**
   * Closes the extension sidebar in the active window if the sidebar belongs to the extension.
   */
  fun close(): Promise<Any>
}
