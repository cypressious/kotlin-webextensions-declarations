package events

import kotlin.Any

/**
 * Description of a declarative rule for handling events.
 */
external class Rule {
  /**
   * Optional identifier that allows referencing this rule.
   */
  val id: String?

  /**
   * Tags can be used to annotate rules and perform operations on sets of rules.
   */
  val tags: Array<String>?

  /**
   * List of conditions that can trigger the actions.
   */
  val conditions: Array<Any>

  /**
   * List of actions that are triggered if one of the condtions is fulfilled.
   */
  val actions: Array<Any>

  /**
   * Optional priority of this rule. Defaults to 100.
   */
  val priority: Int?
}

typealias Event = Any

/**
 * Filters URLs for various criteria. See <a href='events#filtered'>event filtering</a>. All criteria are case sensitive.
 */
external class UrlFilter {
  /**
   * Matches if the host name of the URL contains a specified string. To test whether a host name component has a prefix 'foo', use hostContains: '.foo'. This matches 'www.foobar.com' and 'foo.com', because an implicit dot is added at the beginning of the host name. Similarly, hostContains can be used to match against component suffix ('foo.') and to exactly match against components ('.foo.'). Suffix- and exact-matching for the last components need to be done separately using hostSuffix, because no implicit dot is added at the end of the host name.
   */
  val hostContains: String?

  /**
   * Matches if the host name of the URL is equal to a specified string.
   */
  val hostEquals: String?

  /**
   * Matches if the host name of the URL starts with a specified string.
   */
  val hostPrefix: String?

  /**
   * Matches if the host name of the URL ends with a specified string.
   */
  val hostSuffix: String?

  /**
   * Matches if the path segment of the URL contains a specified string.
   */
  val pathContains: String?

  /**
   * Matches if the path segment of the URL is equal to a specified string.
   */
  val pathEquals: String?

  /**
   * Matches if the path segment of the URL starts with a specified string.
   */
  val pathPrefix: String?

  /**
   * Matches if the path segment of the URL ends with a specified string.
   */
  val pathSuffix: String?

  /**
   * Matches if the query segment of the URL contains a specified string.
   */
  val queryContains: String?

  /**
   * Matches if the query segment of the URL is equal to a specified string.
   */
  val queryEquals: String?

  /**
   * Matches if the query segment of the URL starts with a specified string.
   */
  val queryPrefix: String?

  /**
   * Matches if the query segment of the URL ends with a specified string.
   */
  val querySuffix: String?

  /**
   * Matches if the URL (without fragment identifier) contains a specified string. Port numbers are stripped from the URL if they match the default port number.
   */
  val urlContains: String?

  /**
   * Matches if the URL (without fragment identifier) is equal to a specified string. Port numbers are stripped from the URL if they match the default port number.
   */
  val urlEquals: String?

  /**
   * Matches if the URL (without fragment identifier) matches a specified regular expression. Port numbers are stripped from the URL if they match the default port number. The regular expressions use the <a href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
   */
  val urlMatches: String?

  /**
   * Matches if the URL without query segment and fragment identifier matches a specified regular expression. Port numbers are stripped from the URL if they match the default port number. The regular expressions use the <a href="https://github.com/google/re2/blob/master/doc/syntax.txt">RE2 syntax</a>.
   */
  val originAndPathMatches: String?

  /**
   * Matches if the URL (without fragment identifier) starts with a specified string. Port numbers are stripped from the URL if they match the default port number.
   */
  val urlPrefix: String?

  /**
   * Matches if the URL (without fragment identifier) ends with a specified string. Port numbers are stripped from the URL if they match the default port number.
   */
  val urlSuffix: String?

  /**
   * Matches if the scheme of the URL is equal to any of the schemes specified in the array.
   */
  val schemes: Array<String>?

  /**
   * Matches if the port of the URL is contained in any of the specified port lists. For example <code>[80, 443, [1000, 1200]]</code> matches all requests on port 80, 443 and in the range 1000-1200.
   */
  val ports: Array<Any>?
}

external class Events
