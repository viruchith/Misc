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
	
	
	protected String validateEmail(String email){
				
		Pattern email_pattern = Pattern.compile(EMAIL_REGEX);
		Matcher email_matcher = email_pattern.matcher(email);
				
		if(email.isEmpty())
		{
			return "Email cannot be empty" ;
			
			
			}else if(email_matcher.matches()){
				
				return "valid";
				
				}else{
					
					return "Invalid e-mail";
					
					
					}
							
		
		}
		
		
		
		protected String validatePassword(String password){
		
			if(password.isEmpty()){
				
				return "Password cannot be Empty !";
				}else if(password.length()>8){
					return "valid";
					
					
					}
					else{
						
						return "Invalid Password Format !";
						
						}
			
			
			}
			
			
			
			protected String validateName(String name){
								
				if(name.isEmpty()){
					return "Cannot be empty !";
					
					}else if(name.matches("[A-Z][a-z]*")||name.matches("[a-z]*")){
						
						return "valid";
						
						}else{
							
							return "can contain only alphabets" ; 
							
							}				
				
				}
				
				
				
				protected String validateMobileNumber(String mob){
										
					if(mob.isEmpty()){
						return "cannot be left empty !";
						
						}else if(mob.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")){
							
							return "valid";
							
							}else{
								
								return "Invalid format !" ;
								
								}					
					
					}
					
				
				protected String validateFieldByRangeLength(int minLength,int maxLength,String value){
					
					
					if(value.isEmpty()){
						
						return "cannot be empty !";
						
						}else if(value.length()>=minLength && value.length()<=maxLength ){
							
							return "valid";
							
							}else{
								
								return "Must be "+Integer.toString(minLength)+" to  "+Integer.toString(maxLength)+" characters in length !" ;
								
								
								}
								
					
					}
					
					protected String validateFieldByLength(int length,String value){
						
						
						if(value.isEmpty()){
							
							return "cannot be empty !" ; 
							
							}else if(value.length()<=length){
								
								return "valid"; 
								
								}else{
										return "Must be "+Integer.toString(length)+" characters in length !" ;
									
									}
														
						}
		
	
	}
