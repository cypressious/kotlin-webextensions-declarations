package extensionTypes

/**
 * The format of an image. */
typealias ImageFormat = String

/**
 * Details about the format and quality of an image.
 */
external class ImageDetails {
    /**
     * The format of the resulting image.  Default is <code>"jpeg"</code>.
     */
    var format: ImageFormat?

    /**
     * When format is <code>"jpeg"</code>, controls the quality of the resulting image.  This value is ignored for PNG images.  As quality is decreased, the resulting image will have more visual artifacts, and the number of bytes needed to store it will decrease.
     */
    var quality: Int?
}

/**
 * The soonest that the JavaScript or CSS will be injected into the tab. */
typealias RunAt = String

/**
 * The origin of the CSS to inject, this affects the cascading order (priority) of the stylesheet. */
typealias CSSOrigin = String

/**
 * Details of the script or CSS to inject. Either the code or the file property must be set, but both may not be set at the same time.
 */
external class InjectDetails {
    /**
     * JavaScript or CSS code to inject.<br><br><b>Warning:</b><br>Be careful using the <code>code</code> parameter. Incorrect use of it may open your extension to <a href="https://en.wikipedia.org/wiki/Cross-site_scripting">cross site scripting</a> attacks.
     */
    var code: String?

    /**
     * JavaScript or CSS file to inject.
     */
    var file: String?

    /**
     * If allFrames is <code>true</code>, implies that the JavaScript or CSS should be injected into all frames of current page. By default, it's <code>false</code> and is only injected into the top frame.
     */
    var allFrames: Boolean?

    /**
     * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document. Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
     */
    var matchAboutBlank: Boolean?

    /**
     * The ID of the frame to inject the script into. This may not be used in combination with <code>allFrames</code>.
     */
    var frameId: Int?

    /**
     * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
     */
    var runAt: RunAt?

    /**
     * The css origin of the stylesheet to inject. Defaults to "author".
     */
    var cssOrigin: CSSOrigin?
}

typealias Date = Any

external class ExtensionTypesNamespace
