package xyz.todooc4.blogbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

/**
 * User permissions
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "role")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Role extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "role_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_fk"), inverseJoinColumns = @JoinColumn(name = "user_fk"))
	private List<User> users;
	
}