import java.sql.*;

public class Jdbc01 {
	/*
	static Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String uid = "hr";
	String pass = "1234";
	String sql = "select * from members";
	*/

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");// ����
			System.out.println("����Ϸ�");

			//excuteQuery�ȿ� ������ ������� ������ ���ϴ� sql���� �ְ� executeQuery���� �ִ°� �̻�����
			stmt =conn.createStatement();
			//Insert��
			//stmt.executeQuery("insert into student(id,name,dept) values('1234567','�����','�����а�')");
			//Update��
			//stmt.executeQuery("update student set dept='ö�а�' where name='�̱���'");
			//Delete��
			//stmt.executeQuery("Delete student where name='�̱���'");
			//Select��
			ResultSet rs = stmt.executeQuery("select * from student");
			System.out.println("== �й� ==== �̸� === �а� ==");
			while(rs.next()) {
				//System.out.print(rs.getString("id")); //Į�� �̸���� Į�� �ε����� ��� ����
				System.out.print(rs.getString(1));
				System.out.print("\t     ");
				System.out.print(rs.getString("name"));
				System.out.print("\t ");
				System.out.println(rs.getString("dept"));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("����ȵ�");
			e.printStackTrace();
		}
	}
}
