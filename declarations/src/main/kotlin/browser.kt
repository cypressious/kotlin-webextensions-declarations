import bookmarks.Bookmarks
import browserAction.BrowserAction
import browsingData.BrowsingData
import commands.Commands
import contextMenus.ContextMenus
import devtools.Devtools
import find.Find
import geckoProfiler.GeckoProfiler
import history.History
import menus.Menus
import menusInternal.MenusInternal
import omnibox.Omnibox
import pageAction.PageAction
import pkcs11.Pkcs11
import sessions.Sessions
import sidebarAction.SidebarAction
import tabs.Tabs
import windows.Windows

external class Browser {
  val bookmarks: Bookmarks

  val browserAction: BrowserAction

  val browsingData: BrowsingData

  val commands: Commands

  val devtools: Devtools

  val find: Find

  val geckoProfiler: GeckoProfiler

  val history: History

  val contextMenus: ContextMenus

  val menus: Menus

  val menusInternal: MenusInternal

  val omnibox: Omnibox

  val pageAction: PageAction

  val pkcs11: Pkcs11

  val sessions: Sessions

  val sidebarAction: SidebarAction

  val tabs: Tabs

  val windows: Windows
}

external val browser: Browser