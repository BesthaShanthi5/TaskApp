package sms;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.ws.soap.MTOM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String qualification;
	private String universityName;
	private  double percentage;
	private boolean higherQualification;
	@ManyToOne
	private Student student;
	
	

}
