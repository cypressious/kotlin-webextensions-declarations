package privacy

/**
 * The IP handling policy of WebRTC. */
typealias IPHandlingPolicy = String

external class NetworkNamespace

external class ServicesNamespace

/**
 * The mode for tracking protection. */
typealias TrackingProtectionModeOption = String

/**
 * The settings for cookies.
 * @param behavior The type of cookies to allow.
 * @param nonPersistentCookies Whether to create all cookies as nonPersistent (i.e., session)
        cookies.
 */
class CookieConfig(
    var behavior: String? = null,
    var nonPersistentCookies: Boolean? = null
)

external class WebsitesNamespace

external class PrivacyNamespace {
    val network: NetworkNamespace

    val services: ServicesNamespace

    val websites: WebsitesNamespace
}
