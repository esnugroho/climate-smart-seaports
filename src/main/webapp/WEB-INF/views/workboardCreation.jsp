<%--
 Copyright (c) 2013, RMIT University, Australia.
 All rights reserved.
  
 This code is under the BSD license. See 'license.txt' for details.
 Project hosted at: https://code.google.com/p/climate-smart-seaports/
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" import="war.model.UserStory" %>
<%@ page language="java" import="war.model.User" %>

<div class="grid_12">
	<h2>New Workboard</h2>
	
	<c:set var="successMessage" scope="request" value="${successMessage}"/>
	<c:set var="warningMessage" scope="request" value="${warningMessage}"/>
	<c:set var="errorMessage" scope="request" value="${errorMessage}"/>
	<jsp:include page="notifications.jsp" />
	
	<p class="hint"><i>You have no active workboard. Create a new workboard using the page below, or read the <a href="/public/guidelines#introduction" target="_blank">Climate Smart Seaports User Guide</a> for more information on how to use this tool.</i></p>
	
	<form:form method="POST" action="/auth/workboard/create?username=${user.username}"  modelAttribute="userstory" >
	
	<table class="form" width="100%">
		<tr>
			<td>
				<label style="font-size:13px">Title:</label>
			</td>
			<td class="col2">
				<form:input id="txtWorkboardTitle" path="name" style="width:300px" onblur="checkTitle()" />
			</td>
			<td class="top" style="min-width:300px">
				<span id="titleErrorMessage" style="color:red;"></span>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<label style="font-size:13px">Region Selection <a href="#" class="helpTooltip" title="Hover over a Natural Resource Management region and click to select the region you want. <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2" valign="top">
				<div id="displayUserStoryRegion" style="display:inline; font-size:13px" ><span class="hint"><i>Select a region using the map then select a seaport within it.</i></span></div>
				<span id="regionErrorMessage" style="color:red;"></span>
				<form:hidden id="hdnUserStoryRegion" path="seaport.region.name" />
				<br />
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3" style="vertical-align:center">
				<center><div id="map-container">
				    <img class="map" src="<c:url value="/resources/img/data/nrm-regions-clusters-map.png" />" usemap="#nrm-clusters" 
						data-maphilight='{"strokeColor":"000000","fillColor":"0055A8","groupBy":"alt"}' />
					<!-- Map has been generated using the software MapEdit v3.16 -->
					<map name="nrm-clusters">	
						<area class="mapArea" title="East Coast South" shape="poly" coords="565,279,568,281,571,281,574,281,575,279,579,279,581,278,581,283,582,287,581,289,580,292,578,294,579,299,577,302,578,304,576,306,577,307,574,311,573,315,574,318,573,321,572,326,570,331,566,332,566,336,566,339,563,342,560,345,556,345,552,351,549,358,549,360,547,361,545,364,543,365,539,368,535,369,531,371,528,371,527,373,526,377,524,377,524,373,523,370,521,368,524,367,527,367,527,365,529,363,528,360,530,358,530,353,531,351,529,348,529,346,531,345,534,345,534,345,534,343,525,336,527,334,531,329,533,329,537,330,541,329,546,327,550,324,553,324,554,322,550,318,550,314,551,313,552,311,553,309,553,304,556,301,558,297,559,296,559,291,559,287,557,284,561,282" />
				
						<area class="mapArea" title="Southern Slopes Vic East" shape="poly" coords="544,364,540,367,538,369,536,368,532,370,528,371,526,377,524,377,524,381,523,384,523,387,524,388,524,390,522,391,522,395,523,397,520,396,517,394,515,392,511,388,509,386,508,387,509,389,507,392,506,394,505,397,506,399,503,401,500,402,497,405,495,407,494,407,493,405,490,403,486,403,483,405,480,407,479,409,480,411,478,411,476,411,475,412,475,414,472,414,470,415,471,416,473,418,472,421,470,423,466,424,465,425,466,426,469,427,472,429,472,430,474,430,476,430,476,432,477,434,480,435,482,433,481,431,484,431,484,429,486,428,488,426,490,423,495,420,500,417,509,415,515,415,523,415,525,414,528,412,530,412,531,409,531,406,529,402,531,398,533,393,534,392,533,391,534,387,537,382,539,379,541,378,543,377,543,375,542,372,544,369,544,367" />
							
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="157,345,167,345,167,348,166,351,165,354,164,357,161,358,159,360,158,362,156,360,155,361,154,364,151,363,148,363,143,364,139,365,138,366,137,364,132,363,129,361,126,359,121,359,116,360,108,361,105,361,103,364,104,366,103,367,100,368,96,369,94,368,91,369,88,372,87,374,87,374,84,375,81,377,77,378,76,377,65,376,59,376,56,374,51,373,49,371,45,367,41,366,42,368,38,367,36,363,36,358,37,353,39,354,42,355,45,352,47,348,46,346,45,342,46,340,46,339,46,335,44,334,43,332,46,330,46,328,43,322,39,315,37,310,36,302,37,298,35,294,35,292,31,288,31,284,25,277,24,270,23,267,25,264,29,265,37,267,41,266,42,264,43,260,45,262,43,263,42,266,44,269,46,269,48,271,51,272,51,276,49,277,49,283,51,282,54,280,55,284,53,287,55,290,58,292,60,291,60,289,62,288,64,290,66,292,68,294,66,296,67,299,67,301,68,302,73,302,74,300,79,299,87,299,108,307,108,310,111,314,113,314,116,317,118,317,118,321,119,323,123,325,118,329,114,329,108,338,109,342,107,343,108,345,112,344,117,343,117,341,156,341" />
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="18,279,21,282,23,285,25,288,24,290,23,291,20,289,19,287,18,284" />
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="159,363,157,366,158,368,159,367" />
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="162,360,161,364,164,363" />
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="166,355,164,358,165,359,167,359,167,358" />
						<area class="mapArea" title="Southern and Southwestern Flatlands West" shape="poly" coords="136,366,135,368,137,368" />
					</map>
				</div></center>
			</td>
		</tr>
		<tr>
			<td class="top">
				<label style="font-size:13px">Seaport <a href="#" class="helpTooltip" title="Select your port. <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>"><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2">
				<select id="cbbSeaport" disabled style="width:300px">
					<option value="none">Please first select a region on the map.</option>
				</select>
				<form:hidden id="hdnSeaportCode" path="seaport.code" value="none" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="top">
				<label style="font-size:13px">Purpose of inquiry <a href="#" class="helpTooltip" title="Tell us why you are using this tool. For example, climate risk assessment for work; study (indicate what field) or research (indicate your area of interest / topic). <a href=&quot;/public/guidelines#css-workboard&quot; target=&quot;_blank&quot;>Read more...</a>" ><img src="<c:url value="/resources/img/icons/help.png" />" alt="Help" /></a>:</label>
			</td>
			<td class="col2" valign="top">
				<form:textarea id ="txtWorkboardPurpose" path="purpose" rows="5" cols="30" style="width:300px" onblur="checkPurpose()" value="Test" />
			</td>
			<td class="top" style="min-width:300px">
				<span id="purposeErrorMessage" style="color:red;"></span>
			</td>
		</tr>
	</table>
	
	<div align="center">
	<button id="btnCreateWorkboard" type="submit" class="btn btn-icon btn-blue btn-arrow-right" >
		<span></span>Create
	</button>
	</div>
	</form:form>
	
	<script type="text/javascript">
	$(function() {
		// Map highlight
		$('.map').maphilight();
		
		var dataArr = [
		<c:forEach items="${allSeaports}" var="seaport" varStatus="seaportsLoop">
			{'region':'${seaport.region.name}', 'seaportCode':'${seaport.code}', 'seaportName':'${seaport.name}'}<c:if test="${!seaportsLoop.last}">,</c:if>
		</c:forEach>
		];
		
		// Click on map event
		$(".mapArea").click(function(event) {
			var selectedValue = $(this).attr("title");
			$("#hdnUserStoryRegion").attr("value", selectedValue);
			$("#displayUserStoryRegion").html(selectedValue);
			checkRegion();
			
			// Only append seaports from the selected region
			$('#cbbSeaport option').remove();
			$.each(dataArr, function(i){
			    if (dataArr[i]['region'] == selectedValue){
			        $('#cbbSeaport').append($("<option></option>")
			        		.attr("value",dataArr[i]['seaportCode'])
			        		.text(dataArr[i]['seaportName']));
			    }
			    $("#hdnSeaportCode").val($("#cbbSeaport").val());
			});
			$('#cbbSeaport').removeAttr('disabled');
		});
		
		// Seaport selection change event
		$("#cbbSeaport").change(function (e) {
			$("#hdnSeaportCode").val($("#cbbSeaport").val());
		});
		
		// Field verifications
		$("#titleErrorMessage").hide();
		$("#regionErrorMessage").hide();
		$("#purposeErrorMessage").hide();
		$("#btnCreateWorkboard").click(function (e) {
			if (checkTitle() == false || checkRegion() == false || checkPurpose() == false)
				e.preventDefault();
		});
		
		// Help tooltips activation
		setupTooltips();
	});
	
	function checkTitle() {
		if ($("#txtWorkboardTitle").val().length > 0) {
			$("#txtWorkboardTitle").removeClass("error");
			$("#titleErrorMessage").hide();
			return true;
		}
		else {
			$("#titleErrorMessage").html("Please provide a title for your workboard.");
		}
		$("#txtWorkboardTitle").addClass("error");
		$("#titleErrorMessage").show();
		return false;
	}
	
	function checkRegion() {
		if ($("#hdnUserStoryRegion").val().length > 0) {
			$("#hdnUserStoryRegion").removeClass("error");
			$("#regionErrorMessage").hide();
			return true;
		}
		else {
			$("#regionErrorMessage").html("Please select a region.");
		}
		$("#hdnUserStoryRegion").addClass("error");
		$("#regionErrorMessage").show();
		return false;
	}
	
	function checkPurpose() {
		if ($("#txtWorkboardPurpose").val().length > 0) {
			$("#txtWorkboardPurpose").removeClass("error");
			$("#purposeErrorMessage").hide();
			return true;
		}
		else {
			$("#purposeErrorMessage").html("Please describe the purpose of your work.");
		}
		$("#txtWorkboardPurpose").addClass("error");
		$("#purposeErrorMessage").show();
		return false;
	}
</script>
</div>