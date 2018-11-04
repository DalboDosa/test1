package C_level;
import java.util.*;
public class C_049 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int floor=1; // 층 차이
        int sum=0; // 총 이동 층 수
        int temp=1; // 이동 하기 전에 엘레베이터 위치 
        for(int i = 0; i<num ; i++)
        {
        	int input = sc.nextInt();
        	if(temp>input) // 있는 위치보다 내려간다면 
        	{
        		floor = temp-input;
        	}
        	else if(temp<input)// 있는 위치보다 올라간다면 
        	{
        		floor=input-temp;
        	}
     
        	temp=input; // 위치 저장 
        	sum+=floor; // 총 층수의 합 
        }
        System.out.println(sum);
        sc.close();
       

	}

}
