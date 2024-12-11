package sms;

import java.lang.invoke.SwitchPoint;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.bytebuddy.asm.Advice.Exit;

public class App {
	static EntityManager em=Persistence.createEntityManagerFactory("dev").createEntityManager();
	static EntityTransaction et=em.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
		System.out.println("\n\n\n.....Welcome to Student Management System.....\n");
		System.out.println("1) Click 1 for Register the new Student:");
		System.out.println("2) Click 2 for Add Education to Student");
		System.out.println("3) Click 3 for Add Address to Student");
		System.out.println("4) Click 4 for Display All Education Details Of A Student");
		System.out.println("5) Click 5 for Display All Address Details Od a Student");
		System.out.println("6) Click 6 for Login into SMS APP");
		System.out.println("7) Click 7 for Display All Student Of A Department");
		System.out.println("8) Click 8 for Remove Student By StudentId");
		System.out.println("9) Click 9 for Remove Address by Student ID");
		System.out.println("10) Exit");
		System.out.println("Enter Your Choice");
		switch (sc.nextInt()) {
		case 1:
			registerStudent();
			break;
		case 2:
			addEducation();
			break;
		case 3:
			  addAddress();
			break;
		case 4:
			displayAllEducationDetails();
			break;
		case 5:
			displayAllAddressDetails();
			break;
		case 6:
			login();
			break;
		case 7:
			DisplayStudentDetailsByDept();
			break;
		case 8:
			removeStudentByStudentID();
			break;
		case 9:
			removeAddressByStudentID();
			break;
		case 10:	System.err.print("...Thank you for Visiting SMS APP...");
			System.exit(0);
	
			break;

		default:System.err.print("..Invalied choice..");
			break;
		}
		}
	
		
	}
	









	public static void registerStudent() {
		
		System.err.println("Welcome to Student Registration Details");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter First Name:");
		String fname=sc.next();
		System.out.println("Enter Last Name:");
		String lname=sc.next();
		System.out.println("Enter EmailID:");
		String email=sc.next();
		System.out.println("Enter Password:");
		String pwd=sc.next();
		System.out.println("Enter Department Name:");
		String dept=sc.next();
		System.out.println("Enter Student Expected Salary:");
		Long sal=sc.nextLong();
		System.out.println("Enter Phone Number:");
		Long phone=sc.nextLong();
		System.out.println("Enter Experience");
		String exp=sc.next();
				
		
		Student s=new Student(0, fname, lname, email, pwd, null, dept, sal, phone, exp);
		et.begin();
		em.persist(s);
		et.commit();
		
		System.out.println("Student Registered Done Sucessfully..........");
		
	}
	
	
	public static void addEducation() {
		
		 System.out.println("Enter Student Id");
		 Student s=em.find(Student.class, sc.nextInt()); 
		 if(s==null) {
			 System.err.print("...Invalid Student ID...");
			 return ;
		 }
		 System.out.println("Enter Qualification");
		 String q=sc.next();
		 System.out.println("Enter University Name");
		 String uname=sc.next();
		 System.out.println("Enter Student Parcentage");
		 Double per=sc.nextDouble();
		 System.out.println("This Is HigherQualification or not Y/N");
		 boolean h=sc.nextBoolean();
		 
		 Education ed=new Education(0, q, uname, per, h, s);
		 
		 
		 et.begin();
		 em.persist(ed);
		 em.merge(s);
		 et.commit();
		 System.out.println("Education add Successfully to that Student...");
		 
		 
		 
		 
		
		 
	}
	
	private static void addAddress() {
		System.out.println("Enter Student Id:");
		Student s=em.find(Student.class,sc.nextInt());	
		if(s==null) {
			System.err.print("...Invalied Student ID...");
			return;
		}
		System.out.println("Enter Door Number:");
		int dn=sc.nextInt();
		System.out.println("Enter Street Name:");
		String sn=sc.next();
		System.out.println("Enter AddressLine2:");
		String ad2=sc.next();
		System.out.println("Enter City name:");
		String cn=sc.next();
	    System.out.println("Enter State Name:");
		String snn=sc.next();
		System.out.println("Enter Country Name:");
		String cnn=sc.next();
		System.out.println("Enter PostalCode:");
		String pc=sc.next();
		System.out.println("Enter Type oF Address:");
		String td=sc.next();
		
		Address add=new Address(0, dn,sn , ad2, cn, snn, cnn, pc, td, s);
		
		et.begin();
		em.persist(add);
		em.merge(s);
		et.commit();
		System.out.println("Address Add Successfully.......");
	
		
	}
	



	private static void displayAllEducationDetails() {
		System.out.println("Enter Student Id");
		
		Student s=em.find(Student.class, sc.nextInt());
		if(s==null) {
			System.err.print("...Invalid Student ID...");
			return;
		}
		
		Query q= em.createQuery("from Education");
		
		List<Education> el= q.getResultList();
		for(Education e:el) {
			Student ss= e.getStudent();
	
			if(ss.getId()==s.getId()) {
				System.out.println(e.getQualification()+" \n"+e.getUniversityName()+" \n"+e.getPercentage()+" \n");
			}
		}
		
		System.out.println("This are Educations....");
		
	
	}


	



	private static void displayAllAddressDetails() {
		System.out.println("enter Student Id:");
		Student s=em.find(Student.class,sc.nextInt());
		if(s==null) {
			System.err.print("...Invalied Student Id...");
			return;
			
		}
		
		List<Address> al= em.createQuery("from Address").getResultList();
		for(Address a:al) {
			Student ss= a.getStudent();
			if(ss.getId()==s.getId()) {
				
				System.out.println(a.getAddressLine2()+" \n"+a.getCity()+" \n"+a.getState()+" \n"+a.getCountry()+" \n"+a.getPostalCode()+" \n");
				
			}
		}
		
	}


	private static void login() {
		// TODO Auto-generated method stub
		/* 
		 *  em.createQuery(Select s from student s where s.email="email and password=:password
		 *  q.setParameter("email",sc.next());
		 *  q.setParameter("password",sc.next());
		 *  
		 */
		
		Query q= em.createQuery("Select s from Student s where s.email=:email and password=:password");
		System.out.println("Enter Student Email-ID");
		q.setParameter("email", sc.next());
		System.out.println("Enter Password");
		q.setParameter("password", sc.next());
		
		Student ss=(Student) q.getSingleResult();
		
		System.out.println(ss);
		if(true) {
			System.out.println("Login Done Successfully");
		}else {
			System.out.println("Invalied Email-id or Password");
			
		}
	
		
		
	}

	private static void DisplayStudentDetailsByDept() {
		System.out.println("Enter Deparment Name:");
		String deptname=sc.next();
		Student s= em.find(Student.class,deptname);
		if(s==null) {
			System.err.print("...Invalied Department Name...");
			return;
		}
		
		System.out.println(s.getFirstName()+" \n"+s.getLastName()+" \n"+s.getEmail()+" \n"+s.getPhone()+" \n"+s.getDateofjoining()+" \n");
	
		
	}


	private static void removeStudentByStudentID() {
		System.out.println("Enter Student ID");
		Student s=em.find(Student.class, sc.nextInt());
		if(s==null) {
			System.err.print("...Invalied Student Id...");
			return;
			
		}
		List<Student>sl= em.createQuery("from Student").getResultList();
		for(Student ss:sl) {
			if(ss.getId()==s.getId())
			{
				et.begin();
				em.remove(s);
				et.commit();
				
			}
			
		}
		System.out.println("..Student Removed Successfully..");

		
	}

	private static void removeAddressByStudentID() {
		System.out.println("Enter Student ID:");
		
		Student s=em.find(Student.class, sc.nextInt());
		if(s==null) {
			System.err.print("Invalied Student ");
			return;
			
		}
		List<Address>al= em.createQuery("from Address").getResultList();
		for(Address a:al) {
			Student ss= a.getStudent();
			if(ss.getId()==s.getId()) {
				et.begin();
				em.remove(a);
				et.commit();
			}
		}
		System.out.println("Student Adrress removed successfully....");

		
	}





}
