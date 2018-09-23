package browserAction

import kotlin.Suppress
import kotlin.js.Promise
import tabs.Tab
import webextensions.Event

/**
 * Specifies to which tab or window the value should be set, or from which one it should be retrieved. If no tab nor window is specified, the global value is set or retrieved.
 * @param tabId When setting a value, it will be specific to the specified tab, and will automatically reset when the tab navigates. When getting, specifies the tab to get the value from; if there is no tab-specific value, the window one will be inherited.
 * @param windowId When setting a value, it will be specific to the specified window. When getting, specifies the window to get the value from; if there is no window-specific value, the global one will be inherited.
 */
class Details(
    var tabId: Int? = null,
    var windowId: Int? = null
)

typealias ColorArray = Array<Int>

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
 * An array of four integers in the range [0,255] that make up the RGBA color of the badge. For example, opaque red is <code>[255, 0, 0, 255]</code>. Can also be a string with a CSS value, with opaque red being <code>#FF0000</code> or <code>#F00</code>. */
typealias ColorValue = Any

/**
 * The string the browser action should display when moused over. */
typealias Title = Any

/**
 * @param title The string the browser action should display when moused over.
 */
class Details2(
    var title: Title
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
 */
class Details3(
    var imageData: ImageData2? = null,
    var path: Path2? = null
)

/**
 * The html file to show in a popup.  If set to the empty string (''), no popup is shown. */
typealias Popup = Any

/**
 * @param popup The html file to show in a popup.  If set to the empty string (''), no popup is shown.
 */
class Details4(
    var popup: Popup
)

/**
 * Any number of characters can be passed, but only about four can fit in the space. */
typealias Text = Any

/**
 * @param text Any number of characters can be passed, but only about four can fit in the space.
 */
class Details5(
    var text: Text
)

class Details6(var color: ColorValue)

class Details7(var color: ColorValue)

external class BrowserActionNamespace {
    /**
     * Fired when a browser action icon is clicked.  This event will not fire if the browser action has a popup.
     *
     * @param tab null */
    val onClicked: Event<(tab: Tab) -> Unit>

    /**
     * Sets the title of the browser action. This shows up in the tooltip.
     */
    fun setTitle(details: Details2): Promise<Any>

    /**
     * Gets the title of the browser action.
     */
    fun getTitle(details: Details): Promise<String>

    /**
     * Sets the icon for the browser action. The icon can be specified either as the path to an image file or as the pixel data from a canvas element, or as dictionary of either one of those. Either the <b>path</b> or the <b>imageData</b> property must be specified.
     */
    fun setIcon(details: Details3): Promise<Any>

    /**
     * Sets the html document to be opened as a popup when the user clicks on the browser action's icon.
     */
    fun setPopup(details: Details4): Promise<Any>

    /**
     * Gets the html document set as the popup for this browser action.
     */
    fun getPopup(details: Details): Promise<String>

    /**
     * Sets the badge text for the browser action. The badge is displayed on top of the icon.
     */
    fun setBadgeText(details: Details5): Promise<Any>

    /**
     * Gets the badge text of the browser action. If no tab nor window is specified is specified, the global badge text is returned.
     */
    fun getBadgeText(details: Details): Promise<String>

    /**
     * Sets the background color for the badge.
     */
    fun setBadgeBackgroundColor(details: Details6): Promise<Any>

    /**
     * Gets the background color of the browser action badge.
     */
    fun getBadgeBackgroundColor(details: Details): Promise<ColorArray>

    /**
     * Sets the text color for the badge.
     */
    fun setBadgeTextColor(details: Details7): Promise<Any>

    /**
     * Gets the text color of the browser action badge.
     */
    fun getBadgeTextColor(details: Details): Promise<Any>

    /**
     * Enables the browser action for a tab. By default, browser actions are enabled.
     */
    fun enable(tabId: Int? = definedExternally): Promise<Any>

    /**
     * Disables the browser action for a tab.
     */
    fun disable(tabId: Int? = definedExternally): Promise<Any>

    /**
     * Checks whether the browser action is enabled.
     */
    fun isEnabled(details: Details): Promise<Any>

    /**
     * Opens the extension popup window in the active window.
     */
    fun openPopup(): Promise<Any>
}
