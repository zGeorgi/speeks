package mycode.models;

import java.io.Serializable;
import javax.persistence.*;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_msg")
	private int idMsg;

	private String readMsg;

	@Temporal(TemporalType.TIMESTAMP)
	private Date umsgTime;

	@Lob
	private String userMsg;

	//bi-directional many-to-one association to UsersMsg
	@OneToMany(mappedBy="message", fetch = FetchType.EAGER)
	private List<UsersMsg> usersMsgs = new ArrayList<UsersMsg>();

	public Message() {
	}

	public int getIdMsg() {
		return this.idMsg;
	}

	public void setIdMsg(int idMsg) {
		this.idMsg = idMsg;
	}

	public String getReadMsg() {
		return this.readMsg;
	}

	public void setReadMsg(String readMsg) {
		this.readMsg = readMsg;
	}

	public Date getUmsgTime() {
		return this.umsgTime;
	}

	public void setUmsgTime(Date umsgTime) {
		this.umsgTime = umsgTime;
	}

	public String getUserMsg() {
		return this.userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public List<UsersMsg> getUsersMsgs() {
		return this.usersMsgs;
	}

	public void setUsersMsgs(List<UsersMsg> usersMsgs) {
		this.usersMsgs = usersMsgs;
	}

}