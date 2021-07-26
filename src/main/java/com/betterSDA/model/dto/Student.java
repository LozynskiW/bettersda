package com.betterSDA.model.dto;

import com.betterSDA.model.Person;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.model.entity.GroupEntity;
import com.betterSDA.model.entity.StudentEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends Person {

    private Long id;

    private Group group;

    private Address address;

    public Student(Long id, String firstName, String lastName, String phoneNumber, String email, Address address, Group group) {
        super(firstName, lastName, phoneNumber, email);
        this.id = id;
        this.group = group;
        this.address = address;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private Address address;
        private Group group;

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

        public Builder groupEntity(final Group group) {
            this.group = group;
            return this;
        }

        public Student build() {
            return new Student(id, firstName, lastName, phoneNumber, email, address, group);
        }
    }
}
