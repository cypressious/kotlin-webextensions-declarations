package clipboard

import kotlin.Any
import kotlin.js.Promise

typealias ImageData = Any

external class ClipboardNamespace {
  /**
   * Copy an image to the clipboard. The image is re-encoded before it is written to the clipboard. If the image is invalid, the clipboard is not modified.
   */
  fun setImageData(imageData: ImageData, imageType: String): Promise<Any>
}
