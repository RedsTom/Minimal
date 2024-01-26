package me.redstom.minimal.compiler.parser;

import me.redstom.minimal.compiler.parser.ast.IParser;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ParsingRegistry {

    private final List<ParsingRegistryEntry<?>> entries;

    public ParsingRegistry() {
        this.entries = new ArrayList<>();
    }

    public <T> void register(Class<T> type, IParser<T> parser) {
        entries.add(new ParsingRegistryEntry<>(type, parser));
    }

    @SuppressWarnings("unchecked")
    public <T> IParser<T> ofType(Class<T> type) {
        return (IParser<T>) entries.stream()
                .filter(entry -> entry.type().equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(STR."No parser found for type \{type.getName()}"))
                .parser();
    }

    public <T> boolean has(Class<T> type) {
        return entries.stream()
                .anyMatch(entry -> entry.type().equals(type));
    }

    public void unregister(Class<?> type) {
        entries.removeIf(entry -> entry.type().equals(type));
    }

    public void scan(ApplicationContext context) {
        context.getBeansWithAnnotation(Parses.class)
                .forEach((name, bean) -> {
                    if (!IParser.class.isAssignableFrom(bean.getClass())) {
                        throw new RuntimeException(STR."Bean \{name} is not an instance of IParser");
                    }
                    Parses annotation = bean.getClass().getAnnotation(Parses.class);
                    this.registerUnchecked(annotation.value(), bean);
                });
    }

    @SuppressWarnings("unchecked")
    private <T> void registerUnchecked(Class<?> value, Object bean) {
        register((Class<T>) value, (IParser<T>) bean);
    }

    public record ParsingRegistryEntry<T>(
            Class<T> type,
            IParser<T> parser
    ) {
    }
}
