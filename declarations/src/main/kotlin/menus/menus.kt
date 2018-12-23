package menus

import extension.ViewType
import kotlin.Suppress
import kotlin.js.Promise
import tabs.Tab
import webextensions.Event

/**
 * The different contexts a menu can appear in. Specifying 'all' is equivalent to the combination of
        all other contexts except for 'tab' and 'tools_menu'. */
typealias ContextType = String

/**
 * The type of menu item. */
typealias ItemType = String

/**
 * Information sent when a context menu item is clicked.
 * @param menuItemId The ID of the menu item that was clicked.
 * @param parentMenuItemId The parent ID, if any, for the item clicked.
 * @param viewType The type of view where the menu is clicked. May be unset if the menu is not
        associated with a view.
 * @param mediaType One of 'image', 'video', or 'audio' if the context menu was activated on one of
        these types of elements.
 * @param linkText If the element is a link, the text of that link.
 * @param linkUrl If the element is a link, the URL it points to.
 * @param srcUrl Will be present for elements with a 'src' URL.
 * @param pageUrl The URL of the page where the menu item was clicked. This property is not set if
        the click occured in a context where there is no current page, such as in a launcher context
        menu.
 * @param frameId The id of the frame of the element where the context menu was clicked.
 * @param frameUrl  The URL of the frame of the element where the context menu was clicked, if it
        was in a frame.
 * @param selectionText The text for the context selection, if any.
 * @param editable A flag indicating whether the element is editable (text input, textarea, etc.).
 * @param wasChecked A flag indicating the state of a checkbox or radio item before it was clicked.
 * @param checked A flag indicating the state of a checkbox or radio item after it is clicked.
 * @param bookmarkId The id of the bookmark where the context menu was clicked, if it was on a
        bookmark.
 * @param modifiers An array of keyboard modifiers that were held while the menu item was clicked.
 * @param button An integer value of button by which menu item was clicked.
 * @param targetElementId An identifier of the clicked element, if any. Use menus.getTargetElement
        in the page to find the corresponding element.
 */
class OnClickData(
    var menuItemId: MenuItemId,
    var parentMenuItemId: ParentMenuItemId? = null,
    var viewType: ViewType? = null,
    var mediaType: String? = null,
    var linkText: String? = null,
    var linkUrl: String? = null,
    var srcUrl: String? = null,
    var pageUrl: String? = null,
    var frameId: Int? = null,
    var frameUrl: String? = null,
    var selectionText: String? = null,
    var editable: Boolean,
    var wasChecked: Boolean? = null,
    var checked: Boolean? = null,
    var bookmarkId: String,
    var modifiers: Array<String>,
    var button: Int? = null,
    var targetElementId: Int? = null
)

/**
 * The ID of the menu item that was clicked. */
typealias MenuItemId = Any

/**
 * The parent ID, if any, for the item clicked. */
typealias ParentMenuItemId = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

/**
 * The ID of a parent menu item; this makes the item a child of a previously added item. */
typealias ParentId = Any

/**
 * @param type The type of menu item. Defaults to 'normal' if not specified.
 * @param id The unique ID to assign to this item. Mandatory for event pages. Cannot be the same as
        another ID for this extension.
 * @param title The text to be displayed in the item; this is <em>required</em> unless
        <code>type</code> is 'separator'. When the context is 'selection', you can use
        <code>%s</code> within the string to show the selected text. For example, if this
        parameter's value is "Translate '%s' to Pig Latin" and the user selects the word "cool", the
        context menu item for the selection is "Translate 'cool' to Pig Latin".
 * @param checked The initial state of a checkbox or radio item: true for selected and false for
        unselected. Only one radio item can be selected at a time in a given group of radio items.
 * @param contexts List of contexts this menu item will appear in. Defaults to ['page'] if not
        specified.
 * @param viewTypes List of view types where the menu item will be shown. Defaults to any view,
        including those without a viewType.
 * @param visible Whether the item is visible in the menu.
 * @param onclick A function that will be called back when the menu item is clicked. Event pages
        cannot use this; instead, they should register a listener for $(ref:contextMenus.onClicked).
 * @param parentId The ID of a parent menu item; this makes the item a child of a previously added
        item.
 * @param documentUrlPatterns Lets you restrict the item to apply only to documents whose URL
        matches one of the given patterns. (This applies to frames as well.) For details on the
        format of a pattern, see $(topic:match_patterns)[Match Patterns].
 * @param targetUrlPatterns Similar to documentUrlPatterns, but lets you filter based on the src
        attribute of img/audio/video tags and the href of anchor tags.
 * @param enabled Whether this context menu item is enabled or disabled. Defaults to true.
 * @param command Specifies a command to issue for the context click.  Currently supports internal
        commands _execute_page_action, _execute_browser_action and _execute_sidebar_action.
 */
class CreateProperties(
    var type: ItemType? = null,
    var id: String? = null,
    var icons: Icons? = null,
    var title: String? = null,
    var checked: Boolean? = null,
    var contexts: Array<ContextType>? = null,
    var viewTypes: Array<ViewType>? = null,
    var visible: Boolean? = null,
    var onclick: (() -> Unit)? = null,
    var parentId: ParentId? = null,
    var documentUrlPatterns: Array<String>? = null,
    var targetUrlPatterns: Array<String>? = null,
    var enabled: Boolean? = null,
    var command: String? = null
)

