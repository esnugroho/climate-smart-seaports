<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.codec.binary.*"%>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ page import="org.apache.commons.lang.*" %>
<%@ page language="java" import="war.model.UserStory" %>
<%@ page language="java" import="war.model.User" %>
<%@ page language="java" import="war.model.DataElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="grid_12">
	<h2>${listingTitle}</h2>
	<c:choose> 
	<c:when test="${not empty userStoriesList}">
		<table class="data display datatable" id="tblUserStoryList">
			<thead>
				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Published on</th>
					<th>NRM Region</th>
					<th>View</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userStoriesList}" var="story" varStatus="status"> 
				<tr>
					<td><c:out value="${story.name}" /></td>
					<td><a href="/public/user/${story.owner.username}" title="View profile">${story.owner.firstname} ${story.owner.lastname}</a></td>
					<td><fmt:formatDate value="${story.publishDate}" pattern="dd MMM yyyy" /></td>
					<td>${story.seaport.region.name}</td>
					<td><a href="/public/reports/view?id=${story.id}" title="View this Story" target="_blank"><img src="<c:url value="/resources/img/icons/page_white.png" />" alt="View" /></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
			$(document).ready(function () {
				$('#tblUserStoryList').dataTable();
			});
	 	</script>
	</c:when>
	<c:otherwise>
		<i>There is no published report available.</i>
	</c:otherwise>
	</c:choose>
</div>	
