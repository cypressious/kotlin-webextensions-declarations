package menusInternal

/**
 * Information sent when a context menu item is clicked.
 */
class OnClickData(
        /**
         * The ID of the menu item that was clicked.
         */
        var menuItemId: MenuItemId,
        /**
         * The parent ID, if any, for the item clicked.
         */
        var parentMenuItemId: ParentMenuItemId? = null,
        /**
         * One of 'image', 'video', or 'audio' if the context menu was activated on one of these types of elements.
         */
        var mediaType: String? = null,
        /**
         * If the element is a link, the URL it points to.
         */
        var linkUrl: String? = null,
        /**
         * Will be present for elements with a 'src' URL.
         */
        var srcUrl: String? = null,
        /**
         * The URL of the page where the menu item was clicked. This property is not set if the click occured in a context where there is no current page, such as in a launcher context menu.
         */
        var pageUrl: String? = null,
        /**
         *  The URL of the frame of the element where the context menu was clicked, if it was in a frame.
         */
        var frameUrl: String? = null,
        /**
         * The text for the context selection, if any.
         */
        var selectionText: String? = null,
        /**
         * A flag indicating whether the element is editable (text input, textarea, etc.).
         */
        var editable: Boolean,
        /**
         * A flag indicating the state of a checkbox or radio item before it was clicked.
         */
        var wasChecked: Boolean? = null,
        /**
         * A flag indicating the state of a checkbox or radio item after it is clicked.
         */
        var checked: Boolean? = null
)

/**
 * The ID of the menu item that was clicked. */
typealias MenuItemId = Any

/**
 * The parent ID, if any, for the item clicked. */
typealias ParentMenuItemId = Any

external class MenusInternalNamespace
