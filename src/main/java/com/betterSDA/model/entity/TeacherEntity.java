package com.betterSDA.model.entity;

import com.betterSDA.model.Person;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Group;
import com.betterSDA.model.dto.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TeacherEntity extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    protected AddressEntity addressEntity;

    @ManyToMany
    private Set<GroupEntity> groupsEntity;

    public TeacherEntity(Long id, String firstName, String lastName, String phoneNumber, String email,
                         AddressEntity addressEntity, Set<GroupEntity> groups) {
        super(firstName, lastName, phoneNumber, email);
        this.id = id;
        this.groupsEntity = groups;
        this.addressEntity = addressEntity;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private AddressEntity addressEntity;
        private Set<GroupEntity> groupsEntity;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder addressEntity(final AddressEntity addressEntity) {
            this.addressEntity = addressEntity;
            return this;
        }

        public Builder groupEntity(final Set<GroupEntity> groupsEntity) {
            this.groupsEntity = groupsEntity;
            return this;
        }

        public TeacherEntity build() {
            return new TeacherEntity(id, firstName, lastName, phoneNumber, email, addressEntity, groupsEntity);
        }
    }
}
