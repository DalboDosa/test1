package C_level;
import java.util.*;
public class C_022 {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int val1=0;
				int val2 =0; int max=0; int min=0;
        
        String num2= sc.nextLine();// 숫자넣기
        int num=Integer.parseInt(num2);
        int [][] value = new int [num][4];//행 입력 열은 4개짜리
        
        for(int i=0 ; i<num; i++)
        {
        	for(int j=0 ; j<4 ; j++)
        	{
        		String input=sc.next();
        		value[i][j]=Integer.parseInt(input);
        		if(i==0&&j==0)
        		{
        			val1=value[i][j]; // 첫번째 값
        		}
        		
        		if(i==num-1&&j==1)
        		{
        			val2=value[i][j]; // 마지막 줄 두번째 값
        		}
        		if(i==0&&j==0)
        		{
        			max=value[i][j];
        			min=value[i][j];
        		}
        		if(value[i][j]<=min)
        		{
        			min=value[i][j];
        		}
        		if(value[i][j]>=max)
        		{
        			max=value[i][j];
        		}
        		
        		
        	}
        }
        
        
        System.out.println(val1 +" "+val2+" "+max+ " "+min);
        sc.close();
	}
}

