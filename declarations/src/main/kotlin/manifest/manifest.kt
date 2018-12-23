package manifest

import experiments.ExperimentAPI
import extensionTypes.RunAt

typealias OptionalPermission = Any

/**
 * Represents a WebExtension manifest.json file
 * @param protocol_handlers A list of protocol handler definitions.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class WebExtensionManifest(
    var browser_action: Browser_action? = null,
    var chrome_settings_overrides: Chrome_settings_overrides? = null,
    var commands: Commands2? = null,
    var devtools_page: ExtensionURL? = null,
    var experiment_apis: Experiment_apis? = null,
    var protocol_handlers: Array<ProtocolHandler>? = null,
    var default_locale: String? = null,
    var minimum_chrome_version: String? = null,
    var minimum_opera_version: String? = null,
    var icons: Icons? = null,
    var incognito: String? = null,
    var background: Background3? = null,
    var options_ui: Options_ui? = null,
    var content_scripts: Array<ContentScript>? = null,
    var content_security_policy: String? = null,
    var permissions: Array<PermissionOrOrigin>? = null,
    var optional_permissions: Array<OptionalPermissionOrOrigin>? = null,
    var web_accessible_resources: Array<String>? = null,
    var developer: Developer? = null,
    var hidden: Boolean? = null,
    var omnibox: Omnibox? = null,
    var page_action: Page_action? = null,
    var sidebar_action: Sidebar_action? = null,
    var theme_experiment: ThemeExperiment? = null,
    var chrome_url_overrides: Chrome_url_overrides? = null,
    var user_scripts: User_scripts? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

typealias Permission = Any

typealias KeyName = String

/**
 * Represents a protocol handler definition.
 * @param name A user-readable title string for the protocol handler. This will be displayed to the
        user in interface objects as needed.
 * @param protocol The protocol the site wishes to handle, specified as a string. For example, you
        can register to handle SMS text message links by registering to handle the "sms" scheme.
 * @param uriTemplate The URL of the handler, as a string. This string should include "%s" as a
        placeholder which will be replaced with the escaped URL of the document to be handled. This
        URL might be a true URL, or it could be a phone number, email address, or so forth.
 */
class ProtocolHandler(
    var name: String,
    var protocol: Protocol,
    var uriTemplate: UriTemplate
)

/**
 * Common properties for all manifest.json files
 */
class ManifestBase(
    var manifest_version: Int,
    var applications: Applications? = null,
    var browser_specific_settings: Browser_specific_settings? = null,
    var name: String,
    var short_name: String? = null,
    var description: String? = null,
    var author: String? = null,
    var version: String,
    var homepage_url: String? = null
)

/**
 * Represents a WebExtension language pack manifest.json file
 */
class WebExtensionLangpackManifest(
    var homepage_url: String? = null,
    var langpack_id: String,
    var languages: Languages2,
    var sources: Sources2? = null
)

/**
 * Represents a WebExtension dictionary manifest.json file
 */
class WebExtensionDictionaryManifest(var homepage_url: String? = null, var dictionaries:
        Dictionaries)

/**
 * @param light A light icon to use for dark themes
 * @param dark The dark icon to use for light themes
 * @param size The size of the icons
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ThemeIcons(
    var light: ExtensionURL,
    var dark: ExtensionURL,
    var size: Int
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

typealias OptionalPermissionOrOrigin = Any

typealias PermissionOrOrigin = Any

typealias HttpURL = String

typealias ExtensionURL = String

typealias ImageDataOrExtensionURL = String

typealias ExtensionID = Any

class FirefoxSpecificProperties(
    var id: ExtensionID? = null,
    var update_url: String? = null,
    var strict_min_version: String? = null,
    var strict_max_version: String? = null
)

typealias MatchPattern = Any

/**
 * Same as MatchPattern above, but excludes <all_urls> */
typealias MatchPatternRestricted = Any

/**
 * Mostly unrestricted match patterns for privileged add-ons. This should technically be rejected
        for unprivileged add-ons, but, reasons. The MatchPattern class will still refuse privileged
        schemes for those extensions. */
typealias MatchPatternUnestricted = Any

