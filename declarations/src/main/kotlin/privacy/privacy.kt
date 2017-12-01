package privacy

/**
 * The IP handling policy of WebRTC. */
typealias IPHandlingPolicy = String

external class NetworkNamespace

external class ServicesNamespace

/**
 * The mode for tracking protection. */
typealias TrackingProtectionModeOption = String

external class WebsitesNamespace

external class PrivacyNamespace {
    val network: NetworkNamespace

    val services: ServicesNamespace

    val websites: WebsitesNamespace
}
