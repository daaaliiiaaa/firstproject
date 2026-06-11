package MyPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.xdevapi.Statement;

public class MyTestCases {	WebDriver driver = new ChromeDriver();

String website = "https://automationteststore.com/";

Random rand = new Random();

Connection con;

 java.sql.Statement stmt;

ResultSet rs;

String firstname;
String lastname;

// dalia
String password;

String email;

String address ; 
String numberAsText ; 

@BeforeTest

public void mySetup() throws SQLException {

	driver.get(website);

	driver.manage().window().maximize();

	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "abed");
}

@Test(enabled = false, priority = 1)
public void InsertData() throws SQLException {

	stmt = con.createStatement();
	
 

	String query = "INSERT INTO customers (" + "customerNumber, customerName, contactLastName, contactFirstName, "
			+ "phone, addressLine1, addressLine2, city, state, postalCode, "
			+ "country, salesRepEmployeeNumber, creditLimit" + ") VALUES (" + "991, " + "'Aqaba Tech Supplies', "
			+ "'Saqa', " + "'Abdulrahim ', " + "'+962 7 9000 1234', " + "'University Street', " + "NULL, "
			+ "'Amman', " + "NULL, " + "'11953', " + "'Jordan', " + "NULL, " + "50000.00" + ");";

	stmt.executeUpdate(query);

	// driver.navigate().to("https://automationteststore.com/index.php?rt=account/create");

}

@Test(priority = 2, enabled = false)
public void updateData() throws SQLException {

	stmt = con.createStatement();

	String query = "update customers set contactFirstName ='Mohammad' where customerNumber = 991 ";

	stmt.executeUpdate(query);

}

@Test(priority = 3, enabled = false)
public void DeleteData() throws SQLException {

	stmt = con.createStatement();

	String query = "delete from customers where customerNumber = 991  ";

	stmt.executeUpdate(query);

}

@Test(priority = 3, enabled = false)
public void DeleteData2() throws SQLException {

	stmt = con.createStatement();

	String query = "delete from customers where customerNumber = 991  ";

	stmt.executeUpdate(query);

}

@Test(priority = 4)
public void readData() throws SQLException {
	stmt = con.createStatement();
	
String [] randomIDs = {"121","141","124","125","171"}; 	
	
	int randomindex = rand.nextInt(randomIDs.length); 
	String therandomID = randomIDs[randomindex];

	String query = "select * from customers where customerNumber = "+therandomID;

	rs = stmt.executeQuery(query);

	while (rs.next()) {

		firstname = rs.getString("contactFirstName");

		password = rs.getString("customerNumber") + rs.getString("contactLastName");
		
	int a =	rand.nextInt(233); 
	int c=	rand.nextInt(321); 
	
int numberrrrrrrr= a+c ; 
numberAsText = Integer.toString(numberrrrrrrr); 
		email = rs.getString("contactFirstName").trim() + rs.getString("contactLastName").trim()
				+numberAsText+ "@gmail.com";
		
		lastname = rs.getString("contactLastName") ; 
		address = rs.getString("addressLine1") ; 

		
	}

}

@Test(priority = 5)

public void Signup() throws InterruptedException {

	driver.navigate().to("https://automationteststore.com/index.php?rt=account/create");

	driver.findElement(By.name("loginname")).sendKeys(firstname.trim()+numberAsText);

	driver.findElement(By.name("password")).sendKeys(password);

	driver.findElement(By.id("AccountFrm_email")).sendKeys(email.trim());
	driver.findElement(By.id("AccountFrm_firstname")).sendKeys(firstname.trim()); 
	
	driver.findElement(By.id("AccountFrm_lastname")).sendKeys(lastname.trim()); 

	driver.findElement(By.id("AccountFrm_address_1")).sendKeys(address.trim()); 
	
	driver.findElement(By.id("AccountFrm_city")).sendKeys("aajksdha"); 
	
	WebElement selectState = driver.findElement(By.id("AccountFrm_zone_id")); 
	Select myselctor = new Select(selectState);
	
	myselctor.selectByIndex(1);
	
	driver.findElement(By.id("AccountFrm_postcode")).sendKeys("123Abc"); 
	
	driver.findElement(By.id("AccountFrm_confirm")).sendKeys(password); 
	
	driver.findElement(By.id("AccountFrm_agree")).click(); 
	
	driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click")).click();
	
	Thread.sleep(2000); 
	
	String ActualValue = driver.getCurrentUrl(); 
	
	System.out.println(ActualValue);
	
	Assert.assertEquals(ActualValue, "https://automationteststore.com/index.php?rt=account/success");
	Assert.assertEquals(ActualValue.contains("success"), true);

	
	
}

@Test(priority = 6 , enabled = true)

public void Logout() {
	
driver.navigate().to("https://automationteststore.com/index.php?rt=account/logout");

String ActualValue = driver.getCurrentUrl(); 

Assert.assertEquals(ActualValue.contains("logout"), true); 
	
}

@Test(priority = 7)

public void Login() throws InterruptedException {
	driver.navigate().to("https://automationteststore.com/index.php?rt=account/login");
	
	driver.findElement(By.id("loginFrm_loginname")).sendKeys(firstname.trim()+numberAsText);
	driver.findElement(By.id("loginFrm_password")).sendKeys(password); 
	
	driver.findElement(By.xpath("//button[@title='Login']")).click();
	
	Thread.sleep(12000);
	
	Assert.assertEquals(driver.getPageSource().contains(firstname.trim()), true); 
	
	//System.out.println(driver.getPageSource());
//	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+firstname);

}
}
