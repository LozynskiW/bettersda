package com.betterSDA.model.entity;

import com.betterSDA.model.Person;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentEntity extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    protected AddressEntity addressEntity;

    @ManyToOne
    private TeamEntity teamEntity;

    public StudentEntity(Long id, String firstName, String lastName, String phoneNumber,
                         String email, AddressEntity addressEntity, TeamEntity teamEntity) {

        super(firstName, lastName, phoneNumber, email);
        this.id = id;
        this.teamEntity = teamEntity;
        this.addressEntity = addressEntity;
    }

    public static class Builder {

        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private AddressEntity addressEntity;
        private TeamEntity teamEntity;

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


        public Builder groupEntity(final TeamEntity teamEntity) {
            this.teamEntity = teamEntity;
            return this;
        }

        public StudentEntity build() {
            return new StudentEntity(id, firstName, lastName, phoneNumber, email, addressEntity, teamEntity);
        }
    }

}
