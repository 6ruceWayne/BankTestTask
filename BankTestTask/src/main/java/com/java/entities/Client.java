package com.java.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	@Size(min = 2, max = 20, message = "User name size has to be between 2 and 20 characters")
	private String name;
	@NonNull
	@NotNull
	private String address;
	@NonNull
	@Min(value = 18, message = "User has to be adult")
	@Max(value = 150, message = "Age should not be greater than 150")
	private int age;
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts;
	@CreationTimestamp
	private Timestamp data;
}
