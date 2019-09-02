package com.mvvm.cvapplication.util

import com.google.gson.Gson

/**
 * Setup Fake Responses
 * */
object FakeResponses {
    const val fakeCVJsonResponse200 = "{\"firstName\": \"Vaibhav\", \"lastName\": \"Panchal\", \"gender\": \"Male\", \"dob\":\"1987-01-01\", \"email\":\"vaibhav@gmail.com\", \"profilePic\":\"path-to-pic\", \"address\": {\"streetAddress\": \"21 Fake Street\", \"city\": \"New York City\", \"state\": \"NY\", \"zipOrPostal\": \"10021\"}, \"phoneNumber\":\"212 555-1234\" , \"summary\":\"6 years of software development experience in J2EE, Portal and CMS system.\", \"skills\":[\"High-End Application Software: IBM Websphere Portal, Liferay Portal, Alfresco CMS system\", \"Languages: Java, J2EE, Groovy\", \"Frameworks: Spring, Seam, JEE5, JEE6, Hibernate, iBatis, JPA, Freemarker, XML, JSON\", \"Tools: eclipse, RAD, TOAD, MAVEN, Ant, UML, ER-Diagram, jBPM\"], \"projects\":[{\"name\":\"SBS (aka. Shared Business Services)\", \"start\":\"2011-06-01\", \"end\":\"Now\", \"type\":\"development\", \"role\":\"Sr. J2EE DEVELOPER\", \"details\":\"Direct & Shared Business Services is a master layer in conjunction with J2EE web services to access data from FACTS/NASCO/FEP\", \"environment\":\"IBM Websphere Application Server, RAD, Spring IOC, Spring AOP, ehcache, JAXB, JSON, Groovy, DB2, Hibernate\"}, {\"name\":\"RLCM (aka. Role Lifecycle Management)\", \"start\":\"2011-03-01\", \"end\":\"2011-07-01\", \"type\":\"development\", \"role\":\"TECHNICAL LEADER /IBM PORTAL Sr. DEVELOPER\", \"details\":\"Developed the JSR-286 Portlets with Spring MVC and Spring IOC;Utilized SOAP as service interface;Created XML Access Script for setup page structure, url mapping and access control;\", \"environment\":\"IBM Websphere Portal 6.1, IBM AIX Unix, JSR268, Spring, JSP, MAVEN, SOAP, iText, Freemarker, CSS/HTML, jQuery, RAD\"}, {\"name\":\"Member/Provider Portal Enhancement\", \"start\":\"2011-03-01\", \"end\":\"2009-11-01\", \"type\":\"enhancement\", \"role\":\"TECHNICAL LEADER /IBM PORTAL Sr. DEVELOPER\", \"details\":\"Enhancement and Defects fixing of Member/Provider Portal;Developed the JSR-286 Portlets with Spring MVC and Spring IOC;Created Websphere portal theme and theme policy with Websphere portal SPI, Websphere portal tag-lib;Utilized JSON as data exchange format;Created a set of annotation to parse the JSON format string;Utilized the xstream to convert java object to JSON string\", \"environment\":\"IBM Websphere Portal 6.1, IBM AIX Unix, JSR268, Spring, JSP, MAVEN, SOAP, iText, Freemarker, CSS/HTML, jQuery, RAD\"} ] }"

    fun <T>convertJsonToClass(jsonString: String, t : Class<T>): T{
        val gson = Gson()
        return gson.fromJson(jsonString, t)
    }
}