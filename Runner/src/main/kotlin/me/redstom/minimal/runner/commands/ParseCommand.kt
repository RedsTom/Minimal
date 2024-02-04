package me.redstom.minimal.runner.commands

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import me.redstom.minimal.runner.Minimal
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.readLines

object ParseCommand : Subcommand("parse", "Parses the input file and prints / exports the AST.") {

    val inputFile by argument(
        ArgType.String,
        fullName = "input-file",
        description = "The input file to parse."
    )

    val outputFile by option(
        ArgType.String,
        description = "The output file to write the AST to.",
        shortName = "o",
        fullName = "output-file"
    )

    override fun execute() {
        val file = Path(inputFile)

        if (!Files.exists(file)) {
            println("File $file does not exist.")
            return
        }

        val content = file.readLines().joinToString("\n")

        val tokens = Minimal.lexer.lex(content)
        val ast = Minimal.parser.parse(tokens)

        val astContent = Minimal.objectMapper.writeValueAsString(ast)

        if (outputFile != null) {
            val outputFile = Path(outputFile!!)
            Files.write(outputFile, astContent.toByteArray())

            println("AST successfully written to $outputFile")
        } else {
            println(astContent)
        }

    }

}
