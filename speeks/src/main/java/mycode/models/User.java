package mycode.models;

import java.io.Serializable;
import javax.persistence.*;



import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private int idUser;

	private String еmail;

	private String avatar;

	private String firstName;

	private String pass;

	private String state;

	//bi-directional many-to-one association to UsersMsg
	@OneToMany(mappedBy="user1", fetch = FetchType.EAGER)
	private List<UsersMsg> usersMsgs1= new ArrayList<UsersMsg>();

	//bi-directional many-to-one association to UsersMsg
	@OneToMany(mappedBy="user2", fetch = FetchType.EAGER)
	private List<UsersMsg> usersMsgs2= new ArrayList<UsersMsg>();

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getЕmail() {
		return this.еmail;
	}

	public void setЕmail(String еmail) {
		this.еmail = еmail;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<UsersMsg> getUsersMsgs1() {
		return this.usersMsgs1;
	}

	public void setUsersMsgs1(List<UsersMsg> usersMsgs1) {
		this.usersMsgs1 = usersMsgs1;
	}
	
	public List<UsersMsg> getUsersMsgs2() {
		return this.usersMsgs2;
	}

	public void setUsersMsgs2(List<UsersMsg> usersMsgs2) {
		this.usersMsgs2 = usersMsgs2;
	}

}