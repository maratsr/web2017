package model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="adm_roles")
public class Role extends AbstractNamedEntity implements GrantedAuthority {
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 5)
    private String description;

    @Column(name = "comment")
    @Size(max = 255)
    private String comment;

    public Role() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
