//import jdk.internal.agent.resources.agent;
import java.lang.Math;
public class person {
	private int age;
	private int id;
	public void setAge(int age) {
		this.age = age;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public int getId() {
		return id;
	}
	person(int _age,int _id){
		age = _age;
		id = _id;
				
	}
	
	
	
	public static void main(String[] args) {
		person c = new person(18,19);
		System.out.println(c.getAge());
		System.out.println(Math.sqrt(4));
	}

}
