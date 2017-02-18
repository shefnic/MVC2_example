<%-- 
    Document   : index
    Created on : Nov 2, 2016, 8:56:25 PM
    Author     : nicholas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">


	<title>Beartooth Hiking Company</title>    
    
    <jsp:useBean id="outQuote" class="beans.RatesBean" scope="session" />    

    <link rel="stylesheet" type="text/css" href="css/BHC3.css" />
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
    <script type="text/javascript" src="js/BHC.js"></script>



<body id="main">
	
	<div id="imgwrapper" class="wrapper">
		
		<img src="img/Beartooth002-01.jpg" alt="Welcome" id="headimg"/>
		<div id="imgheadtext">
			Beartooth Hiking Company: Explore Wilderness With Us
		</div>
		
	</div>
	
	<div id="intro" class="text">
		
		Beartooth Hiking Company is proud to be offering expedition services to the public.
		We offer a variety of expeditionary hiking tours covering multiple durations and
		experience levels.
		
		Please familiarize yourself with our scenic location here in the beautiful 
		<a href="http://www.wilderness.net/index.cfm?fuse=NWPS&amp;sec=wildView&amp;WID=1">
		Absaroka-Beartooth Wilderness Region</a> of Montana.
		
	</div>
	
	<div id="content" class="text">
	
		<div id="upper">
	
			Beartooth Hiking Company offers the following packages:<br/>
			
			<ul id="shortlist">
				<li>Gardiner Lake Tour</li>
				<li>Hellroaring Tour</li>
				<li>The Beaten Path</li>
			</ul>
		

			<img src="img/Hiking2.gif" alt="Join us on the trails"/>
		
		</div>
		<br/>
		<table id="packages">
		<caption align="bottom">Saturday/Sunday: 
			All hikes subject to 50% surcharge
		</caption>
			
			<thead>
				<tr>
					<td>Package</td>
					<td>Duration (Days)</td>
					<td>Difficulty</td>
					<td>Price</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Gardiner Lake</td>
					<td>3 or 5</td>
					<td>Intermediate</td>
					<td>$40/day</td>
				</tr>
				<tr>
					<td>Hellroaring</td>
					<td>2, 3, or 4</td>
					<td>Easy</td>
					<td>$35/day</td>
				</tr>
				<tr>
					<td>Beaten Path</td>
					<td>5 or 7</td>
					<td>Difficult</td>
					<td>$45/day</td>
				</tr>
			</tbody>

		</table>

	
	</div>
	
	<div id="form_wrapper">
		<form id="rate_quote" name="rate_quote" action="HW_Controller" method="POST">
			<h1 id="form_title">Rate Quote</h1>
            Party Size:<br/>            
            <select id="party" name="party"></select>
            
            <br/>
			Choose Tour:<br/>
			<select id="tours" name="tours" size="3"></select>
			<br/>
			Enter Start Date:<br/>
			<input id="day" name ="day" class="date" type="text" 
				placeholder="DD" maxlength="2" size="2"
				max="31" required="required" pattern="\d{2}"/>
			/
			<input id="month" name="month" class="date" type="text" 
				placeholder="MM" maxlength="2" size="2"
				max="12" required="required" pattern="\d{2}"/>
			/
			<input id="year" name="year" class="date" type="text" 
				placeholder="YYYY" maxlength="4" size="4" 
				required="required" pattern="2\d+"/>
			<br/><br/>
			Choose Duration:<br/>
			<select id="duration" name="duration" size="3"></select>
			<br/>
			<input id="submit" type="submit" />
		</form>
	</div>
    
    <% if(outQuote != null){ %>
    
        <h1>Quoted Rate</h1>
        <h2>Thank you for submitting a query</h2>
        
        
        <% if(outQuote.getDetails() != null) { %>
            Error: <jsp:getProperty property="details" name="outQuote"/>
        <% } else { %> 
            Cost: $<jsp:getProperty property="cost" name="outQuote"/> <br/>
            Weekdays: <jsp:getProperty property="normalDays" name="outQuote"/> <br/>
            Weekends: <jsp:getProperty property="premiumDays" name="outQuote"/>
        <% } %>
        
    <% } else {%>
        Please enter a valid date
    <% } %>

	
</body>

</html>