/**
 * Details of the script or CSS to inject. Either the code or the file property must be set, but
        both may not be set at the same time. Based on InjectDetails, but using underscore rather
        than camel case naming conventions.
 * @param css The list of CSS files to inject
 * @param js The list of JS files to inject
 * @param all_frames If allFrames is <code>true</code>, implies that the JavaScript or CSS should be
        injected into all frames of current page. By default, it's <code>false</code> and is only
        injected into the top frame.
 * @param match_about_blank If matchAboutBlank is true, then the code is also injected in
        about:blank and about:srcdoc frames if your extension has access to its parent document.
        Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
 * @param run_at The soonest that the JavaScript or CSS will be injected into the tab. Defaults to
        "document_idle".
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ContentScript(
    var matches: Array<MatchPattern>,
    var exclude_matches: Array<MatchPattern>? = null,
    var include_globs: Array<String>? = null,
    var exclude_globs: Array<String>? = null,
    var css: Array<ExtensionURL>? = null,
    var js: Array<ExtensionURL>? = null,
    var all_frames: Boolean? = null,
    var match_about_blank: Boolean? = null,
    var run_at: RunAt? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
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

class ThemeExperiment(
    var stylesheet: ExtensionURL? = null,
    var images: Images? = null,
    var colors: Colors? = null,
    var properties: Properties? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ThemeType(
    var images: Images2? = null,
    var colors: Colors2? = null,
    var icons: Icons2? = null,
    var properties: Properties2? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

/**
 * Contents of manifest.json for a static theme
 */
class ThemeManifest(
    var theme: ThemeType,
    var default_locale: String? = null,
    var theme_experiment: ThemeExperiment? = null,
    var icons: Icons3? = null
)

/**
 * @param theme_icons Specifies icons to use for dark and light themes
 * @param default_area Defines the location the browserAction will appear by default.  The default
        location is navbar.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Browser_action(
    var default_title: String? = null,
    var default_icon: IconPath? = null,
    var theme_icons: Array<ThemeIcons>? = null,
    var default_popup: String? = null,
    var browser_style: Boolean? = null,
    var default_area: String? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

/**
 * @param name A url parameter name
 * @param condition The type of param can be either "purpose" or "pref".
 * @param pref The preference to retreive the value from.
 * @param purpose The context that initiates a search, required if condition is "purpose".
 * @param value A url parameter value.
 */
class Params(
    var name: String,
    var condition: String? = null,
    var pref: String? = null,
    var purpose: String? = null,
    var value: String? = null
)

/**
 * @param search_url_post_params POST parameters to the search_url as a query string.
 * @param suggest_url_post_params POST parameters to the suggest_url as a query string.
 * @param encoding Encoding of the search term.
 * @param is_default Sets the default engine to a built-in engine only.
 * @param params A list of optional search url parameters. This allows the additon of search url
        parameters based on how the search is performed in Firefox.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Search_provider(
    var name: String,
    var keyword: String? = null,
    var search_url: String,
    var favicon_url: String? = null,
    var suggest_url: String? = null,
    var instant_url: String? = null,
    var image_url: String? = null,
    var search_url_post_params: String? = null,
    var suggest_url_post_params: String? = null,
    var instant_url_post_params: String? = null,
    var image_url_post_params: String? = null,
    var alternate_urls: Array<String>? = null,
    var prepopulated_id: Int? = null,
    var encoding: String? = null,
    var is_default: Boolean? = null,
    var params: Array<Params>? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Chrome_settings_overrides(var homepage: String? = null, var search_provider: Search_provider?
        = null) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

class Suggested_key(
    var default: KeyName? = null,
    var mac: KeyName? = null,
    var linux: KeyName? = null,
    var windows: KeyName? = null,
    var chromeos: String? = null,
    var android: String? = null,
    var ios: String? = null,
    var additionalProperties: String? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Commands(var suggested_key: Suggested_key? = null, var description: String? = null) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Commands2() {
    inline operator fun get(key: String): Commands = asDynamic()[key]
    inline operator fun set(key: String, value: Commands) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Experiment_apis() {
    inline operator fun get(key: String): ExperimentAPI = asDynamic()[key]
    inline operator fun set(key: String, value: ExperimentAPI) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Background(var page: ExtensionURL, var persistent: PersistentBackgroundProperty? = null) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Background2(var scripts: Array<ExtensionURL>, var persistent: PersistentBackgroundProperty? =
        null) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

typealias Background3 = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Options_ui(
    var page: ExtensionURL,
    var browser_style: Boolean? = null,
    var chrome_style: Boolean? = null,
    var open_in_tab: Boolean? = null
) {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

class Developer(var name: String? = null, var url: String? = null)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Omnibox(var keyword: String) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Page_action(
    var default_title: String? = null,
    var default_icon: IconPath? = null,
    var default_popup: String? = null,
    var browser_style: Boolean? = null,
    var show_matches: Array<MatchPattern>? = null,
    var hide_matches: Array<MatchPatternRestricted>? = null,
    var pinned: Boolean? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

/**
 * @param open_at_install Whether or not the sidebar is opened at install. Default is
        <code>true</code>.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Sidebar_action(
    var default_title: String? = null,
    var default_icon: IconPath? = null,
    var browser_style: Boolean? = null,
    var default_panel: String,
    var open_at_install: Boolean? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

class Chrome_url_overrides(var newtab: ExtensionURL? = null)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class User_scripts(var api_script: ExtensionURL? = null) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

/**
 * The protocol the site wishes to handle, specified as a string. For example, you can register to
        handle SMS text message links by registering to handle the "sms" scheme. */
