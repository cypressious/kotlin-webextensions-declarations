package management

import browser.Event
import kotlin.js.Promise
import manifest.ExtensionID

/**
 * Information about an icon belonging to an extension.
 */
class IconInfo(/**
 * A number representing the width and height of the icon. Likely values include (but are not limited to) 128, 48, 24, and 16.
 */
var size: Int, /**
 * The URL for this icon image. To display a grayscale version of the icon (to indicate that an extension is disabled, for example), append <code>?grayscale=true</code> to the URL.
 */
var url: String)

/**
 * A reason the item is disabled. */
typealias ExtensionDisabledReason = String

/**
 * The type of this extension. Will always be 'extension'. */
typealias ExtensionType = String

/**
 * How the extension was installed. One of<br><var>development</var>: The extension was loaded unpacked in developer mode,<br><var>normal</var>: The extension was installed normally via an .xpi file,<br><var>sideload</var>: The extension was installed by other software on the machine,<br><var>other</var>: The extension was installed by other means. */
typealias ExtensionInstallType = String

/**
 * Information about an installed extension.
 */
class ExtensionInfo(
        /**
         * The extension's unique identifier.
         */
        var id: String,
        /**
         * The name of this extension.
         */
        var name: String,
        /**
         * A short version of the name of this extension.
         */
        var shortName: String? = null,
        /**
         * The description of this extension.
         */
        var description: String,
        /**
         * The <a href='manifest/version'>version</a> of this extension.
         */
        var version: String,
        /**
         * The <a href='manifest/version#version_name'>version name</a> of this extension if the manifest specified one.
         */
        var versionName: String? = null,
        /**
         * Whether this extension can be disabled or uninstalled by the user.
         */
        var mayDisable: Boolean,
        /**
         * Whether it is currently enabled or disabled.
         */
        var enabled: Boolean,
        /**
         * A reason the item is disabled.
         */
        var disabledReason: ExtensionDisabledReason? = null,
        /**
         * The type of this extension. Will always return 'extension'.
         */
        var type: ExtensionType,
        /**
         * The URL of the homepage of this extension.
         */
        var homepageUrl: String? = null,
        /**
         * The update URL of this extension.
         */
        var updateUrl: String? = null,
        /**
         * The url for the item's options page, if it has one.
         */
        var optionsUrl: String,
        /**
         * A list of icon information. Note that this just reflects what was declared in the manifest, and the actual image at that url may be larger or smaller than what was declared, so you might consider using explicit width and height attributes on img tags referencing these images. See the <a href='manifest/icons'>manifest documentation on icons</a> for more details.
         */
        var icons: Array<IconInfo>? = null,
        /**
         * Returns a list of API based permissions.
         */
        var permissions: Array<String>? = null,
        /**
         * Returns a list of host based permissions.
         */
        var hostPermissions: Array<String>? = null,
        /**
         * How the extension was installed.
         */
        var installType: ExtensionInstallType
)

class Options(/**
 * Whether or not a confirm-uninstall dialog should prompt the user. Defaults to false.
 */
var showConfirmDialog: Boolean? = null, /**
 * The message to display to a user when being asked to confirm removal of the extension.
 */
var dialogMessage: String? = null)

external class ManagementNamespace {
    val onDisabled: Event<(info: ExtensionInfo) -> Unit>

    val onEnabled: Event<(info: ExtensionInfo) -> Unit>

    val onInstalled: Event<(info: ExtensionInfo) -> Unit>

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
     * Returns information about the calling extension. Note: This function can be used without requesting the 'management' permission in the manifest.
     */
    fun getSelf(): Promise<ExtensionInfo>

    /**
     * Uninstalls the calling extension. Note: This function can be used without requesting the 'management' permission in the manifest.
     */
    fun uninstallSelf(options: Options? = definedExternally): Promise<Any>

    /**
     * Enables or disables the given add-on.
     */
    fun setEnabled(id: String, enabled: Boolean): Promise<Any>
}
