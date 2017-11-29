package privacy

typealias IPHandlingPolicy = String

external class NetworkNamespace

external class ServicesNamespace

typealias TrackingProtectionModeOption = String

external class WebsitesNamespace

external class PrivacyNamespace {
  val network: NetworkNamespace

  val services: ServicesNamespace

  val websites: WebsitesNamespace
}
