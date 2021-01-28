package com.mtek.poc.users_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Emrah TOY
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users.users")
public class UserModel {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("avatar")
    private String avatar;
}
