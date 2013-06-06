<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="grid_12">
	<div class="message info">
		<h5>Information: BETA version</h5>
		<p>This is a BETA version of the tool that currently has data for three NRM regions. Please refer to the Limitations section of the Guidance document for further process and data limitations.</p>
	</div>
	<br />
	<sec:authorize access="!isAuthenticated()">
	<center>
		<input type="button" class="btn btn-blue btn-large" value="Log in" onclick="location.href='/auth/workboard/my-workboard'" />&nbsp;
		<input type="button" class="btn btn-blue btn-large" value="Sign up" onclick="location.href='/register'" />
	</center>
	<br />
	</sec:authorize>
	
	<p class="info-paragraph">The Climate Smart Seaports Tool enables interested users to begin the process of a climate risk assessment. It assists them to identify current and historical climate trends and variability, as well as future climate projections under a variety of scenarios.</p> 
	
	<p class="info-paragraph">Population and trade data is included, and users can add port-specific information to round out their analysis.</p>
	
	<p class="info-paragraph">Adapting to climate change is still in its infancy and all organisations are learning how to manage the risks posed by the changing climate. One way to learn is by communication. One of the aims of this tool is that users will publish their generated reports to the Climate Smart Seaports site, promoting peer-to-peer learning and the spread of knowledge.</p>
	
	<p class="info-paragraph">Often risk management is confined to specific areas of a business operation, eg. risks related to trade, to supply chains to the workplace functions or infrastructure. However, climate change poses a risk to all areas of a business. This tool attempts to bring the thinking about climate risks in different areas of the business into one report, beginning the creation of an integrated climate risk assessment.</p>	
	
	<a href="/public/guidelines#introduction">Read more...</a>
</div>