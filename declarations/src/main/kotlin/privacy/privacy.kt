package privacy

import types.Setting

/**
 * The IP handling policy of WebRTC. */
typealias IPHandlingPolicy = String

external class NetworkNamespace {
    /**
     * If enabled, the browser attempts to speed up your web browsing experience by pre-resolving
            DNS entries, prerendering sites (<code>&lt;link rel='prefetch' ...&gt;</code>), and
            preemptively opening TCP and SSL connections to servers.  This preference's value is a
            boolean, defaulting to <code>true</code>.
     */
    var networkPredictionEnabled: Setting

    /**
     * Allow users to enable and disable RTCPeerConnections (aka WebRTC).
     */
    var peerConnectionEnabled: Setting

    /**
     * Allow users to specify the media performance/privacy tradeoffs which impacts how WebRTC
            traffic will be routed and how much local address information is exposed. This
            preference's value is of type IPHandlingPolicy, defaulting to <code>default</code>.
     */
    var webRTCIPHandlingPolicy: Setting
}

external class ServicesNamespace {
    /**
     * If enabled, the password manager will ask if you want to save passwords. This preference's
            value is a boolean, defaulting to <code>true</code>.
     */
    var passwordSavingEnabled: Setting
}

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

external class WebsitesNamespace {
    /**
     * If enabled, the browser sends auditing pings when requested by a website (<code>&lt;a
            ping&gt;</code>). The value of this preference is of type boolean, and the default value
            is <code>true</code>.
     */
    var hyperlinkAuditingEnabled: Setting

    /**
     * If enabled, the browser sends <code>referer</code> headers with your requests. Yes, the name
            of this preference doesn't match the misspelled header. No, we're not going to change
            it. The value of this preference is of type boolean, and the default value is
            <code>true</code>.
     */
    var referrersEnabled: Setting

    /**
     * If enabled, the browser attempts to appear similar to other users by reporting generic
            information to websites. This can prevent websites from uniquely identifying users.
            Examples of data that is spoofed include number of CPU cores, precision of JavaScript
            timers, the local timezone, and disabling features such as GamePad support, and the
            WebSpeech and Navigator APIs. The value of this preference is of type boolean, and the
            default value is <code>false</code>.
     */
    var resistFingerprinting: Setting

    /**
     * If enabled, the browser will associate all data (including cookies, HSTS data, cached images,
            and more) for any third party domains with the domain in the address bar. This prevents
            third party trackers from using directly stored information to identify you across
            different websites, but may break websites where you login with a third party account
            (such as a Facebook or Google login.) The value of this preference is of type boolean,
            and the default value is <code>false</code>.
     */
    var firstPartyIsolate: Setting

    /**
     * Allow users to specify the mode for tracking protection. This setting's value is of type
            TrackingProtectionModeOption, defaulting to <code>private_browsing_only</code>.
     */
    var trackingProtectionMode: Setting

    /**
     * Allow users to specify the default settings for allowing cookies, as well as whether all
            cookies should be created as non-persistent cookies. This setting's value is of type
            CookieConfig.
     */
    var cookieConfig: Setting
}

external class PrivacyNamespace {
    val network: NetworkNamespace

    val services: ServicesNamespace

    val websites: WebsitesNamespace
}
