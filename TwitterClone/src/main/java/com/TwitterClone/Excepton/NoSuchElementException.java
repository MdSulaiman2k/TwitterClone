package com.TwitterClone.Excepton;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NoSuchElementException extends CustormizeException {
	
	public NoSuchElementException(String code ,  String status ,  String message) {
	    super(code , status , message) ;
	}


}
