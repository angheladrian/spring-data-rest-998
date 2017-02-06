package com.anghel.example;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.Identifiable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parent implements Identifiable<Long> {
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "parent_id", nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Child> children = new HashSet<>();
    
//    public Set<Child> getChilden() {
//        return children;
//    }
//
//    public void setChildren(Set<Child> children) {
//        if (this.children == children) {
//            return;
//        }
//        this.children.clear();
//        if (children != null) {
//            this.children.addAll(children);
//        }
//
//    }
    
}
