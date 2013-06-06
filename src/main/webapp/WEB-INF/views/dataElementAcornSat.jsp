<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "org.springframework.web.servlet.tags.*" %>
<%@ page import="war.model.DataElementCsiro" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
	<c:when test="${not empty dataelement.acornSatDataList}">
		<h6><center>
		<c:if test="${dataelement.acornSatDataList[0].extreme == true}">
			Extreme measurements by Acorn-Sat stations in the ${userstory.seaport.region.name} region within the period <fmt:formatDate value="${dataelement.acornSatDataList[0].periodStart}" pattern="yyyy" />-<fmt:formatDate value="${dataelement.acornSatDataList[0].periodEnd}" pattern="yyyy" />
		</c:if>
		<c:if test="${dataelement.acornSatDataList[0].extreme == false}">
			Annual mean measurements by Acorn-Sat stations in ${userstory.seaport.region.name} within the period <fmt:formatDate value="${dataelement.acornSatDataList[0].periodStart}" pattern="yyyy" />-<fmt:formatDate value="${dataelement.acornSatDataList[0].periodEnd}" pattern="yyyy" />
		</c:if>
		</center></h6>
		<br />
		
		<table class="data display datatable">
		<thead>
			<tr>
				<th>ACORN-SAT stations</th>
				<th>Station Name</th>
				<c:if test="${dataelement.acornSatDataList[0].extreme == true}">
				<th>Highest temperature</th>
				<th>Highest daily rainfall</th>
				<th>Maximum wind gust</th>
				</c:if>
				<c:if test="${dataelement.acornSatDataList[0].extreme == false}">
				<th>Annual mean surface <br />temperature</th>
				<th>Annual mean <br />rainfall</th>
				<th colspan="2">Annual mean Relative humidity</th>
				<th colspan="2">Annual Mean wind speed</th>
				</c:if>
			</tr>
			</thead>
			<tbody>
				<c:if test="${dataelement.acornSatDataList[0].extreme == false}">
				<tr>
					<td colspan="4"></td>
					<td><b>9am</b></td>
					<td><b>3pm</b></td>
					<td><b>9am</b></td>
					<td><b>3pm</b></td>
				</tr>
				</c:if>
				<tr>
					<td rowspan="10" width="420">
						<c:set var="pictureName" value="/resources/img/data/acornsat/acornsat-${fn:replace(userstory.seaport.region.name, ' ', '-')}.png" />
						<c:set var="formattedPictureName" value="${fn:toLowerCase(pictureName)}" />
						<img src="<c:url value="${formattedPictureName}" />" alt="ACORN-SAT stations map" />
					</td>
				</tr>
				<c:forEach items="${dataelement.acornSatDataList}" var="acornSatData" varStatus="dataLoopStatus">
					<c:if test="${prevData.acornSatStation.name != acornSatData.acornSatStation.name}">
					<td></td>
					</tr>
					<tr class="${dataLoopStatus.index % 2 == 0 ? 'even' : 'odd'}">
						<td>${acornSatData.acornSatStation.name}</td>
					</c:if>

					<td>
						<c:out value="${acornSatData.value}" /> ${acornSatData.variable.uom}<br />
						<c:if test="${acornSatData.extreme == true}">
							<fmt:formatDate value="${acornSatData.dateMeasured}" pattern="dd MMM yyyy" />
						</c:if>
					</td>	
					<c:set var="prevData" value="${acornSatData}" />
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div id="warningMessage" class="message warning">
		<h5>No Data</h5>
		<p>No data corresponding to the selected settings.</p>
	</div>
	</c:otherwise>
</c:choose>