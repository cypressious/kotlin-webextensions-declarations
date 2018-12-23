package events

/**
 * Description of a declarative rule for handling events.
 * @param id Optional identifier that allows referencing this rule.
 * @param tags Tags can be used to annotate rules and perform operations on sets of rules.
 * @param conditions List of conditions that can trigger the actions.
 * @param actions List of actions that are triggered if one of the condtions is fulfilled.
 * @param priority Optional priority of this rule. Defaults to 100.
 */
class Rule(
    var id: String? = null,
    var tags: Array<String>? = null,
    var conditions: Array<dynamic>,
    var actions: Array<dynamic>,
    var priority: Int? = null
)

/**
 * An object which allows the addition and removal of listeners for a Chrome event.
 */
external class Event {
    /**
     * Registers an event listener <em>callback</em> to an event.
     */
    fun addListener(callback: () -> Unit)

    /**
     * Deregisters an event listener <em>callback</em> from an event.
     */
    fun removeListener(callback: () -> Unit)

    fun hasListener(callback: () -> Unit): Boolean

    fun hasListeners(): Boolean

    /**
     * Registers rules to handle events.
     */
    fun addRules(
        eventName: String,
        webViewInstanceId: Int,
        rules: Array<Rule>,
        callback: (() -> Unit)? = definedExternally
    )

    /**
     * Returns currently registered rules.
     */
    fun getRules(
        eventName: String,
        webViewInstanceId: Int,
        ruleIdentifiers: Array<String>? = definedExternally,
        callback: () -> Unit
    )

    /**
     * Unregisters currently registered rules.
     */
    fun removeRules(
        eventName: String,
        webViewInstanceId: Int,
        ruleIdentifiers: Array<String>? = definedExternally,
        callback: (() -> Unit)? = definedExternally
    )
}

/**
 * Filters URLs for various criteria. See <a href='events#filtered'>event filtering</a>. All
        criteria are case sensitive.
 * @param hostContains Matches if the host name of the URL contains a specified string. To test
        whether a host name component has a prefix 'foo', use hostContains: '.foo'. This matches
        'www.foobar.com' and 'foo.com', because an implicit dot is added at the beginning of the
        host name. Similarly, hostContains can be used to match against component suffix ('foo.')
        and to exactly match against components ('.foo.'). Suffix- and exact-matching for the last
        components need to be done separately using hostSuffix, because no implicit dot is added at
        the end of the host name.
 * @param hostEquals Matches if the host name of the URL is equal to a specified string.
 * @param hostPrefix Matches if the host name of the URL starts with a specified string.
 * @param hostSuffix Matches if the host name of the URL ends with a specified string.
 * @param pathContains Matches if the path segment of the URL contains a specified string.
 * @param pathEquals Matches if the path segment of the URL is equal to a specified string.
 * @param pathPrefix Matches if the path segment of the URL starts with a specified string.
 * @param pathSuffix Matches if the path segment of the URL ends with a specified string.
 * @param queryContains Matches if the query segment of the URL contains a specified string.
 * @param queryEquals Matches if the query segment of the URL is equal to a specified string.
 * @param queryPrefix Matches if the query segment of the URL starts with a specified string.
 * @param querySuffix Matches if the query segment of the URL ends with a specified string.
 * @param urlContains Matches if the URL (without fragment identifier) contains a specified string.
        Port numbers are stripped from the URL if they match the default port number.
 * @param urlEquals Matches if the URL (without fragment identifier) is equal to a specified string.
        Port numbers are stripped from the URL if they match the default port number.
 * @param urlMatches Matches if the URL (without fragment identifier) matches a specified regular
        expression. Port numbers are stripped from the URL if they match the default port number.
        The regular expressions use the <a
        href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
 * @param originAndPathMatches Matches if the URL without query segment and fragment identifier
        matches a specified regular expression. Port numbers are stripped from the URL if they match
        the default port number. The regular expressions use the <a
        href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
 * @param urlPrefix Matches if the URL (without fragment identifier) starts with a specified string.
        Port numbers are stripped from the URL if they match the default port number.
 * @param urlSuffix Matches if the URL (without fragment identifier) ends with a specified string.
        Port numbers are stripped from the URL if they match the default port number.
 * @param schemes Matches if the scheme of the URL is equal to any of the schemes specified in the
        array.
 * @param ports Matches if the port of the URL is contained in any of the specified port lists. For
        example <code>[80, 443, [1000, 1200]]</code> matches all requests on port 80, 443 and in the
        range 1000-1200.
 */
class UrlFilter(
    var hostContains: String? = null,
    var hostEquals: String? = null,
    var hostPrefix: String? = null,
    var hostSuffix: String? = null,
    var pathContains: String? = null,
    var pathEquals: String? = null,
    var pathPrefix: String? = null,
    var pathSuffix: String? = null,
    var queryContains: String? = null,
    var queryEquals: String? = null,
    var queryPrefix: String? = null,
    var querySuffix: String? = null,
    var urlContains: String? = null,
    var urlEquals: String? = null,
    var urlMatches: String? = null,
    var originAndPathMatches: String? = null,
    var urlPrefix: String? = null,
    var urlSuffix: String? = null,
    var schemes: Array<String>? = null,
    var ports: Array<Ports>? = null
)

typealias Ports = Any

external class EventsNamespace
