package com.emc.mystic.client;

import java.util.function.Function;

@FunctionalInterface
public interface NameConventionTransformer<T, R>  extends Function<T, R> {
}

