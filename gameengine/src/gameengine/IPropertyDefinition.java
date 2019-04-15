package gameengine;

/**
 * Interface to represent a single property
 * @author David Miron
 */
public interface IPropertyDefinition<T> {
    String getName();
    T getValue();
    void setValue(T value);
}
