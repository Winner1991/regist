package message;

public class Content {

	private Integer id;
	private String content;
	private int userId;
	private String username;
	private Double created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getCreated() {
		return created;
	}
	public void setCreated(Double created) {
		this.created = created;
	}
	public Content(Integer id, String content, int userId, String username, Double created) {
		super();
		this.id = id;
		this.content = content;
		this.userId = userId;
		this.username = username;
		this.created = created;
	}
	public Content() {
		super();
	}
	@Override
	public String toString() {
		return "Content [id=" + id + ", content=" + content + ", userId=" + userId + ", username=" + username
				+ ", created=" + created + "]";
	}
	
}
