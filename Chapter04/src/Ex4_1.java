class Circle{
	int radius;
	String name;
	public Circle() {}
	public double getArea() {
		return 3.14*radius*radius;
	}
}
public class Ex4_1 {
	public static void main(String[] args) {
		Circle pizza;
		pizza = new Circle();
		pizza.name="��������";
		pizza.radius=10;
		double area = pizza.getArea();
		System.out.println(pizza.name+"�� ���� :"+area);

		Circle donut = new Circle();
		donut.name = "���ӵ���";
		donut.radius=2;
		area = donut.getArea();
		System.out.println(donut.name+"�� ���� :"+area);

	}

}
