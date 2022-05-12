package com.main;

import java.util.Scanner;

import com.beans.Customer;
import com.beans.Login;
import com.service.CustomerService;
import com.service.VendorService;

public class App {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			Customer Customer =  new Customer();
			String username="";
			Login login = new Login(); 
			VendorService vendorService = new VendorService(); 
			while(true) {
				System.out.println("--------------------------------");
				System.out.println("    Canteen Management System   ");
				System.out.println("--------------------------------");
				System.out.println("1. For Customer Login");
				System.out.println("2. For Registering New Customer");
				System.out.println("3. Change Password");
				System.out.println("4. For Logging Out");
				System.out.println("5. For Vendor Login"); //If vendor login show vendor menu
				
				System.out.println("Enter your choice");
				int input = sc.nextInt();
				if(input == 4) {
					System.out.println("Logging out...");
					System.out.println("Bye " + username);
					break; 
				}
				
				switch(input) {
					case 1:
						System.out.println("Enter Customer Username");
						sc.nextLine();
						username=sc.nextLine();
						login.setUsername(username);
						
						System.out.println("Enter Password");
						String password=sc.next();
						login.setPassword(password);
						
						//check if username and password are present in User table 
						CustomerService customerService = new CustomerService();
						boolean status = customerService.checkLogin(login);
						if(status == true) {
							//display the full menu
							System.out.println("Welcome " + username);
							while(true) {
								System.out.println("1. Show menu");
								System.out.println("2. Placing order");
								System.out.println("3. Order history");
								System.out.println("4. profile");
								System.out.println("5. Wallet balance");
								System.out.println("6. Cancel Order");
								System.out.println("7. Rating order");
								System.out.println("8. Total Calories consumption");
								System.out.println("9. Previous Menu");
								input = sc.nextInt(); 
								if(input == 9) {
									break; 
								}
								switch(input) {
									case 1: 
										/* ========MUST_HAVE===========
										 * From fooditem table, display 
										 * food_id | food_name   | food_price
										 * 
										 * code tip: create FoodItem class, in DB(ResultSet) 
										 * fetch each row into FoodItem object, add object to List 
										 * and iterate through list.  
										 */
										
										break;
									case 2: 
										/*  ========MUST_HAVE===========
										 * Place Order
										 * show foodItem menu => ask(foodItem ID) => ask(quantity)
										 * compute(total_amount=foodItem_price*quantity)//400
										 * go to DB=> fetch customer details based on username=> (customer)
										 * check(customer.walletBalance>total_amount?insert into order history table: 
										 * display "less balance message". )
										 * Note: for date_time: use LocalDate.now() 
										 */
										break;
									case 3:
										/* ========MUST_HAVE===========
										 * go to DB=> fetch records from order_details based on customer username. 
										 * In SQL Query: connect(join) order_details to customer, 
										 * then customer to login 
										 * and apply criteria on login.username. 
										 * take list from DB and iterate over it. 
										 */
										break;
									case 4:
										/* ========MUST_HAVE===========
										 * Go to DB and fetch all customer info, along with username and display 
										 * For username info, join customer to login
										 */
										break;
									case 5:
										/* ========MUST_HAVE===========
										 * go to DB and fetch wallet balance of the customer 
										 */
										break;
									case 6:
										/* ========MUST_HAVE===========
										 * Show all PENDING orders of customer based on username.
										 * for this=> connect login to customer and customer to order_details  
										 * Take order ID as input from the user and update the order_status as
										 * "CANCELLED". 
										 *   
										 */
										break;
									case 7:
										/*  ========COULD_HAVE===========
										 * Rating: After the order is placed, ask the customer for rating about this
										 * order out of 5. 
										 * save this in order_history table. for this, you have to add a column
										 * "rating" for each order. 
										 */
										break;
									case 8:
										/* ========COULD_HAVE===========
										 * add calories column in fooditem table. 
										 * fetch list of all food items ordered by customer based on username. 
										 * for this connect/join=> login-customer-order_details-fooditem 
										 * compute sum of all calories and display the value. 
										 */
										break;
									default:
										System.out.println("Invalid Input");
										break;
								}
							}
							
						}
						else {
							System.out.println("Invalid Credentials, please try again");
						}
						break;
					case 2:
						/* ========NICE_TO_HAVE===========
						 * Register New Customer
						 * Take Input from Customer: customer_name,username,password 
						 * set customer_coupon="something", customer_walletBalance=0   
						 * Save customer info in customer table and login info in login table and 
						 * preserve login_id FK in customer table
						 */ 
						break;
					case 3:
						/* ========NICE_TO_HAVE===========
						 * Change Password:
						 * Take username from user 
						 * if the username is present in the DB, ask for new password
						 * and update the password in the DB
						 */
						break;
					default:
						System.out.println("Invalid Input");
						break;
					case 5: 
						System.out.println("Enter Vendor Username");
						sc.nextLine();
						username=sc.nextLine();
						login.setUsername(username);
						
						System.out.println("Enter Password");
						password=sc.next();
						login.setPassword(password);
						status = vendorService.checkLogin(login);
						if(status == true) {
							//display the full vendor menu
							String name = vendorService.fetchNameByUsername(username);
							System.out.println("Welcome " + name);
							while(true) {
								System.out.println("1. Show menu");
								System.out.println("2. Accept & Reject");
								System.out.println("3. Order History");
								System.out.println("4. Edit Menu");
								System.out.println("5. Profile");
								System.out.println("6. Previous menu");
								input = sc.nextInt();
								if(input == 6) {
									break; 
								}
								switch(input) {
								case 1: 
									/* ========NICE_TO_HAVE===========
									 * fetch fooditem rows based on vendor username
									 * connect fooditem => vendor => login  
									 * iterate over result 
									 */
									break; 
								case 2:
									  /* ========COULD_HAVE===========
									   * ACCEPT+REJECT 
									   * show all orders based on vendor username as done in case 3
									   * Ask for Order_details_id. 
									   * give 2 options 
									   * 1. ACCEPT ORDER
									   * 2. REJECT ORDER 
									   * if input=1?update order_status= "ACCEPT": update order_status= "REJECT"
									   * based on Order_details_id
									   */
									break;
								case 3: 
									/* ========NICE_TO_HAVE===========
									 * Fetch order_details based on vendor username
									 * order_details=>fooditem=>vendor=>login 
									 * iterate over order_details rows 
									 */
									break;
								case 4: 
									/* ========COULD_HAVE===========
									 * EDIT MENU
									 * Show menu as shown in case 1
									 * Ask vendor which fooditemID it wants to edit : fooditem_id=4
									 * Ask new values of name and price of the fooditem. 
									 * go to DB and update this new values of name and price for given
									 * fooditemID
									 */
									break;
								case 5: 
									/* ========NICE_TO_HAVE===========
									 * Display vendor info based on vendor username
									 * vendor=> login
									 */
									break;
								}
							} 
						}else {
							System.out.println("Invalid Credentials, please try again");
						}
						break;
				}
			}
		}	
	}
}

