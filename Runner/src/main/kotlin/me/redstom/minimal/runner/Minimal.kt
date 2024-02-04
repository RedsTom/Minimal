package me.redstom.minimal.runner

import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import me.redstom.minimal.compiler.lexer.Lexer
import me.redstom.minimal.compiler.parser.Parser

data object Minimal {
    val lexer = Lexer()
    val parser = Parser()

    val objectMapper = XmlMapper()
        .registerModules(Jdk8Module())
        .setPolymorphicTypeValidator(BasicPolymorphicTypeValidator.builder().build())
        .writerWithDefaultPrettyPrinter()

}
