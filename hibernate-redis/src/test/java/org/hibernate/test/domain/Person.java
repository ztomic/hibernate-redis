package org.hibernate.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

/**
 * org.hibernate.test.domain.Person
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:54
 */
@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Person implements Serializable {

    private static final long serialVersionUID = -8245742950718661800L;

    @Id
    @GeneratedValue
    private Long id;

    private int age;
    private String firstname;
    private String lastname;

    private Float weight = 77.7f;
    private Double height = 188.8d;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<Event>();

    @CollectionTable(name = "EmailAddressSet", joinColumns = @JoinColumn(name = "PersonId"))
    @ElementCollection(targetClass = String.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<String> emailAddresses = new HashSet<String>();

    @CollectionTable(name = "PhoneNumberSet", joinColumns = @JoinColumn(name = "ProductItemId"))
    @ElementCollection(targetClass = PhoneNumber.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

    @CollectionTable(name = "TailsManList", joinColumns = @JoinColumn(name = "PersonId"))
    @ElementCollection(targetClass = String.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> tailsmans = new ArrayList<String>();

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj instanceof Person) && hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? id.hashCode()
                : Objects.hash(firstname, lastname, weight, height, age);
    }
}
