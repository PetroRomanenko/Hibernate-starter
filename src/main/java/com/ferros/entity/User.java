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
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long id;
    @Column(unique = true)
    private String username;
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;
    @Type(type = "ferros" )
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id")// company_id п умолчанию использует
    private Company company;

}
