package events

/**
 * Description of a declarative rule for handling events.
 */
external class Rule {
    /**
     * Optional identifier that allows referencing this rule.
     */
    var id: String?

    /**
     * Tags can be used to annotate rules and perform operations on sets of rules.
     */
    var tags: Array<String>

    /**
     * List of conditions that can trigger the actions.
     */
    var conditions: Array<Any>

    /**
     * List of actions that are triggered if one of the condtions is fulfilled.
     */
    var actions: Array<Any>

    /**
     * Optional priority of this rule. Defaults to 100.
     */
    var priority: Int?
}

/**
 * An object which allows the addition and removal of listeners for a Chrome event. */
typealias Event = Any

/**
 * Filters URLs for various criteria. See <a href='events#filtered'>event filtering</a>. All criteria are case sensitive.
 */
external class UrlFilter {
    /**
     * Matches if the host name of the URL contains a specified string. To test whether a host name component has a prefix 'foo', use hostContains: '.foo'. This matches 'www.foobar.com' and 'foo.com', because an implicit dot is added at the beginning of the host name. Similarly, hostContains can be used to match against component suffix ('foo.') and to exactly match against components ('.foo.'). Suffix- and exact-matching for the last components need to be done separately using hostSuffix, because no implicit dot is added at the end of the host name.
     */
    var hostContains: String?

    /**
     * Matches if the host name of the URL is equal to a specified string.
     */
    var hostEquals: String?

    /**
     * Matches if the host name of the URL starts with a specified string.
     */
    var hostPrefix: String?

    /**
     * Matches if the host name of the URL ends with a specified string.
     */
    var hostSuffix: String?

    /**
     * Matches if the path segment of the URL contains a specified string.
     */
    var pathContains: String?

    /**
     * Matches if the path segment of the URL is equal to a specified string.
     */
    var pathEquals: String?

    /**
     * Matches if the path segment of the URL starts with a specified string.
     */
    var pathPrefix: String?

    /**
     * Matches if the path segment of the URL ends with a specified string.
     */
    var pathSuffix: String?

    /**
     * Matches if the query segment of the URL contains a specified string.
     */
    var queryContains: String?

    /**
     * Matches if the query segment of the URL is equal to a specified string.
     */
    var queryEquals: String?

    /**
     * Matches if the query segment of the URL starts with a specified string.
     */
    var queryPrefix: String?

    /**
     * Matches if the query segment of the URL ends with a specified string.
     */
    var querySuffix: String?

    /**
     * Matches if the URL (without fragment identifier) contains a specified string. Port numbers are stripped from the URL if they match the default port number.
     */
    var urlContains: String?

    /**
     * Matches if the URL (without fragment identifier) is equal to a specified string. Port numbers are stripped from the URL if they match the default port number.
     */
    var urlEquals: String?

    /**
     * Matches if the URL (without fragment identifier) matches a specified regular expression. Port numbers are stripped from the URL if they match the default port number. The regular expressions use the <a href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
     */
    var urlMatches: String?

    /**
     * Matches if the URL without query segment and fragment identifier matches a specified regular expression. Port numbers are stripped from the URL if they match the default port number. The regular expressions use the <a href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
     */
    var originAndPathMatches: String?

    /**
     * Matches if the URL (without fragment identifier) starts with a specified string. Port numbers are stripped from the URL if they match the default port number.
     */
    var urlPrefix: String?

    /**
     * Matches if the URL (without fragment identifier) ends with a specified string. Port numbers are stripped from the URL if they match the default port number.
     */
    var urlSuffix: String?

    /**
     * Matches if the scheme of the URL is equal to any of the schemes specified in the array.
     */
    var schemes: Array<String>

    /**
     * Matches if the port of the URL is contained in any of the specified port lists. For example <code>[80, 443, [1000, 1200]]</code> matches all requests on port 80, 443 and in the range 1000-1200.
     */
    var ports: Array<Ports>
}

typealias Ports = Any

external class EventsNamespace
