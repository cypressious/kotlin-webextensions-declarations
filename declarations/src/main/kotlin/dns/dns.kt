package dns

import kotlin.js.Promise

/**
 * An object encapsulating a DNS Record.
 * @param canonicalName The canonical hostname for this record.  this value is empty if the record
        was not fetched with the 'canonical_name' flag.
 * @param isTRR Record retreived with TRR.
 */
class DNSRecord(
    var canonicalName: String? = null,
    var isTRR: String,
    var addresses: Array<String>
)

typealias ResolveFlags = Array<String>

external class DnsNamespace {
    /**
     * Resolves a hostname to a DNS record.
     */
    fun resolve(hostname: String, flags: ResolveFlags? = definedExternally): Promise<Any>
}
