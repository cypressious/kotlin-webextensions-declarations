package manifest

import extensionTypes.RunAt

external class OptionalPermission

/**
 * Represents a WebExtension manifest.json file
 */
external class WebExtensionManifest {
  val browser_action: Browser_action

  val chrome_settings_overrides: Chrome_settings_overrides

  val commands: Commands

  val devtools_page: ExtensionURL

  /**
   * A list of protocol handler definitions.
   */
  val protocol_handlers: Array<ProtocolHandler>?

  val default_locale: String?

  val manifest_version: Int

  val minimum_chrome_version: String?

  val minimum_opera_version: String?

  val applications: Applications

  val browser_specific_settings: Browser_specific_settings

  val name: String

  val short_name: String?

  val description: String?

  val author: String?

  val version: String

  val homepage_url: String?

  val icons: Icons

  val incognito: String?

  val background: Any?

  val options_ui: Options_ui

  val content_scripts: Array<ContentScript>?

  val content_security_policy: String?

  val permissions: Array<PermissionOrOrigin>?

  val optional_permissions: Array<OptionalPermissionOrOrigin>?

  val web_accessible_resources: Array<String>?

  val developer: Developer

  val omnibox: Omnibox

  val page_action: Page_action

  val sidebar_action: Sidebar_action

  val theme: ThemeType

  val chrome_url_overrides: Chrome_url_overrides
}

external class Permission

typealias KeyName = Any

/**
 * Represents a protocol handler definition.
 */
external class ProtocolHandler {
  /**
   * A user-readable title string for the protocol handler. This will be displayed to the user in interface objects as needed.
   */
  val name: String

  /**
   * The protocol the site wishes to handle, specified as a string. For example, you can register to handle SMS text message links by registering to handle the "sms" scheme.
   */
  val protocol: Any

  /**
   * The URL of the handler, as a string. This string should include "%s" as a placeholder which will be replaced with the escaped URL of the document to be handled. This URL might be a true URL, or it could be a phone number, email address, or so forth.
   */
  val uriTemplate: Any
}

/**
 * Represents a WebExtension language pack manifest.json file
 */
external class WebExtensionLangpackManifest {
  val manifest_version: Int

  val applications: Applications2

  val browser_specific_settings: Browser_specific_settings2

  val name: String

  val short_name: String?

  val description: String?

  val author: String?

  val version: String

  val homepage_url: String?

  val langpack_id: String

  val languages: Languages

  val sources: Sources
}

external class ThemeIcons {
  /**
   * A light icon to use for dark themes
   */
  val light: ExtensionURL

  /**
   * The dark icon to use for light themes
   */
  val dark: ExtensionURL

  /**
   * The size of the icons
   */
  val size: Int
}

typealias OptionalPermissionOrOrigin = Any

typealias PermissionOrOrigin = Any

typealias HttpURL = String

typealias ExtensionURL = String

typealias ImageDataOrExtensionURL = String

typealias ExtensionID = Any

external class FirefoxSpecificProperties {
  val id: ExtensionID

  val update_url: String?

  val strict_min_version: String?

  val strict_max_version: String?
}

typealias MatchPattern = Any

typealias MatchPatternInternal = Any

/**
 * Details of the script or CSS to inject. Either the code or the file property must be set, but both may not be set at the same time. Based on InjectDetails, but using underscore rather than camel case naming conventions.
 */
external class ContentScript {
  val matches: Array<MatchPattern>

  val exclude_matches: Array<MatchPattern>?

  val include_globs: Array<String>?

  val exclude_globs: Array<String>?

  /**
   * The list of CSS files to inject
   */
  val css: Array<ExtensionURL>?

  /**
   * The list of JS files to inject
   */
  val js: Array<ExtensionURL>?

  /**
   * If allFrames is <code>true</code>, implies that the JavaScript or CSS should be injected into all frames of current page. By default, it's <code>false</code> and is only injected into the top frame.
   */
  val all_frames: Boolean?

  /**
   * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document. Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
   */
  val match_about_blank: Boolean?

  /**
   * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
   */
  val run_at: RunAt
}

typealias IconPath = Any

typealias IconImageData = Any

typealias ImageData = Any

typealias UnrecognizedProperty = Any

typealias PersistentBackgroundProperty = Boolean

typealias NativeManifest = Any

typealias ThemeColor = Any

external class ThemeType {
  val images: Images

  val colors: Colors

  val icons: Icons2

  val properties: Properties
}

external class Browser_action {
  val default_title: String?

  val default_icon: IconPath

  /**
   * Specifies icons to use for dark and light themes
   */
  val theme_icons: Array<ThemeIcons>?

  val default_popup: String?

  val browser_style: Boolean?

  /**
   * Defines the location the browserAction will appear by default.  The default location is navbar.
   */
  val default_area: String?
}

class Search_provider(
    val name: String,
    val keyword: String?,
    val search_url: String,
    val favicon_url: String?,
    val suggest_url: String?,
    val instant_url: String?,
    val image_url: String?,
    val search_url_post_params: String?,
    val instant_url_post_params: String?,
    val image_url_post_params: String?,
    val alternate_urls: Array<String>?,
    val prepopulated_id: Int?,
    /**
     * Sets the default engine to a built-in engine only.
     */
    val is_default: Boolean?
)

