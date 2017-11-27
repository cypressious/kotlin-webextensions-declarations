package bookmarks

import history.Query
import kotlin.js.Promise

class Destination(val parentId: String, val index: Int)

class Changes(val title: String, val url: String)

/**
 * Indicates the reason why this node is unmodifiable. The <var>managed</var> value indicates that this node was configured by the system administrator or by the custodian of a supervised user. Omitted if the node can be modified by the user and the extension (default).
 */
external class BookmarkTreeNodeUnmodifiable

/**
 * Indicates the type of a BookmarkTreeNode, which can be one of bookmark, folder or separator.
 */
external class BookmarkTreeNodeType

/**
 * A node (either a bookmark or a folder) in the bookmark tree.  Child nodes are ordered within their parent folder.
 */
external class BookmarkTreeNode {
  /**
   * The unique identifier for the node. IDs are unique within the current profile, and they remain valid even after the browser is restarted.
   */
  val id: String

  /**
   * The <code>id</code> of the parent folder.  Omitted for the root node.
   */
  val parentId: String

  /**
   * The 0-based position of this node within its parent folder.
   */
  val index: Int

  /**
   * The URL navigated to when a user clicks the bookmark. Omitted for folders.
   */
  val url: String

  /**
   * The text displayed for the node.
   */
  val title: String

  /**
   * When this node was created, in milliseconds since the epoch (<code>new Date(dateAdded)</code>).
   */
  val dateAdded: Any

  /**
   * When the contents of this folder last changed, in milliseconds since the epoch.
   */
  val dateGroupModified: Any

  /**
   * Indicates the reason why this node is unmodifiable. The <var>managed</var> value indicates that this node was configured by the system administrator or by the custodian of a supervised user. Omitted if the node can be modified by the user and the extension (default).
   */
  val unmodifiable: BookmarkTreeNodeUnmodifiable

  /**
   * Indicates the type of the BookmarkTreeNode, which can be one of bookmark, folder or separator.
   */
  val type: BookmarkTreeNodeType

  /**
   * An ordered list of children of this node.
   */
  val children: Array<BookmarkTreeNode>
}

/**
 * Object passed to the create() function.
 */
external class CreateDetails {
  /**
   * Defaults to the Other Bookmarks folder.
   */
  val parentId: String

  val index: Int

  val title: String

  val url: String

  /**
   * Indicates the type of BookmarkTreeNode to create, which can be one of bookmark, folder or separator.
   */
  val type: BookmarkTreeNodeType
}

external class Bookmarks {
  /**
   * Retrieves the specified BookmarkTreeNode(s).
   */
  fun get(idOrIdList: String): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Retrieves the specified BookmarkTreeNode(s).
   */
  fun get(idOrIdList: Array<String>): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Retrieves the children of the specified BookmarkTreeNode id.
   */
  fun getChildren(id: String): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Retrieves the recently added bookmarks.
   */
  fun getRecent(numberOfItems: Int): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Retrieves the entire Bookmarks hierarchy.
   */
  fun getTree(): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Retrieves part of the Bookmarks hierarchy, starting at the specified node.
   */
  fun getSubTree(id: String): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Searches for BookmarkTreeNodes matching the given query. Queries specified with an object produce BookmarkTreeNodes matching all specified properties.
   */
  fun search(query: String): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Searches for BookmarkTreeNodes matching the given query. Queries specified with an object produce BookmarkTreeNodes matching all specified properties.
   */
  fun search(query: Query): Promise<Array<BookmarkTreeNode>> {
  }

  /**
   * Creates a bookmark or folder under the specified parentId.  If url is NULL or missing, it will be a folder.
   */
  fun create(bookmark: CreateDetails): Promise<BookmarkTreeNode> {
  }

  /**
   * Moves the specified BookmarkTreeNode to the provided location.
   */
  fun move(id: String, destination: Destination): Promise<BookmarkTreeNode> {
  }

  /**
   * Updates the properties of a bookmark or folder. Specify only the properties that you want to change; unspecified properties will be left unchanged.  <b>Note:</b> Currently, only 'title' and 'url' are supported.
   */
  fun update(id: String, changes: Changes): Promise<BookmarkTreeNode> {
  }

  /**
   * Removes a bookmark or an empty bookmark folder.
   */
  fun remove(id: String): Promise<Any> {
  }

  /**
   * Recursively removes a bookmark folder.
   */
  fun removeTree(id: String): Promise<Any> {
  }

  /**
   * Imports bookmarks from an html bookmark file
   */
  fun import(): Promise<Any> {
  }

  /**
   * Exports bookmarks to an html bookmark file
   */
  fun export(): Promise<Any> {
  }
}
