package menusInternal

external class OnClickData {
  /**
   * The ID of the menu item that was clicked.
   */
  val menuItemId: Any

  /**
   * The parent ID, if any, for the item clicked.
   */
  val parentMenuItemId: Any

  /**
   * One of 'image', 'video', or 'audio' if the context menu was activated on one of these types of elements.
   */
  val mediaType: String

  /**
   * If the element is a link, the URL it points to.
   */
  val linkUrl: String

  /**
   * Will be present for elements with a 'src' URL.
   */
  val srcUrl: String

  /**
   * The URL of the page where the menu item was clicked. This property is not set if the click occured in a context where there is no current page, such as in a launcher context menu.
   */
  val pageUrl: String

  /**
   *  The URL of the frame of the element where the context menu was clicked, if it was in a frame.
   */
  val frameUrl: String

  /**
   * The text for the context selection, if any.
   */
  val selectionText: String

  /**
   * A flag indicating whether the element is editable (text input, textarea, etc.).
   */
  val editable: Boolean

  /**
   * A flag indicating the state of a checkbox or radio item before it was clicked.
   */
  val wasChecked: Boolean

  /**
   * A flag indicating the state of a checkbox or radio item after it is clicked.
   */
  val checked: Boolean
}

external class MenusInternal