external class Chrome_settings_overrides {
  val homepage: String?

  val search_provider: Search_provider?
}

typealias Commands = Any

external class Applications {
  val gecko: FirefoxSpecificProperties
}

external class Browser_specific_settings {
  val gecko: FirefoxSpecificProperties
}

typealias Icons = Any

external class Options_ui {
  val page: ExtensionURL

  val browser_style: Boolean?

  val chrome_style: Boolean?

  val open_in_tab: Boolean?
}

external class Developer {
  val name: String?

  val url: String?
}

external class Omnibox {
  val keyword: String
}

external class Page_action {
  val default_title: String?

  val default_icon: IconPath

  val default_popup: String?

  val browser_style: Boolean?
}

external class Sidebar_action {
  val default_title: String?

  val default_icon: IconPath

  val browser_style: Boolean?

  val default_panel: String
}

external class Chrome_url_overrides {
  val newtab: ExtensionURL
}

external class Applications2 {
  val gecko: FirefoxSpecificProperties
}

external class Browser_specific_settings2 {
  val gecko: FirefoxSpecificProperties
}

typealias Languages = Any

typealias Sources = Any

external class Images {
  val additional_backgrounds: Array<ImageDataOrExtensionURL>?

  val headerURL: ImageDataOrExtensionURL

  val theme_frame: ImageDataOrExtensionURL
}

external class Colors {
  val accentcolor: ThemeColor

  val frame: ThemeColor

  val textcolor: ThemeColor

  val background_tab_text: ThemeColor

  val tab_text: ThemeColor

  val toolbar: ThemeColor

  val toolbar_text: ThemeColor

  val bookmark_text: ThemeColor

  val toolbar_field: ThemeColor

  val toolbar_field_text: ThemeColor

  val toolbar_field_border: ThemeColor

  val toolbar_top_separator: ThemeColor

  val toolbar_bottom_separator: ThemeColor

  val toolbar_vertical_separator: ThemeColor
}

external class Icons2 {
  val back: ExtensionURL

  val forward: ExtensionURL

  val reload: ExtensionURL

  val stop: ExtensionURL

  val bookmark_star: ExtensionURL

  val bookmark_menu: ExtensionURL

  val downloads: ExtensionURL

  val home: ExtensionURL

  val app_menu: ExtensionURL

  val cut: ExtensionURL

  val copy: ExtensionURL

  val paste: ExtensionURL

  val new_window: ExtensionURL

  val new_private_window: ExtensionURL

  val save_page: ExtensionURL

  val print: ExtensionURL

  val history: ExtensionURL

  val full_screen: ExtensionURL

  val find: ExtensionURL

  val options: ExtensionURL

  val addons: ExtensionURL

  val developer: ExtensionURL

  val synced_tabs: ExtensionURL

  val open_file: ExtensionURL

  val sidebars: ExtensionURL

  val subscribe: ExtensionURL

  val text_encoding: ExtensionURL

  val email_link: ExtensionURL

  val forget: ExtensionURL

  val pocket: ExtensionURL

  val getmsg: ExtensionURL

  val newmsg: ExtensionURL

  val address: ExtensionURL

  val reply: ExtensionURL

  val replyall: ExtensionURL

  val replylist: ExtensionURL

  val forwarding: ExtensionURL

  val delete: ExtensionURL

  val junk: ExtensionURL

  val file: ExtensionURL

  val nextUnread: ExtensionURL

  val prevUnread: ExtensionURL

  val mark: ExtensionURL

  val tag: ExtensionURL

  val compact: ExtensionURL

  val archive: ExtensionURL

  val chat: ExtensionURL

  val nextMsg: ExtensionURL

  val prevMsg: ExtensionURL

  val QFB: ExtensionURL

  val conversation: ExtensionURL

  val newcard: ExtensionURL

  val newlist: ExtensionURL

  val editcard: ExtensionURL

  val newim: ExtensionURL

  val send: ExtensionURL

  val spelling: ExtensionURL

  val attach: ExtensionURL

  val security: ExtensionURL

  val save: ExtensionURL

  val quote: ExtensionURL

  val buddy: ExtensionURL

  val join_chat: ExtensionURL

  val chat_accounts: ExtensionURL

  val calendar: ExtensionURL

  val tasks: ExtensionURL

  val synchronize: ExtensionURL

  val newevent: ExtensionURL

  val newtask: ExtensionURL

  val editevent: ExtensionURL

  val today: ExtensionURL

  val category: ExtensionURL

  val complete: ExtensionURL

  val priority: ExtensionURL

  val saveandclose: ExtensionURL

  val attendees: ExtensionURL

  val privacy: ExtensionURL

  val status: ExtensionURL

  val freebusy: ExtensionURL

  val timezones: ExtensionURL
}

external class Properties {
  val additional_backgrounds_alignment: Array<String>?

  val additional_backgrounds_tiling: Array<String>?
}

external class ManifestNamespace
