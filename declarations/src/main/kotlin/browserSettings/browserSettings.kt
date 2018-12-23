package browserSettings

import types.Setting

/**
 * How images should be animated in the browser. */
typealias ImageAnimationBehavior = String

/**
 * After which mouse event context menus should popup. */
typealias ContextMenuMouseEvent = String

external class BrowserSettingsNamespace {
    /**
     * Allows or disallows pop-up windows from opening in response to user events.
     */
    var allowPopupsForUserEvents: Setting

    /**
     * Enables or disables the browser cache.
     */
    var cacheEnabled: Setting

    /**
     * This boolean setting controls whether the selected tab can be closed with a double click.
     */
    var closeTabsByDoubleClick: Setting

    /**
     * Controls after which mouse event context menus popup. This setting's value is of type
            ContextMenuMouseEvent, which has possible values of <code>mouseup</code> and
            <code>mousedown</code>.
     */
    var contextMenuShowEvent: Setting

    /**
     * Returns the value of the overridden home page. Read-only.
     */
    var homepageOverride: Setting

    /**
     * Controls the behaviour of image animation in the browser. This setting's value is of type
            ImageAnimationBehavior, defaulting to <code>normal</code>.
     */
    var imageAnimationBehavior: Setting

    /**
     * Returns the value of the overridden new tab page. Read-only.
     */
    var newTabPageOverride: Setting

    /**
     * Controls where new tabs are opened. `afterCurrent` will open all new tabs next to the current
            tab, `relatedAfterCurrent` will open only related tabs next to the current tab, and
            `atEnd` will open all tabs at the end of the tab strip. The default is
            `relatedAfterCurrent`.
     */
    var newTabPosition: Setting

    /**
     * This boolean setting controls whether bookmarks are opened in the current tab or in a new
            tab.
     */
    var openBookmarksInNewTabs: Setting

    /**
     * This boolean setting controls whether search results are opened in the current tab or in a
            new tab.
     */
    var openSearchResultsInNewTabs: Setting

    /**
     * This boolean setting controls whether urlbar results are opened in the current tab or in a
            new tab.
     */
    var openUrlbarResultsInNewTabs: Setting

    /**
     * Disables webAPI notifications.
     */
    var webNotificationsDisabled: Setting

    /**
     * This setting controls whether the user-chosen colors override the page's colors.
     */
    var overrideDocumentColors: Setting

    /**
     * This setting controls whether the document's fonts are used.
     */
    var useDocumentFonts: Setting
}
