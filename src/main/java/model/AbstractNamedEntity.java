package model;

import org.hibernate.validator.constraints.SafeHtml;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractIdEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 255)
    //@SafeHtml(groups = {View.ValidatedRestUI.class})
    protected String name;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), getId(), name);
    }
}