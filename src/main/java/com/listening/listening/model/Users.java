package com.listening.listening.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "prueba")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {
    private UUID id;
    private String name;
    private String email;
    private String password;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", table = "users")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", table = "users")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", table = "users")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(name, users.name) &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }
}
