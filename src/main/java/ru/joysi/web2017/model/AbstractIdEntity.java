package ru.joysi.web2017.model;

import ru.joysi.web2017.concept.IdStructure;
import org.hibernate.Hibernate;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractIdEntity implements IdStructure {

    @Id
    @SequenceGenerator(name = "ids_generator", sequenceName = "ids_generator", allocationSize = 1, initialValue = 1000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    // PROPERTY access for id due to bug: https://hibernate.atlassian.net/browse/HHH-3718
    @Access(value = AccessType.PROPERTY)
    private long id;

    protected AbstractIdEntity() {
    }

    protected AbstractIdEntity(Long id) {
        this.id = id;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getInfo() {
        return "Model structure with identity auto generated key";
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(Hibernate.getClass(o))) return false;

        AbstractIdEntity that = (AbstractIdEntity) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (int) ((getId()  == null) ? 0 : getId());
    }
}
