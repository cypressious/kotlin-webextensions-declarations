import alarms.Alarms
import bookmarks.Bookmarks
import browserAction.BrowserAction
import browserSettings.BrowserSettings
import browsingData.BrowsingData
import clipboard.Clipboard
import commands.Commands
import contentScripts.ContentScripts
import contextMenus.ContextMenus
import contextualIdentities.ContextualIdentities
import cookies.Cookies
import devtools.Devtools
import downloads.Downloads
import events.Events
import extension.Extension
import extensionTypes.ExtensionTypes
import find.Find
import geckoProfiler.GeckoProfiler
import history.History
import i18n.I18n
import identity.Identity
import idle.Idle
import management.Management
import menus.Menus
import menusInternal.MenusInternal
import notifications.Notifications
import omnibox.Omnibox
import pageAction.PageAction
import permissions.Permissions
import pkcs11.Pkcs11
import privacy.Privacy
import proxy.Proxy
import runtime.Runtime
import sessions.Sessions
import sidebarAction.SidebarAction
import storage.Storage
import tabs.Tabs
import test.Test
import theme.Theme
import topSites.TopSites
import types.Types
import webNavigation.WebNavigation
import webRequest.WebRequest
import windows.Windows

external class Browser {
  val alarms: Alarms

  val bookmarks: Bookmarks

  val browserAction: BrowserAction

  val browserSettings: BrowserSettings

  val browsingData: BrowsingData

  val clipboard: Clipboard

  val commands: Commands

  val contentScripts: ContentScripts

  val contextualIdentities: ContextualIdentities

  val cookies: Cookies

  val devtools: Devtools

  val downloads: Downloads

  val events: Events

  val extension: Extension

  val extensionTypes: ExtensionTypes

  val find: Find

  val geckoProfiler: GeckoProfiler

  val history: History

  val i18n: I18n

  val identity: Identity

  val idle: Idle

  val management: Management

  val contextMenus: ContextMenus

  val menus: Menus

  val menusInternal: MenusInternal

  val notifications: Notifications

  val omnibox: Omnibox

  val pageAction: PageAction

  val permissions: Permissions

  val pkcs11: Pkcs11

  val privacy: Privacy

  val proxy: Proxy

  val runtime: Runtime

  val sessions: Sessions

  val sidebarAction: SidebarAction

  val storage: Storage

  val tabs: Tabs

  val test: Test

  val theme: Theme

  val topSites: TopSites

  val types: Types

  val webNavigation: WebNavigation

  val webRequest: WebRequest

  val windows: Windows
}

external val browser: Browser