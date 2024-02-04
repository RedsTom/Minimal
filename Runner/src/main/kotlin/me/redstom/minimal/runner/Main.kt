package me.redstom.minimal.runner

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli
import me.redstom.minimal.runner.commands.LexCommand
import me.redstom.minimal.runner.commands.ParseCommand

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("MinimalCli")

    parser.subcommands(LexCommand, ParseCommand)

    try {
        parser.parse(args)
    } catch (e: Exception) {
        System.err.println(e.message)
    }
}
