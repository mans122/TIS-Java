class Book {
	String title;
	String author;

	void show() {System.out.println(title+" "+author);}
	public Book() {
		this("����X","����X");
		System.out.println("������ ȣ���");
	}
	//	public Book() {}
	public Book(String title) {
		this(title,"���ڹ̻�");
	}
	public Book(String title,String author) {
		this.title=title;
		this.author=author;
	}
}
public class Ex4_5{
	public static void main(String[] args) {
		Book littlePrince = new Book("�����","�����㺣��");
		Book loveStory = new Book("������");
		Book emptyBook = new Book();
		loveStory.show();
		emptyBook.show();
		littlePrince.show();
	}
}
