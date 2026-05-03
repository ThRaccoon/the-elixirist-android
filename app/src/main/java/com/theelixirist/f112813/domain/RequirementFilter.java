package com.theelixirist.f112813.domain;

import com.theelixirist.f112813.AppContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class RequirementFilter {
    public static <T> List<T> getVisible(
            Collection<T> definitions,
            Function<T, List<Requirement>> getRequirements,
            AppContainer appContainer) {

        List<T> result = new ArrayList<>();
        for (T def : definitions) {
            boolean visible = getRequirements.apply(def).stream()
                    .filter(r -> r.getGate() == Requirement.Gate.VISIBILITY)
                    .allMatch(r -> r.isMet(appContainer));
            if (visible) result.add(def);
        }
        return result;
    }

    public static <T> boolean isPurchasable(
            T definition,
            List<Requirement> requirements,
            AppContainer appContainer) {

        return requirements.stream()
                .filter(r -> r.getGate() == Requirement.Gate.PURCHASABILITY)
                .allMatch(r -> r.isMet(appContainer));
    }
}