/**
 * The ID of the newly created item. */
typealias CreateResult = Any

/**
 * The ID of the item to update. */
typealias Id = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons2() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

/**
 * Note: You cannot change an item to be a child of one of its own descendants. */
typealias ParentId2 = Any

/**
 * The properties to update. Accepts the same values as the create function.
 * @param visible Whether the item is visible in the menu.
 * @param parentId Note: You cannot change an item to be a child of one of its own descendants.
 */
class UpdateProperties(
    var type: ItemType? = null,
    var icons: Icons2,
    var title: String? = null,
    var checked: Boolean? = null,
    var contexts: Array<ContextType>? = null,
    var viewTypes: Array<ViewType>? = null,
    var visible: Boolean? = null,
    var onclick: () -> Unit,
    var parentId: ParentId2? = null,
    var documentUrlPatterns: Array<String>? = null,
    var targetUrlPatterns: Array<String>? = null,
    var enabled: Boolean? = null
)

/**
 * The ID of the context menu item to remove. */
typealias MenuItemId2 = Any

/**
 * @param showDefaults Whether to also include default menu items in the menu.
 * @param context ContextType to override, to allow menu items from other extensions in the menu.
        Currently only 'bookmark' and 'tab' are supported. showDefaults cannot be used with this
        option.
 * @param bookmarkId Required when context is 'bookmark'. Requires 'bookmark' permission.
 * @param tabId Required when context is 'tab'. Requires 'tabs' permission.
 */
class ContextOptions(
    var showDefaults: Boolean? = null,
    var context: String? = null,
    var bookmarkId: String? = null,
    var tabId: Int? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class GetTargetElementResult() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

typealias MenuIds = Any

/**
 * Information about the context of the menu action and the created menu items. For more information
        about each property, see OnClickData. The following properties are only set if the extension
        has host permissions for the given context: linkUrl, linkText, srcUrl, pageUrl, frameUrl,
        selectionText.
 * @param menuIds A list of IDs of the menu items that were shown.
 * @param contexts A list of all contexts that apply to the menu.
 */
class Info(
    var menuIds: Array<MenuIds>,
    var contexts: Array<ContextType>,
    var viewType: ViewType? = null,
    var editable: Boolean,
    var mediaType: String? = null,
    var linkUrl: String? = null,
    var linkText: String? = null,
    var srcUrl: String? = null,
    var pageUrl: String? = null,
    var frameUrl: String? = null,
    var selectionText: String? = null,
    var targetElementId: Int? = null
)

external class MenusNamespace {
    /**
     * Fired when a context menu item is clicked.
     *
     * @param info Information about the item clicked and the context where the click happened.
     * @param tab The details of the tab where the click took place. If the click did not take place
            in a tab, this parameter will be missing. */
    val onClicked: Event<(info: OnClickData, tab: Tab?) -> Unit>

    /**
     * Fired when a menu is shown. The extension can add, modify or remove menu items and call
            menus.refresh() to update the menu.
     *
     * @param info Information about the context of the menu action and the created menu items. For
            more information about each property, see OnClickData. The following properties are only
            set if the extension has host permissions for the given context: linkUrl, linkText,
            srcUrl, pageUrl, frameUrl, selectionText.
     * @param tab The details of the tab where the menu was opened. */
    val onShown: Event<(info: Info, tab: Tab) -> Unit>

    /**
     * Fired when a menu is hidden. This event is only fired if onShown has fired before.
     */
    val onHidden: Event<() -> Unit>

    /**
     * Creates a new context menu item. Note that if an error occurs during creation, you may not
            find out until the creation callback fires (the details will be in
            $(ref:runtime.lastError)).
     */
    fun create(createProperties: CreateProperties, callback: (() -> Unit)? = definedExternally):
            CreateResult

    /**
     * Updates a previously created context menu item.
     */
    fun update(id: Int, updateProperties: UpdateProperties): Promise<Any>

    /**
     * Updates a previously created context menu item.
     */
    fun update(id: String, updateProperties: UpdateProperties): Promise<Any>

    /**
     * Removes a context menu item.
     */
    fun remove(menuItemId: Int): Promise<Any>

    /**
     * Removes a context menu item.
     */
    fun remove(menuItemId: String): Promise<Any>

    /**
     * Removes all context menu items added by this extension.
     */
    fun removeAll(): Promise<Any>

    /**
     * Show the matching menu items from this extension instead of the default menu. This should be
            called during a 'contextmenu' DOM event handler, and only applies to the menu that opens
            after this event.
     */
    fun overrideContext(contextOptions: ContextOptions)

    /**
     * Updates the extension items in the shown menu, including changes that have been made since
            the menu was shown. Has no effect if the menu is hidden. Rebuilding a shown menu is an
            expensive operation, only invoke this method when necessary.
     */
    fun refresh(): Promise<Any>

    /**
     * Retrieve the element that was associated with a recent contextmenu event.
     */
    fun getTargetElement(targetElementId: Int): GetTargetElementResult?
}
