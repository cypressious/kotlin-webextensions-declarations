package pkcs11

import kotlin.js.Promise

external class Pkcs11 {
  /**
   * checks whether a PKCS#11 module, given by name, is installed
   */
  fun isModuleInstalled(name: String): Promise<Any>

  /**
   * Install a PKCS#11 module with a given name
   */
  fun installModule(name: String, flags: Int?): Promise<Any>

  /**
   * Remove an installed PKCS#11 module from firefox
   */
  fun uninstallModule(name: String): Promise<Any>

  /**
   * Enumerate a module's slots, each with their name and whether a token is present
   */
  fun getModuleSlots(name: String): Promise<Any>
}
