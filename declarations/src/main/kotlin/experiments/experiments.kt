package experiments

class ExperimentAPI(
    var schema: ExperimentURL,
    var parent: Parent? = null,
    var child: Child? = null
)

typealias ExperimentURL = String

typealias APIPaths = Array<APIPath>

typealias APIPath = Array<String>

typealias APIEvents = Array<APIEvent>

typealias APIEvent = String

typealias APIParentScope = String

typealias APIChildScope = String

class Parent(
    var events: APIEvents? = null,
    var paths: APIPaths? = null,
    var script: ExperimentURL,
    var scopes: Array<APIParentScope>? = null
)

class Child(
    var paths: APIPaths,
    var script: ExperimentURL,
    var scopes: Array<APIChildScope>
)

external class ExperimentsNamespace
