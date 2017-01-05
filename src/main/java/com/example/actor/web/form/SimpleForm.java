package com.example.actor.web.form;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SimpleForm implements Serializable {

	private static final long serialVersionUID = -157143280035400042L;

	@NotNull
	@Size(min = 1, max = 120)
	private String ftext;

	@Pattern(regexp = "((19|[2-9][0-9])[0-9]{2})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])")
	private String ftdate;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fdate;

	@Digits(integer = 3, fraction = 0)
	private String ftnum;

	@Min(1)
	@Max(999)
	private Integer fnum;

	@Size(min = 1, max = 600)
	private String farea;

	@Email
	private String femail;

	@NotNull
	@Size(min = 6, max = 12)
	private String fpass;

	@Pattern(regexp = "A|B|C|D|E")
	private String fselect;

	private String[] fmselect;

	@Pattern(regexp = "on|off")
	private String fcheck;

	private String[] fchecks;

	@NotNull
	@Pattern(regexp = "A|B|C|D|E")
	private String fradio;

}
