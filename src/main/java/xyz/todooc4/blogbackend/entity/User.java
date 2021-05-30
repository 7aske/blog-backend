package xyz.todooc4.blogbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

/**
 * Blog user or author
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "about")
	private String about;
	@Column(name = "display_name")
	private String displayName;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
	private List<Role> roles;
	
}