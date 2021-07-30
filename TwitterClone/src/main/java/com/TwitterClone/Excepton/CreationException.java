package com.TwitterClone.Excepton;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CreationException extends CustormizeException {

	public CreationException(String code ,  String status ,  String message) {
	    super(code , status , message) ;
	}
}
