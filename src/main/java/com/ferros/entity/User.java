package com.ferros.entity;


import com.ferros.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonBlobType;
import com.vladmihalcea.hibernate.type.json.internal.JsonBinarySqlTypeDescriptor;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",schema = "public")
@TypeDef(name = "ferros",typeClass = JsonBinaryType.class)
public class User {
    @Id
    private String username;
    @Embedded
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;
    @Type(type = "ferros" )
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

}
