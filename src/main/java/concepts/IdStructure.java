package concepts;

public interface IdStructure {
    Long getId();

    void setId(Long id);

    String getInfo();

    default boolean isNew() {
        return getId() == null;
    }
}
