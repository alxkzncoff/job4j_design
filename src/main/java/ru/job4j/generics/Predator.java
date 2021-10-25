package ru.job4j.generics;

import java.util.Objects;

public class Predator extends Animal {
    private boolean fur;

    public boolean isFur() {
        return fur;
    }

    public void setFur(boolean fur) {
        this.fur = fur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Predator predator = (Predator) o;
        return fur == predator.fur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fur);
    }

    @Override
    public String toString() {
        return "Predator{"
                + "fur=" + fur
                + '}';
    }
}
