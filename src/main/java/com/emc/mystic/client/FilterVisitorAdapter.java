package com.emc.mystic.client;

import com.emc.mystic.uri.parser.ast.*;

public class FilterVisitorAdapter<R extends Node> extends NoArgRSQLVisitorAdapter<Node> {
    @Override
    public Node visit(AndNode node) {
        return TransformerImpl.convertSnakeCaseToLowCamelCase().apply(node).get();
    }

    @Override
    public Node visit(OrNode node) {
        return TransformerImpl.convertSnakeCaseToLowCamelCase().apply(node).get();
    }

    @Override
    public Node visit(ComparisonNode node) {
        return TransformerImpl.convertSnakeCaseToLowCamelCase().apply(node).get();
    }
}
