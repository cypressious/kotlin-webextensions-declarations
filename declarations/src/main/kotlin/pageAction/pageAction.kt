package pageAction

import kotlin.Suppress
import kotlin.js.Promise
import tabs.Tab
import webextensions.Event

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
 * @param tabId Specify the tab to get the shownness from.
 */
class Details(
    var tabId: Int
)

/**
 * The tooltip string. */
typealias Title = Any

/**
 * @param tabId The id of the tab for which you want to modify the page action.
 * @param title The tooltip string.
 */
class Details2(
    var tabId: Int,
    var title: Title
)

/**
 * @param tabId Specify the tab to get the title from.
 */
class Details3(
    var tabId: Int
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
 * @param tabId The id of the tab for which you want to modify the page action.
 * @param imageData Either an ImageData object or a dictionary {size -> ImageData} representing icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.imageData = foo' is equivalent to 'details.imageData = {'19': foo}'
 * @param path Either a relative image path or a dictionary {size -> relative image path} pointing to icon to be set. If the icon is specified as a dictionary, the actual image to be used is chosen depending on screen's pixel density. If the number of image pixels that fit into one screen space unit equals <code>scale</code>, then image with size <code>scale</code> * 19 will be selected. Initially only scales 1 and 2 will be supported. At least one image must be specified. Note that 'details.path = foo' is equivalent to 'details.imageData = {'19': foo}'
 */
class Details4(
    var tabId: Int,
    var imageData: ImageData2? = null,
    var path: Path2? = null
)

/**
 * The html file to show in a popup.  If set to the empty string (''), no popup is shown. */
typealias Popup = Any

/**
 * @param tabId The id of the tab for which you want to modify the page action.
 * @param popup The html file to show in a popup.  If set to the empty string (''), no popup is shown.
 */
class Details5(
    var tabId: Int,
    var popup: Popup
)

/**
 * @param tabId Specify the tab to get the popup from.
 */
class Details6(
    var tabId: Int
)

external class PageActionNamespace {
    /**
     * Fired when a page action icon is clicked.  This event will not fire if the page action has a popup.
     *
     * @param tab null */
    val onClicked: Event<(tab: Tab) -> Unit>

    /**
     * Shows the page action. The page action is shown whenever the tab is selected.
     */
    fun show(tabId: Int): Promise<Any>

    /**
     * Hides the page action.
     */
    fun hide(tabId: Int): Promise<Any>

    /**
     * Checks whether the page action is shown.
     */
    fun isShown(details: Details): Promise<Any>

    /**
     * Sets the title of the page action. This is displayed in a tooltip over the page action.
     */
    fun setTitle(details: Details2)

    /**
     * Gets the title of the page action.
     */
    fun getTitle(details: Details3): Promise<String>

    /**
     * Sets the icon for the page action. The icon can be specified either as the path to an image file or as the pixel data from a canvas element, or as dictionary of either one of those. Either the <b>path</b> or the <b>imageData</b> property must be specified.
     */
    fun setIcon(details: Details4): Promise<Any>

    /**
     * Sets the html document to be opened as a popup when the user clicks on the page action's icon.
     */
    fun setPopup(details: Details5): Promise<Any>

    /**
     * Gets the html document set as the popup for this page action.
     */
    fun getPopup(details: Details6): Promise<String>

    /**
     * Opens the extension page action in the active window.
     */
    fun openPopup(): Promise<Any>
}
