package menusInternal

/**
 * Information sent when a context menu item is clicked.
 * @param menuItemId The ID of the menu item that was clicked.
 * @param parentMenuItemId The parent ID, if any, for the item clicked.
 * @param mediaType One of 'image', 'video', or 'audio' if the context menu was activated on one of these types of elements.
 * @param linkUrl If the element is a link, the URL it points to.
 * @param srcUrl Will be present for elements with a 'src' URL.
 * @param pageUrl The URL of the page where the menu item was clicked. This property is not set if the click occured in a context where there is no current page, such as in a launcher context menu.
 * @param frameUrl  The URL of the frame of the element where the context menu was clicked, if it was in a frame.
 * @param selectionText The text for the context selection, if any.
 * @param editable A flag indicating whether the element is editable (text input, textarea, etc.).
 * @param wasChecked A flag indicating the state of a checkbox or radio item before it was clicked.
 * @param checked A flag indicating the state of a checkbox or radio item after it is clicked.
 */
class OnClickData(
    var menuItemId: MenuItemId,
    var parentMenuItemId: ParentMenuItemId? = null,
    var mediaType: String? = null,
    var linkUrl: String? = null,
    var srcUrl: String? = null,
    var pageUrl: String? = null,
    var frameUrl: String? = null,
    var selectionText: String? = null,
    var editable: Boolean,
    var wasChecked: Boolean? = null,
    var checked: Boolean? = null
)

/**
 * The ID of the menu item that was clicked. */
typealias MenuItemId = Any

/**
 * The parent ID, if any, for the item clicked. */
typealias ParentMenuItemId = Any

external class MenusInternalNamespace
