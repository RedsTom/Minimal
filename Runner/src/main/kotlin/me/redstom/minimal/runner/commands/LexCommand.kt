package me.redstom.minimal.runner.commands

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import me.redstom.minimal.runner.Minimal
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.readLines

object LexCommand : Subcommand("lex", "Lexes the input file and prints the tokens.") {

    val inputFile by argument(
        ArgType.String,
        fullName = "input-file",
        description = "The input file to lex."
    )

    override fun execute() {
        val file = Path(inputFile)

        if (!Files.exists(file)) {
            println("File $file does not exist.")
            return
        }

        val content = file.readLines().joinToString("\n")
        val tokens = Minimal.lexer.lex(content)

        val tokensByLine = tokens.groupBy { it.line }
        val maxLineLength = tokens.maxOfOrNull { it.line }.toString().length
        tokensByLine.forEach { (line, tokens) ->
            val lineShown = String.format("%0${maxLineLength}d", line)
            println("${lineShown}: ${tokens.joinToString(" ") { it.type.stringifier().apply(it) }}")
        }
    }
}
