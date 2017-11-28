package privacy

typealias IPHandlingPolicy = String

external class Network

external class Services

typealias TrackingProtectionModeOption = String

external class Websites

external class Privacy {
  val network: Network

  val services: Services

  val websites: Websites
}
