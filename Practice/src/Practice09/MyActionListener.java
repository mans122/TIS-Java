package Practice09;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//input��ư�� MyActionListener�� ��������Ƿ� �̺�Ʈ�� �߻��ϸ� �����
public class MyActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<4;i++) {
			//bk_num��  ����ڰ� �Է��� �ؽ�Ʈ���� ���������� ��ȯ�ؼ� ������.
			Practice04.bk_num[i] = Integer.parseInt(Practice04.tf_num[i].getText());
			Practice04.sum+=Practice04.bk_num[i]; // sum�� ������ ������ ��� ������.
		}
		for(int i=0;i<4;i++) {
			//�б��� �ۼ�Ʈ���� �������� �бⰪ/�бⰪ��*360 ��.
			Practice04.bkp[i] = (360*Practice04.bk_num[i])/Practice04.sum;
			Practice04.bkp[i] = Math.round(Practice04.bkp[i]);//Math.round�޼ҵ�� �Ҽ��� �ݿø�����
			Practice04.gap += Practice04.bkp[i];//gap�� ��� �бⰪ�� ���ؼ� �־���
			System.out.println(Practice04.bkp[i]);//�бⰪ�� �ߵ����� Ȯ���ϱ����� �ڵ�
		}
		Practice04.gap = 360-Practice04.gap;//�Ҽ����ݿø��� �бⰪ�� ���� 360�� �ȵɰ�� �� ���̰��� ���ϱ����� �ڵ�
		System.out.println("gap :" + Practice04.gap);//gap���� �� ���� Ȯ���ϱ����� �ڵ�
		Practice04.cp.repaint();//�� ���� �Է��������� ��Ʈ������� �г��� �ٽñ׷�����.
	}
}
