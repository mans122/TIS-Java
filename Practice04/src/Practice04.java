//1������
class Mouse{
	String leftButton,rightButton,optical;
	public void mouseMove() {}
}
//2������
class WheelMouse extends Mouse{
	public WheelMouse() {}
	public void scrollWheel() {}
}
//3������
class LaserMouse extends WheelMouse{
	public LaserMouse() {
		this.optical = "Laser";
	}
}
//4������
public class Practice04 {
	public static void main(String[] args) {
		Mouse m = new Mouse();
		WheelMouse wm = new WheelMouse();
		LaserMouse lm = new LaserMouse();
		//5������
		WheelMouse[] wm2 = new WheelMouse[3];
		wm2[0] = new WheelMouse();
		wm2[1] = new WheelMouse();
		wm2[2] = new WheelMouse();
	}

}
