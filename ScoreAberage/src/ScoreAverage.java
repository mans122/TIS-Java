
public class ScoreAverage {
	public static void main(String[] args) {
		double[][] score= {{3.3,3.4},
				{3.5,3.6},
				{3.7,4.0},
				{4.1,4.2}};
		
		double sum=0;
//		for(int year=0;year<score.length;year++) {
//			for(int term=0;term<score[year].length;term++) {
//				sum+=score[year][term];
//			}
//		}
//		int n=score.length;
//		int m=score[0].length;
//		System.out.println("4�� ��ü ��� ������"+sum/(n*m));
//		
		for(double[] s1:score) {
			for(double s2:s1) {
				System.out.print(s2+",");
			}
			System.out.println();
		}
	}

}
