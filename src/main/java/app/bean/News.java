package app.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;
	
	private String brief;

	private String text;
	
	public News() {
	}

	public News(String title, String brief) {
		this.title = title;
		this.brief = brief;
	}

	public News(Integer id, String title, String brief, String text) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brief, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", brief=" + brief + "]";
	}	
	
	

}