typealias Protocol = Any

/**
 * The URL of the handler, as a string. This string should include "%s" as a placeholder which will
        be replaced with the escaped URL of the document to be handled. This URL might be a true
        URL, or it could be a phone number, email address, or so forth. */
typealias UriTemplate = Any

class Applications(var gecko: FirefoxSpecificProperties? = null)

class Browser_specific_settings(var gecko: FirefoxSpecificProperties? = null)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Chrome_resources() {
    inline operator fun get(key: String): ExtensionURL = asDynamic()[key]
    inline operator fun set(key: String, value: ExtensionURL) {
        asDynamic()[key] = value
    }
}

typealias Chrome_resources2 = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Chrome_resources3() {
    inline operator fun set(key: String, value: ExtensionURL) {
        asDynamic()[key] = value
    }

    inline operator fun set(key: String, value: Chrome_resources) {
        asDynamic()[key] = value
    }

    inline operator fun get(key: String): dynamic = asDynamic()[key]}

class Languages(var chrome_resources: Chrome_resources3, var version: String)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Languages2() {
    inline operator fun get(key: String): Languages = asDynamic()[key]
    inline operator fun set(key: String, value: Languages) {
        asDynamic()[key] = value
    }
}

class Sources(var base_path: ExtensionURL, var paths: Array<String>? = null)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Sources2() {
    inline operator fun get(key: String): Sources = asDynamic()[key]
    inline operator fun set(key: String, value: Sources) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Dictionaries() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Images() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Colors() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Properties() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Images2(
    var additional_backgrounds: Array<ImageDataOrExtensionURL>? = null,
    var headerURL: ImageDataOrExtensionURL? = null,
    var theme_frame: ImageDataOrExtensionURL? = null
) {
    inline operator fun get(key: String): ImageDataOrExtensionURL = asDynamic()[key]
    inline operator fun set(key: String, value: ImageDataOrExtensionURL) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Colors2(
    var tab_selected: ThemeColor? = null,
    var accentcolor: ThemeColor? = null,
    var frame: ThemeColor? = null,
    var frame_inactive: ThemeColor? = null,
    var textcolor: ThemeColor? = null,
    var tab_background_text: ThemeColor? = null,
    var tab_background_separator: ThemeColor? = null,
    var tab_loading: ThemeColor? = null,
    var tab_text: ThemeColor? = null,
    var tab_line: ThemeColor? = null,
    var toolbar: ThemeColor? = null,
    var toolbar_text: ThemeColor? = null,
    var bookmark_text: ThemeColor? = null,
    var toolbar_field: ThemeColor? = null,
    var toolbar_field_text: ThemeColor? = null,
    var toolbar_field_border: ThemeColor? = null,
    var toolbar_field_separator: ThemeColor? = null,
    var toolbar_top_separator: ThemeColor? = null,
    var toolbar_bottom_separator: ThemeColor? = null,
    var toolbar_vertical_separator: ThemeColor? = null,
    var icons: ThemeColor? = null,
    var icons_attention: ThemeColor? = null,
    var button_background_hover: ThemeColor? = null,
    var button_background_active: ThemeColor? = null,
    var popup: ThemeColor? = null,
    var popup_text: ThemeColor? = null,
    var popup_border: ThemeColor? = null,
    var toolbar_field_focus: ThemeColor? = null,
    var toolbar_field_text_focus: ThemeColor? = null,
    var toolbar_field_border_focus: ThemeColor? = null,
    var popup_highlight: ThemeColor? = null,
    var popup_highlight_text: ThemeColor? = null,
    var ntp_background: ThemeColor? = null,
    var ntp_text: ThemeColor? = null,
    var sidebar: ThemeColor? = null,
    var sidebar_border: ThemeColor? = null,
    var sidebar_text: ThemeColor? = null,
    var sidebar_highlight: ThemeColor? = null,
    var sidebar_highlight_text: ThemeColor? = null
) {
    inline operator fun get(key: String): ThemeColor = asDynamic()[key]
    inline operator fun set(key: String, value: ThemeColor) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons2(
    var back: ExtensionURL? = null,
    var forward: ExtensionURL? = null,
    var reload: ExtensionURL? = null,
    var stop: ExtensionURL? = null,
    var bookmark_star: ExtensionURL? = null,
    var bookmark_menu: ExtensionURL? = null,
    var downloads: ExtensionURL? = null,
    var home: ExtensionURL? = null,
    var app_menu: ExtensionURL? = null,
    var cut: ExtensionURL? = null,
    var copy: ExtensionURL? = null,
    var paste: ExtensionURL? = null,
    var new_window: ExtensionURL? = null,
    var new_private_window: ExtensionURL? = null,
    var save_page: ExtensionURL? = null,
    var print: ExtensionURL? = null,
    var history: ExtensionURL? = null,
    var full_screen: ExtensionURL? = null,
    var find: ExtensionURL? = null,
    var options: ExtensionURL? = null,
    var addons: ExtensionURL? = null,
    var developer: ExtensionURL? = null,
    var synced_tabs: ExtensionURL? = null,
    var open_file: ExtensionURL? = null,
    var sidebars: ExtensionURL? = null,
    var subscribe: ExtensionURL? = null,
    var text_encoding: ExtensionURL? = null,
    var email_link: ExtensionURL? = null,
    var forget: ExtensionURL? = null,
    var pocket: ExtensionURL? = null,
    var getmsg: ExtensionURL? = null,
    var newmsg: ExtensionURL? = null,
    var address: ExtensionURL? = null,
    var reply: ExtensionURL? = null,
    var replyall: ExtensionURL? = null,
    var replylist: ExtensionURL? = null,
    var forwarding: ExtensionURL? = null,
    var delete: ExtensionURL? = null,
    var junk: ExtensionURL? = null,
    var file: ExtensionURL? = null,
    var nextUnread: ExtensionURL? = null,
    var prevUnread: ExtensionURL? = null,
    var mark: ExtensionURL? = null,
    var tag: ExtensionURL? = null,
    var compact: ExtensionURL? = null,
    var archive: ExtensionURL? = null,
    var chat: ExtensionURL? = null,
    var nextMsg: ExtensionURL? = null,
    var prevMsg: ExtensionURL? = null,
    var QFB: ExtensionURL? = null,
    var conversation: ExtensionURL? = null,
    var newcard: ExtensionURL? = null,
    var newlist: ExtensionURL? = null,
    var editcard: ExtensionURL? = null,
    var newim: ExtensionURL? = null,
    var send: ExtensionURL? = null,
    var spelling: ExtensionURL? = null,
    var attach: ExtensionURL? = null,
    var security: ExtensionURL? = null,
    var save: ExtensionURL? = null,
    var quote: ExtensionURL? = null,
    var buddy: ExtensionURL? = null,
    var join_chat: ExtensionURL? = null,
    var chat_accounts: ExtensionURL? = null,
    var calendar: ExtensionURL? = null,
    var tasks: ExtensionURL? = null,
    var synchronize: ExtensionURL? = null,
    var newevent: ExtensionURL? = null,
    var newtask: ExtensionURL? = null,
    var editevent: ExtensionURL? = null,
    var today: ExtensionURL? = null,
    var category: ExtensionURL? = null,
    var complete: ExtensionURL? = null,
    var priority: ExtensionURL? = null,
    var saveandclose: ExtensionURL? = null,
    var attendees: ExtensionURL? = null,
    var privacy: ExtensionURL? = null,
    var status: ExtensionURL? = null,
    var freebusy: ExtensionURL? = null,
    var timezones: ExtensionURL? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Properties2(var additional_backgrounds_alignment: Array<String>? = null, var
        additional_backgrounds_tiling: Array<String>? = null) {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons3() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

external class ManifestNamespace
