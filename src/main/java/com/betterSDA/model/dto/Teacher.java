package com.betterSDA.model.dto;

import com.betterSDA.model.Person;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher extends Person {

    private Long id;

    private Address address;

    private Set<Group> groups;

    public Teacher(Long id, String firstName, String lastName, String phoneNumber, String email,
                   Address address, Set<Group> groups) {
        super(firstName, lastName, phoneNumber, email);
        this.id = id;
        this.groups = groups;
        this.address = address;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private Address address;
        private Set<Group> groups;

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

        public Builder address(final Address address) {
            this.address = address;
            return this;
        }

        public Builder groupEntity(final Set<Group> groups) {
            this.groups = groups;
            return this;
        }

        public Teacher build() {
            return new Teacher(id, firstName, lastName, phoneNumber, email, address, groups);
        }
    }
}
