package Chapter04;
class Song{
	String title,artist,year,country;
	public Song() {}
	public Song(String title,String artist,String year,String country) {
		this.title = title;this.artist = artist;this.year=year;this.country=country;}
	public void show(){
		System.out.println(year+"�� "+country+"������ "+artist+"�� �θ� "+title);
	}
}

public class Exam03 {
	public static void main(String[] args) {
		Song s = new Song("Dancing Queen","ABBA","1978","������");
		s.show();
	}
}
