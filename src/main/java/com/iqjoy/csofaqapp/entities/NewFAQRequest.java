package com.iqjoy.csofaqapp.repository;

import javax.persistence.*;
import java.sql.Date;


//this creates the table in the database
@Entity
@Table(name = "csofaqrequest")
public class NewFAQRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String question;
    private boolean approved;
    private Date date;

}
