package com.TwitterClone.Excepton;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UnAutherizedException extends CustormizeException {
	
	public UnAutherizedException(String code ,  String status ,  String message) {
	    super(code , status , message) ;
	}
     
}
