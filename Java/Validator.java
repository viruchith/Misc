/*
 * Validator.java
 * 
 * Copyright 2020 Ganesan Koundappan <ganesankoundappan@viruchith.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
 
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator{
	
	private String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	
	private String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
	
	String validateEmail(String email){
		
		String message = "";
		
		Pattern email_pattern = Pattern.compile(EMAIL_REGEX);
		Matcher email_matcher = email_pattern.matcher(email);
				
		if(email.isEmpty())
		{
			message="Email cannot be empty" ;
			
			
			}else if(email_matcher.matches()){
				
				message = "valid";
				
				}else{
					
					message = "Invalid e-mail";
					
					
					}
					
					
			return message;
		
		
		}
		
		
		
		String validatePassword(String password){
			

			
			String message = "";
			
			Pattern password_pattern = Pattern.compile(PASSWORD_REGEX);
			Matcher password_matcher = password_pattern.matcher(password);
			
			if(password.isEmpty()){
				
				message="Password cannot be Empty !";
				}else if(password_matcher.matches()){
					message="valid";
					
					
					}
					else{
						
						message="Invalid Password Format !";
						
						}
			
			return message;
			
			}
			
			
			
			String validateName(String name){
				
				String message = "";
				
				if(name.isEmpty()){
					message = "Cannot be empty !";
					
					}else if(name.matches("[A-Z][a-z]*")||name.matches("[a-z]*")){
						
						message = "valid";
						
						}else{
							
							message="can contain only alphabets" ; 
							
							}
							
							
							return message ;
				
				
				}
				
				
				
				String validateMobileNumber(String mob){
					
					String message = "";
					
					if(mob.isEmpty()){
						message = "cannot be left empty !";
						
						}else if(mob.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")){
							
							message = "valid";
							
							}else{
								
								message = "Invalid format !" ;
								
								}
					
					return message;
					
					
					}
					
				
				String validateFieldByRangeLength(int minLength,int maxLength,String value){
					
					String message = "";
					
					if(value.isEmpty()){
						
						message = "cannot be empty !";
						
						}else if(value.length()>=minLength && value.length()<=maxLength ){
							
							message = "valid";
							
							}else{
								
								message = "Must be "+Integer.toString(minLength)+" to  "+Integer.toString(maxLength)+" characters in length !" ;
								
								
								}
								
								return message ; 
					
					}
					
					String validateFieldByLength(int length,String value){
						
						String message = "" ; 
						
						if(value.isEmpty()){
							
							message = "cannot be empty !" ; 
							
							}else if(value.length()<=length){
								
								message = "valid"; 
								
								}else{
										message="Must be "+Integer.toString(length)+" characters in length !" ;
									
									}
									
									return message ;
					
						}
		
	
	}
