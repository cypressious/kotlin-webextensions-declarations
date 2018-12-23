package webextensions

import alarms.AlarmsNamespace
import bookmarks.BookmarksNamespace
import browserAction.BrowserActionNamespace
import browserSettings.BrowserSettingsNamespace
import browsingData.BrowsingDataNamespace
import clipboard.ClipboardNamespace
import commands.CommandsNamespace
import contentScripts.ContentScriptsNamespace
import contextMenus.ContextMenusNamespace
import contextualIdentities.ContextualIdentitiesNamespace
import cookies.CookiesNamespace
import devtools.DevtoolsNamespace
import dns.DnsNamespace
import downloads.DownloadsNamespace
import events.EventsNamespace
import experiments.ExperimentsNamespace
import extension.ExtensionNamespace
import extensionTypes.ExtensionTypesNamespace
import find.FindNamespace
import geckoProfiler.GeckoProfilerNamespace
import history.HistoryNamespace
import i18n.I18nNamespace
import identity.IdentityNamespace
import idle.IdleNamespace
import management.ManagementNamespace
import manifest.ManifestNamespace
import menus.MenusNamespace
import notifications.NotificationsNamespace
import omnibox.OmniboxNamespace
import pageAction.PageActionNamespace
import permissions.PermissionsNamespace
import pkcs11.Pkcs11Namespace
import privacy.PrivacyNamespace
import proxy.ProxyNamespace
import runtime.RuntimeNamespace
import search.SearchNamespace
import sessions.SessionsNamespace
import sidebarAction.SidebarActionNamespace
import storage.StorageNamespace
import tabs.TabsNamespace
import telemetry.TelemetryNamespace
import test.TestNamespace
import theme.ThemeNamespace
import topSites.TopSitesNamespace
import types.TypesNamespace
import userScripts.UserScriptsNamespace
import webNavigation.WebNavigationNamespace
import webRequest.WebRequestNamespace
import windows.WindowsNamespace

external class Browser {
    val alarms: AlarmsNamespace

    val manifest: ManifestNamespace

    val bookmarks: BookmarksNamespace

    val browserAction: BrowserActionNamespace

    val browserSettings: BrowserSettingsNamespace

    val browsingData: BrowsingDataNamespace

    val clipboard: ClipboardNamespace

    val commands: CommandsNamespace

    val contentScripts: ContentScriptsNamespace

    val contextualIdentities: ContextualIdentitiesNamespace

    val cookies: CookiesNamespace

    val devtools: DevtoolsNamespace

    val dns: DnsNamespace

    val downloads: DownloadsNamespace

    val events: EventsNamespace

    val experiments: ExperimentsNamespace

    val extension: ExtensionNamespace

    val extensionTypes: ExtensionTypesNamespace

    val find: FindNamespace

    val geckoProfiler: GeckoProfilerNamespace

    val history: HistoryNamespace

    val i18n: I18nNamespace

    val identity: IdentityNamespace

    val idle: IdleNamespace

    val management: ManagementNamespace

    val contextMenus: ContextMenusNamespace

    val menus: MenusNamespace

    val notifications: NotificationsNamespace

    val omnibox: OmniboxNamespace

    val pageAction: PageActionNamespace

    val permissions: PermissionsNamespace

    val pkcs11: Pkcs11Namespace

    val privacy: PrivacyNamespace

    val proxy: ProxyNamespace

    val runtime: RuntimeNamespace

    val search: SearchNamespace

    val sessions: SessionsNamespace

    val sidebarAction: SidebarActionNamespace

    val storage: StorageNamespace

    val tabs: TabsNamespace

    val telemetry: TelemetryNamespace

    val test: TestNamespace

    val theme: ThemeNamespace

    val topSites: TopSitesNamespace

    val types: TypesNamespace

    val userScripts: UserScriptsNamespace

    val webNavigation: WebNavigationNamespace

    val webRequest: WebRequestNamespace

    val windows: WindowsNamespace
}

external class Event<in T> {
    fun addListener(listener: T)

    fun removeListener(listener: T)

    fun hasListener(listener: T): Boolean
}

external val browser: Browser
