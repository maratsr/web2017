package ru.joysi.web2017.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="adm_users")
public class User extends AbstractNamedEntity{

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "fio")
    @Size(max = 255)
    private String fio;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "activated", nullable = false)
    private boolean activated = true;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @Column(name = "date_create", nullable = false)
    @NotNull//(groups = {View.ValidatedUI.class, Default.class})
    //@JsonView(View.JsonREST.class)
    private LocalDateTime dateCreate;

    @Column(name = "date_activate")
    @NotNull//(groups = {View.ValidatedUI.class, Default.class})
    //@JsonView(View.JsonREST.class)
    private LocalDateTime dateActivate;

    @Column(name = "date_modify")
    @NotNull//(groups = {View.ValidatedUI.class, Default.class})
    //@JsonView(View.JsonREST.class)
    private LocalDateTime dateModify;

    @Column(name = "date_modify")
    @NotNull//(groups = {View.ValidatedUI.class, Default.class})
    //@JsonView(View.JsonREST.class)
    private LocalDateTime lastActivity;

    @Column(name = "date_reset")
    @NotNull//(groups = {View.ValidatedUI.class, Default.class})
    //@JsonView(View.JsonREST.class)
    private LocalDateTime dateReset;

    @Column(name = "avatar_img")
    @Size(max = 255)
    private String avatarImg;

    @Column(name = "activation_key")
    @Size(max = 32)
    private String activationKey;

    @Column(name = "reset_key")
    @Size(max = 32)
    private String resetKey;

    @Column(name = "reset_key")
    @Size(max = 255)
    private String comment;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(Long id, String name, String password, String fio, boolean enabled, boolean activated, String email, LocalDateTime dateCreate,
                LocalDateTime dateActivate, LocalDateTime dateModify, LocalDateTime lastActivity, LocalDateTime dateReset, String avatarImg,
                String activationKey, String resetKey, String comment, Language language, Collection<Role> roles) {
        super(id, name);
        this.password = password;
        this.fio = fio;
        this.enabled = enabled;
        this.activated = activated;
        this.email = email;
        this.dateCreate = dateCreate;
        this.dateActivate = dateActivate;
        this.dateModify = dateModify;
        this.lastActivity = lastActivity;
        this.dateReset = dateReset;
        this.avatarImg = avatarImg;
        this.activationKey = activationKey;
        this.resetKey = resetKey;
        this.comment = comment;
        this.language = language;
        setRoles(roles);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "adm_user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public void setRoles(Collection<Role> roles) {
        if (CollectionUtils.isEmpty(roles))
            this.roles = new HashSet<>();
        else
            this.roles.addAll(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateActivate() {
        return dateActivate;
    }

    public void setDateActivate(LocalDateTime dateActivate) {
        this.dateActivate = dateActivate;
    }

    public LocalDateTime getDateModify() {
        return dateModify;
    }

    public void setDateModify(LocalDateTime dateModify) {
        this.dateModify = dateModify;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public LocalDateTime getDateReset() {
        return dateReset;
    }

    public void setDateReset(LocalDateTime dateReset) {
        this.dateReset = dateReset;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public void setName(String name) {
        super.setName(name.toLowerCase(Locale.ENGLISH));
    }
}
