package project.web.app.bean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;

	private String brief;

	private String text;

	private String image;

	private Long authorId;

	public News() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {

		this.image = image;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		News news = (News) o;

		return new EqualsBuilder().append(id, news.id).append(title, news.title).append(brief, news.brief).append(text, news.text).append(image, news.image).append(authorId, news.authorId).isEquals();
	}

	@Override
	public int hashCode() {

		return new HashCodeBuilder(17, 37).append(id).append(title).append(brief).append(text).append(image).append(authorId).toHashCode();
	}

	@Override
	public String toString() {

		return "News{" +
				"id=" + id +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", text='" + text + '\'' +
				", image='" + image + '\'' +
				", authorId=" + authorId +
				'}';
	}
}


