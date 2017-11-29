package de.rakhman.webextensions

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.ContentsService
import org.eclipse.egit.github.core.service.RepositoryService
import org.eclipse.egit.github.core.util.EncodingUtils
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {

    val arguments = Args()
    JCommander.newBuilder()
            .addObject(arguments)
            .build()
            .parse(*args)

    val streams = when (SourceArg.valueOf(arguments.source)) {
        SourceArg.Github -> getFromGithub(arguments.apiToken)
        SourceArg.File -> getFromFiles(File(arguments.filePath))
    }

    val gson = Gson()
    val type = object : TypeToken<List<Namespace>>() {}.type

    val list = streams
            .flatMap {
                InputStreamReader(it).use { reader ->
                    gson.fromJson<List<Namespace>>(reader, type).asSequence()
                }
            }
            .toList()

    val dir = File(arguments.outputPath).apply {
        mkdirs()
        deleteRecursively()
        mkdir()
    }

    Generator(dir).generate(list)
}

class Args {
    @Parameter(names = arrayOf("-s", "--source"))
    var source: String = SourceArg.Github.name

    @Parameter(names = arrayOf("--apiToken"), description = "Optional Github OAuth 2 API token. Not using a token can lead to hitting the quota limit quickly.")
    var apiToken: String? = null

    @Parameter(names = arrayOf("--filePath"), description = "Optional path to schema files. Default ist 'schemas'")
    var filePath: String? = "schemas"

    @Parameter(names = arrayOf("-o", "--outputPath"), description = "Optional path for output. Default is 'declarations/src/main/kotlin'")
    var outputPath: String? = "declarations/src/main/kotlin"
}

enum class SourceArg {
    Github, File
}

private fun File.deleteRecur() {
    Files.walk(this.toPath(), FileVisitOption.FOLLOW_LINKS)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach { it.delete() }
}

private fun getFromFiles(file: File): Sequence<InputStream> {
    return file
            .listFiles { f: File -> f.extension == "json" }
            .asSequence()
            .map { it.inputStream() }
}

private fun getFromGithub(token: String?): Sequence<InputStream> {
    val client = GitHubClient().apply { token?.let { setOAuth2Token(it) } }

    val repository = RepositoryService(client).getRepository("mozilla", "gecko-dev")

    val contentsService = ContentsService(client)

    val contents =
            contentsService.getContents(repository, "browser/components/extensions/schemas/") +
                    contentsService.getContents(repository, "toolkit/components/extensions/schemas/")

    return contents
            .asSequence()
            .filter { it.name.endsWith(".json") }
            .flatMap { contentsService.getContents(repository, it.path).asSequence() }
            .map { ByteArrayInputStream(EncodingUtils.fromBase64(it.content)) }
}

