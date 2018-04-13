package com.emc.mystic.client;

import com.emc.mystic.uri.parser.RSQLParser;
import com.emc.mystic.uri.parser.ast.ComparisonOperator;
import com.emc.mystic.uri.parser.ast.Node;
import com.emc.mystic.uri.parser.ast.RSQLOperators;

import java.util.Set;

public class MainApp {
    public static void main(String[] args) {

        Set<ComparisonOperator> operators = RSQLOperators.defaultOperators();

        Node rootNode = new RSQLParser().parse("creation_time lt 111 and type eq 'iDRAC,Exsi' and state eq 'IN_PROGRESS'");

        FilterVisitorAdapter<Node> visitor = new FilterVisitorAdapter<>();

        Node result = rootNode.accept(visitor);

        System.out.println("HI");
    }
}
