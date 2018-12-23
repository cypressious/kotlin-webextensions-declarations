package management

import kotlin.js.Promise
import manifest.ExtensionID
import manifest.HttpURL
import webextensions.Event

/**
 * Information about an icon belonging to an extension.
 * @param size A number representing the width and height of the icon. Likely values include (but
        are not limited to) 128, 48, 24, and 16.
 * @param url The URL for this icon image. To display a grayscale version of the icon (to indicate
        that an extension is disabled, for example), append <code>?grayscale=true</code> to the URL.
 */
class IconInfo(
    var size: Int,
    var url: String
)

/**
 * A reason the item is disabled. */
typealias ExtensionDisabledReason = String

/**
 * The type of this extension, 'extension' or 'theme'. */
typealias ExtensionType = String

/**
 * How the extension was installed. One of<br><var>development</var>: The extension was loaded
        unpacked in developer mode,<br><var>normal</var>: The extension was installed normally via
        an .xpi file,<br><var>sideload</var>: The extension was installed by other software on the
        machine,<br><var>other</var>: The extension was installed by other means. */
typealias ExtensionInstallType = String

/**
 * Information about an installed extension.
 * @param id The extension's unique identifier.
 * @param name The name of this extension.
 * @param shortName A short version of the name of this extension.
 * @param description The description of this extension.
 * @param version The <a href='manifest/version'>version</a> of this extension.
 * @param versionName The <a href='manifest/version#version_name'>version name</a> of this extension
        if the manifest specified one.
 * @param mayDisable Whether this extension can be disabled or uninstalled by the user.
 * @param enabled Whether it is currently enabled or disabled.
 * @param disabledReason A reason the item is disabled.
 * @param type The type of this extension, 'extension' or 'theme'.
 * @param homepageUrl The URL of the homepage of this extension.
 * @param updateUrl The update URL of this extension.
 * @param optionsUrl The url for the item's options page, if it has one.
 * @param icons A list of icon information. Note that this just reflects what was declared in the
        manifest, and the actual image at that url may be larger or smaller than what was declared,
        so you might consider using explicit width and height attributes on img tags referencing
        these images. See the <a href='manifest/icons'>manifest documentation on icons</a> for more
        details.
 * @param permissions Returns a list of API based permissions.
 * @param hostPermissions Returns a list of host based permissions.
 * @param installType How the extension was installed.
 */
class ExtensionInfo(
    var id: String,
    var name: String,
    var shortName: String? = null,
    var description: String,
    var version: String,
    var versionName: String? = null,
    var mayDisable: Boolean,
    var enabled: Boolean,
    var disabledReason: ExtensionDisabledReason? = null,
    var type: ExtensionType,
    var homepageUrl: String? = null,
    var updateUrl: String? = null,
    var optionsUrl: String,
    var icons: Array<IconInfo>? = null,
    var permissions: Array<String>? = null,
    var hostPermissions: Array<String>? = null,
    var installType: ExtensionInstallType
)

/**
 * @param url URL pointing to the XPI file on addons.mozilla.org or similar.
 * @param hash A hash of the XPI file, using sha256 or stronger.
 */
class Options(
    var url: HttpURL,
    var hash: String? = null
)

class Result(var id: ExtensionID)

/**
 * @param showConfirmDialog Whether or not a confirm-uninstall dialog should prompt the user.
        Defaults to false.
 * @param dialogMessage The message to display to a user when being asked to confirm removal of the
        extension.
 */
class Options2(
    var showConfirmDialog: Boolean? = null,
    var dialogMessage: String? = null
)

external class ManagementNamespace {
    /**
     * Fired when an addon has been disabled.
     *
     * @param info null */
    val onDisabled: Event<(info: ExtensionInfo) -> Unit>

    /**
     * Fired when an addon has been enabled.
     *
     * @param info null */
    val onEnabled: Event<(info: ExtensionInfo) -> Unit>

    /**
     * Fired when an addon has been installed.
     *
     * @param info null */
    val onInstalled: Event<(info: ExtensionInfo) -> Unit>

    /**
     * Fired when an addon has been uninstalled.
     *
     * @param info null */
    val onUninstalled: Event<(info: ExtensionInfo) -> Unit>

    /**
     * Returns a list of information about installed extensions.
     */
    fun getAll(): Promise<Array<ExtensionInfo>>

    /**
     * Returns information about the installed extension that has the given ID.
     */
    fun get(id: ExtensionID): Promise<ExtensionInfo>

    /**
     * Installs and enables a theme extension from the given url.
     */
    fun install(options: Options): Promise<Result>

    /**
     * Returns information about the calling extension. Note: This function can be used without
            requesting the 'management' permission in the manifest.
     */
    fun getSelf(): Promise<ExtensionInfo>

    /**
     * Uninstalls the calling extension. Note: This function can be used without requesting the
            'management' permission in the manifest.
     */
    fun uninstallSelf(options: Options2? = definedExternally): Promise<Any>

    /**
     * Enables or disables the given add-on.
     */
    fun setEnabled(id: String, enabled: Boolean): Promise<Any>
}
