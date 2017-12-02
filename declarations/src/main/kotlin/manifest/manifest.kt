package manifest

import extensionTypes.RunAt

typealias OptionalPermission = Any

/**
 * Represents a WebExtension manifest.json file
 */
external class WebExtensionManifest {
    var browser_action: Browser_action?

    var chrome_settings_overrides: Chrome_settings_overrides?

    var commands: Commands2?

    var devtools_page: ExtensionURL?

    /**
     * A list of protocol handler definitions.
     */
    var protocol_handlers: Array<ProtocolHandler>

    var default_locale: String?

    var manifest_version: Int

    var minimum_chrome_version: String?

    var minimum_opera_version: String?

    var applications: Applications?

    var browser_specific_settings: Browser_specific_settings?

    var name: String

    var short_name: String?

    var description: String?

    var author: String?

    var version: String

    var homepage_url: String?

    var icons: Icons?

    var incognito: String?

    var background: Background3?

    var options_ui: Options_ui?

    var content_scripts: Array<ContentScript>

    var content_security_policy: String?

    var permissions: Array<PermissionOrOrigin>

    var optional_permissions: Array<OptionalPermissionOrOrigin>

    var web_accessible_resources: Array<String>

    var developer: Developer?

    var omnibox: Omnibox?

    var page_action: Page_action?

    var sidebar_action: Sidebar_action?

    var theme: ThemeType?

    var chrome_url_overrides: Chrome_url_overrides?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

typealias Permission = Any

typealias KeyName = Any

/**
 * Represents a protocol handler definition.
 */
external class ProtocolHandler {
    /**
     * A user-readable title string for the protocol handler. This will be displayed to the user in interface objects as needed.
     */
    var name: String

    /**
     * The protocol the site wishes to handle, specified as a string. For example, you can register to handle SMS text message links by registering to handle the "sms" scheme.
     */
    var protocol: Protocol

    /**
     * The URL of the handler, as a string. This string should include "%s" as a placeholder which will be replaced with the escaped URL of the document to be handled. This URL might be a true URL, or it could be a phone number, email address, or so forth.
     */
    var uriTemplate: UriTemplate
}

/**
 * Represents a WebExtension language pack manifest.json file
 */
external class WebExtensionLangpackManifest {
    var manifest_version: Int

    var applications: Applications2?

    var browser_specific_settings: Browser_specific_settings2?

    var name: String

    var short_name: String?

    var description: String?

    var author: String?

    var version: String

    var homepage_url: String?

    var langpack_id: String

    var languages: Languages2

    var sources: Sources2?
}

external class ThemeIcons {
    /**
     * A light icon to use for dark themes
     */
    var light: ExtensionURL

    /**
     * The dark icon to use for light themes
     */
    var dark: ExtensionURL

    /**
     * The size of the icons
     */
    var size: Int

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

typealias OptionalPermissionOrOrigin = Any

typealias PermissionOrOrigin = Any

typealias HttpURL = String

typealias ExtensionURL = String

typealias ImageDataOrExtensionURL = String

typealias ExtensionID = Any

external class FirefoxSpecificProperties {
    var id: ExtensionID?

    var update_url: String?

    var strict_min_version: String?

    var strict_max_version: String?
}

typealias MatchPattern = Any

/**
 * Same as MatchPattern above, but includes moz-extension protocol */
typealias MatchPatternInternal = Any

/**
 * Details of the script or CSS to inject. Either the code or the file property must be set, but both may not be set at the same time. Based on InjectDetails, but using underscore rather than camel case naming conventions.
 */
external class ContentScript {
    var matches: Array<MatchPattern>

    var exclude_matches: Array<MatchPattern>

    var include_globs: Array<String>

    var exclude_globs: Array<String>

    /**
     * The list of CSS files to inject
     */
    var css: Array<ExtensionURL>

    /**
     * The list of JS files to inject
     */
    var js: Array<ExtensionURL>

    /**
     * If allFrames is <code>true</code>, implies that the JavaScript or CSS should be injected into all frames of current page. By default, it's <code>false</code> and is only injected into the top frame.
     */
    var all_frames: Boolean?

    /**
     * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document. Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
     */
    var match_about_blank: Boolean?

    /**
     * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
     */
    var run_at: RunAt?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

typealias IconPath = Any

typealias IconImageData = Any

typealias ImageData = Any

typealias UnrecognizedProperty = Any

typealias PersistentBackgroundProperty = Boolean

/**
 * Represents a native manifest file */
typealias NativeManifest = Any

typealias ThemeColor = Any

external class ThemeType {
    var images: Images?

    var colors: Colors?

    var icons: Icons2?

    var properties: Properties?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Browser_action {
    var default_title: String?

    var default_icon: IconPath?

    /**
     * Specifies icons to use for dark and light themes
     */
    var theme_icons: Array<ThemeIcons>

    var default_popup: String?

    var browser_style: Boolean?

    /**
     * Defines the location the browserAction will appear by default.  The default location is navbar.
     */
    var default_area: String?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Search_provider {
    var name: String

    var keyword: String?

    var search_url: String

    var favicon_url: String?

    var suggest_url: String?

    var instant_url: String?

    var image_url: String?

    var search_url_post_params: String?

    var instant_url_post_params: String?

    var image_url_post_params: String?

    var alternate_urls: Array<String>

    var prepopulated_id: Int?

    /**
     * Sets the default engine to a built-in engine only.
     */
    var is_default: Boolean?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Chrome_settings_overrides {
    var homepage: String?

    var search_provider: Search_provider?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Suggested_key {
    var default: KeyName?

    var mac: KeyName?

    var linux: KeyName?

    var windows: KeyName?

    var chromeos: String?

    var android: String?

    var ios: String?

    var additionalProperties: String?
}

external class Commands {
    var suggested_key: Suggested_key?

    var description: String?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Commands2 {
    operator fun get(key: String): Commands

    operator fun set(key: String, value: Commands)
}

external class Applications {
    var gecko: FirefoxSpecificProperties?
}

external class Browser_specific_settings {
    var gecko: FirefoxSpecificProperties?
}

external class Icons {
    operator fun get(key: String): String

    operator fun set(key: String, value: String)
}

external class Background {
    var page: ExtensionURL

    var persistent: PersistentBackgroundProperty?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Background2 {
    var scripts: Array<ExtensionURL>

    var persistent: PersistentBackgroundProperty?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

typealias Background3 = Any

external class Options_ui {
    var page: ExtensionURL

    var browser_style: Boolean?

    var chrome_style: Boolean?

    var open_in_tab: Boolean?

    operator fun get(key: String): dynamic

    operator fun set(key: String, value: dynamic)
}

external class Developer {
    var name: String?

    var url: String?
}

external class Omnibox {
    var keyword: String

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Page_action {
    var default_title: String?

    var default_icon: IconPath?

    var default_popup: String?

    var browser_style: Boolean?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Sidebar_action {
    var default_title: String?

    var default_icon: IconPath?

    var browser_style: Boolean?

    var default_panel: String

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Chrome_url_overrides {
    var newtab: ExtensionURL?
}

/**
 * The protocol the site wishes to handle, specified as a string. For example, you can register to handle SMS text message links by registering to handle the "sms" scheme. */
typealias Protocol = Any

/**
 * The URL of the handler, as a string. This string should include "%s" as a placeholder which will be replaced with the escaped URL of the document to be handled. This URL might be a true URL, or it could be a phone number, email address, or so forth. */
typealias UriTemplate = Any

external class Applications2 {
    var gecko: FirefoxSpecificProperties?
}

external class Browser_specific_settings2 {
    var gecko: FirefoxSpecificProperties?
}

external class Chrome_resources {
    operator fun get(key: String): ExtensionURL

    operator fun set(key: String, value: ExtensionURL)
}

typealias Chrome_resources2 = Any

external class Chrome_resources3 {
    operator fun set(key: String, value: ExtensionURL)

    operator fun set(key: String, value: Chrome_resources)

    operator fun get(key: String): dynamic
}

external class Languages {
    var chrome_resources: Chrome_resources3

    var version: String
}

external class Languages2 {
    operator fun get(key: String): Languages

    operator fun set(key: String, value: Languages)
}

external class Sources {
    var base_path: ExtensionURL

    var paths: Array<String>
}

external class Sources2 {
    operator fun get(key: String): Sources

    operator fun set(key: String, value: Sources)
}

external class Images {
    var additional_backgrounds: Array<ImageDataOrExtensionURL>

    var headerURL: ImageDataOrExtensionURL?

    var theme_frame: ImageDataOrExtensionURL?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Colors {
    var accentcolor: ThemeColor?

    var frame: ThemeColor?

    var textcolor: ThemeColor?

    var background_tab_text: ThemeColor?

    var tab_text: ThemeColor?

    var toolbar: ThemeColor?

    var toolbar_text: ThemeColor?

    var bookmark_text: ThemeColor?

    var toolbar_field: ThemeColor?

    var toolbar_field_text: ThemeColor?

    var toolbar_field_border: ThemeColor?

    var toolbar_top_separator: ThemeColor?

    var toolbar_bottom_separator: ThemeColor?

    var toolbar_vertical_separator: ThemeColor?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Icons2 {
    var back: ExtensionURL?

    var forward: ExtensionURL?

    var reload: ExtensionURL?

    var stop: ExtensionURL?

    var bookmark_star: ExtensionURL?

    var bookmark_menu: ExtensionURL?

    var downloads: ExtensionURL?

    var home: ExtensionURL?

    var app_menu: ExtensionURL?

    var cut: ExtensionURL?

    var copy: ExtensionURL?

    var paste: ExtensionURL?

    var new_window: ExtensionURL?

    var new_private_window: ExtensionURL?

    var save_page: ExtensionURL?

    var print: ExtensionURL?

    var history: ExtensionURL?

    var full_screen: ExtensionURL?

    var find: ExtensionURL?

    var options: ExtensionURL?

    var addons: ExtensionURL?

    var developer: ExtensionURL?

    var synced_tabs: ExtensionURL?

    var open_file: ExtensionURL?

    var sidebars: ExtensionURL?

    var subscribe: ExtensionURL?

    var text_encoding: ExtensionURL?

    var email_link: ExtensionURL?

    var forget: ExtensionURL?

    var pocket: ExtensionURL?

    var getmsg: ExtensionURL?

    var newmsg: ExtensionURL?

    var address: ExtensionURL?

    var reply: ExtensionURL?

    var replyall: ExtensionURL?

    var replylist: ExtensionURL?

    var forwarding: ExtensionURL?

    var delete: ExtensionURL?

    var junk: ExtensionURL?

    var file: ExtensionURL?

    var nextUnread: ExtensionURL?

    var prevUnread: ExtensionURL?

    var mark: ExtensionURL?

    var tag: ExtensionURL?

    var compact: ExtensionURL?

    var archive: ExtensionURL?

    var chat: ExtensionURL?

    var nextMsg: ExtensionURL?

    var prevMsg: ExtensionURL?

    var QFB: ExtensionURL?

    var conversation: ExtensionURL?

    var newcard: ExtensionURL?

    var newlist: ExtensionURL?

    var editcard: ExtensionURL?

    var newim: ExtensionURL?

    var send: ExtensionURL?

    var spelling: ExtensionURL?

    var attach: ExtensionURL?

    var security: ExtensionURL?

    var save: ExtensionURL?

    var quote: ExtensionURL?

    var buddy: ExtensionURL?

    var join_chat: ExtensionURL?

    var chat_accounts: ExtensionURL?

    var calendar: ExtensionURL?

    var tasks: ExtensionURL?

    var synchronize: ExtensionURL?

    var newevent: ExtensionURL?

    var newtask: ExtensionURL?

    var editevent: ExtensionURL?

    var today: ExtensionURL?

    var category: ExtensionURL?

    var complete: ExtensionURL?

    var priority: ExtensionURL?

    var saveandclose: ExtensionURL?

    var attendees: ExtensionURL?

    var privacy: ExtensionURL?

    var status: ExtensionURL?

    var freebusy: ExtensionURL?

    var timezones: ExtensionURL?

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class Properties {
    var additional_backgrounds_alignment: Array<String>

    var additional_backgrounds_tiling: Array<String>

    operator fun get(key: String): UnrecognizedProperty

    operator fun set(key: String, value: UnrecognizedProperty)
}

external class ManifestNamespace
