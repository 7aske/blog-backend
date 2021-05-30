package xyz.todooc4.blogbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

/**
 * User comment on a post
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "comment")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Comment extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "comment_id")
	private Integer id;
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "post_fk", referencedColumnName = "post_id")
	@ManyToOne
	private Post post;
	@Column(name = "body")
	private String body;
	
}