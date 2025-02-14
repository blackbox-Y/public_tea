package com.projects.teashop.tea.domain.DTO;

import java.util.Set;

import com.projects.teashop.tea.domain.status.tea_type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeaRequest {
	private String name;
	private Set <tea_type> sort;
	private String region;
	private Short year;
}
