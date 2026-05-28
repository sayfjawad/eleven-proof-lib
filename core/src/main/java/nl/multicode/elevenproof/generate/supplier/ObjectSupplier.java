package nl.multicode.elevenproof.generate.supplier;

/**
 * Generic supplier abstraction used by number generators. Similar in spirit to
 * {@link java.util.function.Supplier} but kept domain-specific so implementations can be wired
 * by interface in this library.
 *
 * @param <T> the type of object produced by {@link #supply()}
 */
public interface ObjectSupplier<T> {

    /**
     * Supplies a value of type {@code T}.
     *
     * @return the supplied value
     */
    T supply();
}
