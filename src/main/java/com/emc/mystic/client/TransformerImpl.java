package com.emc.mystic.client;

import com.emc.mystic.uri.parser.ast.ComparisonNode;
import com.emc.mystic.uri.parser.ast.LogicalNode;
import com.emc.mystic.uri.parser.ast.Node;
import com.google.common.base.CaseFormat;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransformerImpl {
    public static NameConventionTransformer<Node, Optional<Node>> convertSnakeCaseToLowCamelCase() {
        return node -> {
            if (node instanceof ComparisonNode) {
                return Optional.of(node)
                               .map(ComparisonNode.class::cast)
                               .map(comparisonNode -> comparisonNode.withSelector(
                                       convertSnakeCaseToLowCamelCase(comparisonNode.getSelector())));
            } else {
                return Optional.of(node)
                               .map(LogicalNode.class::cast)
                               .map(TransformerImpl::convertLogicalNodeToLowCamelCase);
            }

        };
    }

    private static String convertSnakeCaseToLowCamelCase(String name) {
        String upperName =  CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);

        char c[] = upperName.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

    private static Node convertLogicalNodeToLowCamelCase(LogicalNode node) {
        Stream<Node> children = node.getChildren()
                                    .stream()
                                    .map(child -> TransformerImpl.convertSnakeCaseToLowCamelCase().apply(child).get());

        return node.withChildren(children.collect(Collectors.toList()));
    }
}
