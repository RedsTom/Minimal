package me.redstom.minimal.compiler.parser.nodes;

public interface ASTNode {
    long line();
    long column();
}
