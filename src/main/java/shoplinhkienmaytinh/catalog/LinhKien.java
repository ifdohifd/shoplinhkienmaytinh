package shoplinhkienmaytinh.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

@Entity
public class LinhKien extends Product {

	
	public static enum LKType {
		KEYBOARD, MOUSE;
	}

	private String manufactory, image;
	private LKType type;
	@OneToMany(cascade = CascadeType.ALL) //
	private List<Comment> comments = new ArrayList<>();

	@SuppressWarnings("unused")
	private LinhKien() {
	}

	public LinhKien(String name, String image, Money price, String manufactory, LKType type) {

		super(name, price);

		this.image = image;
		this.manufactory = manufactory;
		this.type = type;
	}

	public String getManufactory() {
		return manufactory;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}
	public Iterable<Comment> getComments() {
		return comments;
	}

	public String getImage() {
		return image;
	}

	public LKType getType() {
		return type;
	}
}
