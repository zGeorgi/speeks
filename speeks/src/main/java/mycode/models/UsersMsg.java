package mycode.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users_msg database table.
 */
@Entity
@Table(name="users_msg")
@NamedQuery(name="UsersMsg.findAll", query="SELECT u FROM UsersMsg u")
public class UsersMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;

	//bi-directional many-to-one association to Message
	@ManyToOne
	@JoinColumn(name="msgID")
	private Message message;
 
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="msg_from")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="msg_to")
	private User user2;

	public UsersMsg() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}