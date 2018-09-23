package sidebarAction

import kotlin.Suppress
import kotlin.js.Promise

/**
 * Pixel data for an image. Must be an ImageData object (for example, from a <code>canvas</code> element).
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ImageDataType() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * The string the sidebar action should display when moused over. */
typealias Title = Any

/**
 * @param title The string the sidebar action should display when moused over.
 * @param tabId Sets the sidebar title for the tab specified by tabId. Automatically resets when the tab is closed.
 * @param windowId Sets the sidebar title for the window specified by windowId.
 */
class Details(
    var title: Title,
    var tabId: Int? = null,
    var windowId: Int? = null
)

/**
 * @param tabId Specify the tab to get the title from. If no tab nor window is specified, the global title is returned.
 * @param windowId Specify the window to get the title from. If no tab nor window is specified, the global title is returned.
 */
class Details2(
    var tabId: Int? = null,
    var windowId: Int? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ImageData() {
    inline operator fun get(key: String): ImageDataType = asDynamic()[key]
    inline operator fun set(key: String, value: ImageDataType) {
        asDynamic()[key] = value
    }
}

/**
 * Either an ImageData object or a dictionary {size -> ImageData} representing icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.imageData = foo' is equivalent to 'details.imageData = {'19': foo}' */
typealias ImageData2 = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Path() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

/**
 * Either a relative image path or a dictionary {size -> relative image path} pointing to icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.path = foo' is equivalent to 'details.imageData = {'19': foo}' */
typealias Path2 = Any

/**
 * @param imageData Either an ImageData object or a dictionary {size -> ImageData} representing icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.imageData = foo' is equivalent to 'details.imageData = {'19': foo}'
 * @param path Either a relative image path or a dictionary {size -> relative image path} pointing to icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.path = foo' is equivalent to 'details.imageData = {'19': foo}'
 * @param tabId Sets the sidebar icon for the tab specified by tabId. Automatically resets when the tab is closed.
 * @param windowId Sets the sidebar icon for the window specified by windowId.
 */
class Details3(
    var imageData: ImageData2? = null,
    var path: Path2? = null,
    var tabId: Int? = null,
    var windowId: Int? = null
)

/**
 * The url to the html file to show in a sidebar.  If set to the empty string (''), no sidebar is shown. */
typealias Panel = Any

/**
 * @param tabId Sets the sidebar url for the tab specified by tabId. Automatically resets when the tab is closed.
 * @param windowId Sets the sidebar url for the window specified by windowId.
 * @param panel The url to the html file to show in a sidebar.  If set to the empty string (''), no sidebar is shown.
 */
class Details4(
    var tabId: Int? = null,
    var windowId: Int? = null,
    var panel: Panel
)

/**
 * @param tabId Specify the tab to get the panel from. If no tab nor window is specified, the global panel is returned.
 * @param windowId Specify the window to get the panel from. If no tab nor window is specified, the global panel is returned.
 */
class Details5(
    var tabId: Int? = null,
    var windowId: Int? = null
)

/**
 * @param windowId Specify the window to get the openness from.
 */
class Details6(
    var windowId: Int? = null
)

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

    /**
     * Checks whether the sidebar action is open.
     */
    fun isOpen(details: Details6): Promise<Any>
}
