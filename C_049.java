package C_level;
import java.util.*;
public class C_049 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int floor=1; // �� ����
        int sum=0; // �� �̵� �� ��
        int temp=1; // �̵� �ϱ� ���� ���������� ��ġ 
        for(int i = 0; i<num ; i++)
        {
        	int input = sc.nextInt();
        	if(temp>input) // �ִ� ��ġ���� �������ٸ� 
        	{
        		floor = temp-input;
        	}
        	else if(temp<input)// �ִ� ��ġ���� �ö󰣴ٸ� 
        	{
        		floor=input-temp;
        	}
     
        	temp=input; // ��ġ ���� 
        	sum+=floor; // �� ������ �� 
        }
        System.out.println(sum);
        sc.close();
       

	}

}
